package com.iit.placement.careercarve.assignment.scheduler.services.impl;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AreaOfInterestEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.StudentEntity;
import com.iit.placement.careercarve.assignment.scheduler.exception.CustomApplicationException;
import com.iit.placement.careercarve.assignment.scheduler.models.*;
import com.iit.placement.careercarve.assignment.scheduler.repositories.StudentRepository;
import com.iit.placement.careercarve.assignment.scheduler.services.StudentService;
import com.iit.placement.careercarve.assignment.scheduler.utils.ResponseCode;
import com.iit.placement.careercarve.assignment.scheduler.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import javax.ws.rs.core.*;

import java.util.*;

import static org.aspectj.util.LangUtil.isEmpty;

@Service
public class StudentServiceImpl implements StudentService {
    ResponseData responseData;

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseData save(UserRegistration student) {
        String name = student.getName();
        String password = student.getPassword();
        String email = student.getEmail();
        String mobileNo = student.getMobileNo();

        if (isEmpty(name)) {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, ResponseCode.CLIENT_INVALID_REQ_PARAM_USER_NAME.toString());
        }

        if (isEmpty(password)) {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, ResponseCode.CLIENT_INVALID_REQ_PARAM_PASSWORD.toString());
        }

        if (isEmpty(email)) {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST,ResponseCode.CLIENT_INVALID_REQ_PARAM_USER_EMAIL.toString());
        }

        int length = mobileNo.length();
        if (isEmpty(mobileNo) || length != Utils.MOB_NUM_LEN || !StringUtils.isNumeric(mobileNo)) {
            throw new CustomApplicationException(HttpStatus.BAD_REQUEST, ResponseCode.CLIENT_INVALID_REQ_PARAM_MOBILE_NUM.toString());
        }

        Map<String, Object> retData = new LinkedHashMap<>();
        StudentEntity studentEntity = null;
        try{
            studentEntity = studentRepository.findStudentByMobileNo(mobileNo);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }


        StudentEntity newStudentEntity = new StudentEntity();
        newStudentEntity.setName(name);
        newStudentEntity.setPassword(password);
        newStudentEntity.setEmail(email);
        newStudentEntity.setMobileNo(mobileNo);

        try {
            if(studentEntity == null){
                studentEntity = studentRepository.findStudentByEmail(email);

                if(studentEntity == null){
                    newStudentEntity = studentRepository.save(newStudentEntity);
                    User userInfo = new User(newStudentEntity.getId(),
                            newStudentEntity.getName(),
                            newStudentEntity.getEmail(),
                            newStudentEntity.getMobileNo());

                    retData.put("user", userInfo);
                    responseData = new ResponseData(Response.Status.CREATED.getStatusCode(), ResponseCode.SUCCESS.getCode(), retData);
                } else {
                    throw new CustomApplicationException(HttpStatus.CONFLICT, "Email-Id exists");
                }
            } else {
                throw new CustomApplicationException(HttpStatus.CONFLICT, ResponseCode.CLIENT_USER_MOBILE_EXISTING.toString());
            }
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData changePassword(UserPasswordChange userPasswordChange){
        String userName = userPasswordChange.getUserName();
        String oldPassword = userPasswordChange.getPassword();
        String newPassword = userPasswordChange.getNewPassword();

        Map<String, Object> retData = new LinkedHashMap<>();
        StudentEntity studentEntity = null;
        try{
            studentEntity = studentRepository.findStudentByEmail(userName);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity == null){
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        } else {
            try {
                String hashedPassword = studentEntity.getPassword();
                boolean validCredentials = BCrypt.checkpw(oldPassword, hashedPassword);
                if(validCredentials){
                    User userInfo = new User(studentEntity.getId(),
                            studentEntity.getName(),
                            studentEntity.getEmail(),
                            studentEntity.getMobileNo());
                    studentEntity.setPassword(newPassword);
                    studentRepository.save(studentEntity);
                    retData.put("user", userInfo);
                    responseData = new ResponseData(Response.Status.ACCEPTED.getStatusCode(), ResponseCode.SUCCESS.getCode(), retData);
                } else {
                    throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
                }
            } catch (IllegalArgumentException | DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        }

        return responseData;
    }

    @Override
    public void delete(Long id) {
        try{
            studentRepository.deleteById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public ResponseData login(UserLogin studentInfo) {
        String userName = studentInfo.getUserName();
        String password = studentInfo.getPassword();

        Map<String, Object> retData = new LinkedHashMap<>();
        StudentEntity studentEntity = null;
        try{
            studentEntity = studentRepository.findStudentByEmail(userName);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity == null){
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        } else {
            try {
                String hashedPassword = studentEntity.getPassword();
                boolean validCredentials = BCrypt.checkpw(password, hashedPassword);
                if(validCredentials){
                    User userInfo = new User(studentEntity.getId(),
                            studentEntity.getName(),
                            studentEntity.getEmail(),
                            studentEntity.getMobileNo());
                    retData.put("user", userInfo);
                    responseData = new ResponseData(Response.Status.ACCEPTED.getStatusCode(), ResponseCode.SUCCESS.getCode(), retData);
                } else {
                    throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
                }
            } catch (IllegalArgumentException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        }

        return responseData;
    }

    @Override
    public ResponseData update(StudentUpdate studentInfo) {
        String userName = studentInfo.getName();
        String userEmail = studentInfo.getEmail();
        String userMobileNo = studentInfo.getMobileNo();
        String userCollegeName = studentInfo.getCollegeName();
        AreaOfInterestEntity areaOfInterestEntity = studentInfo.getAreaOfInterest();


        Map<String, Object> retData = new LinkedHashMap<>();
        StudentEntity studentEntity = null;
        try{
            studentEntity = studentRepository.findStudentByEmail(userEmail);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity == null){
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        } else {
            try{
                if(userName != null){
                    studentEntity.setName(userName);
                }

                if(userMobileNo  != null){
                    if (userMobileNo.length() != Utils.MOB_NUM_LEN || !StringUtils.isNumeric(userMobileNo)) {
                        throw new CustomApplicationException(HttpStatus.BAD_REQUEST, ResponseCode.CLIENT_INVALID_REQ_PARAM_MOBILE_NUM.toString());
                    } else {
                        StudentEntity mobileNoExistsForAnotherStudent = studentRepository.findStudentByMobileNo(userMobileNo);
                        if (mobileNoExistsForAnotherStudent == null || Objects.equals(mobileNoExistsForAnotherStudent.getMobileNo(), userMobileNo))
                            studentEntity.setMobileNo(userMobileNo);
                        else {
                            throw new CustomApplicationException(HttpStatus.CONFLICT, ResponseCode.CLIENT_USER_MOBILE_EXISTING.toString());
                        }
                    }
                }

                if(userCollegeName != null){
                    studentEntity.setCollegeName(userCollegeName);
                }

                if(areaOfInterestEntity != null){
                   studentEntity.setAreaOfInterest(areaOfInterestEntity);
                }

                studentEntity = studentRepository.save(studentEntity);

                retData.put("user", studentEntity);
                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        }

        return responseData;
    }
}
