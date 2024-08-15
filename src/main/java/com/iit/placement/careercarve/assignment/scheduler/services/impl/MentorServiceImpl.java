package com.iit.placement.careercarve.assignment.scheduler.services.impl;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AreaOfInterestEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.AvailabilityEntity;
import com.iit.placement.careercarve.assignment.scheduler.domain.entities.MentorEntity;
import com.iit.placement.careercarve.assignment.scheduler.exception.CustomApplicationException;
import com.iit.placement.careercarve.assignment.scheduler.models.*;
import com.iit.placement.careercarve.assignment.scheduler.repositories.AreaOfInterestRepository;
import com.iit.placement.careercarve.assignment.scheduler.repositories.MentorRepository;
import com.iit.placement.careercarve.assignment.scheduler.services.MentorService;
import com.iit.placement.careercarve.assignment.scheduler.utils.ResponseCode;
import com.iit.placement.careercarve.assignment.scheduler.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

import static org.aspectj.util.LangUtil.isEmpty;

@Service
public class MentorServiceImpl implements MentorService {
    ResponseData responseData;

    private final MentorRepository mentorRepository;
    private final AreaOfInterestRepository areaOfInterestRepository;

    public MentorServiceImpl(MentorRepository mentorRepository, AreaOfInterestRepository areaOfInterestRepository) {
        this.mentorRepository = mentorRepository;
        this.areaOfInterestRepository = areaOfInterestRepository;
    }

    @Override
    public ResponseData save(UserRegistration mentorInfo) {
        String name = mentorInfo.getName();
        String password = mentorInfo.getPassword();
        String email = mentorInfo.getEmail();
        String mobileNo = mentorInfo.getMobileNo();

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
        MentorEntity mentorEntity = null;
        try{
            mentorEntity = mentorRepository.findMentorByMobileNo(mobileNo);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }


        MentorEntity newMentorEntity = new MentorEntity();
        newMentorEntity.setName(name);
        newMentorEntity.setPassword(password);
        newMentorEntity.setEmail(email);
        newMentorEntity.setMobileNo(mobileNo);

        try {
            if(mentorEntity == null){
                mentorEntity = mentorRepository.findMentorByEmail(email);

                if(mentorEntity == null){
                    newMentorEntity = mentorRepository.save(newMentorEntity);
                    User userInfo = new User(newMentorEntity.getId(),
                            newMentorEntity.getName(),
                            newMentorEntity.getEmail(),
                            newMentorEntity.getMobileNo());

                    retData.put("mentor", userInfo);
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
    public void delete(Long id) {
        try {
            mentorRepository.deleteById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public ResponseData login(UserLogin mentorInfo) {
        String userName = mentorInfo.getUserName();
        String password = mentorInfo.getPassword();

        Map<String, Object> retData = new LinkedHashMap<>();
        MentorEntity mentorEntity = null;
        try{
            mentorEntity = mentorRepository.findMentorByEmail(userName);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(mentorEntity == null){
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        } else {
            try {
                String hashedPassword = mentorEntity.getPassword();
                boolean validCredentials = BCrypt.checkpw(password, hashedPassword);
                if(validCredentials){
                    User userInfo = new User(mentorEntity.getId(),
                            mentorEntity.getName(),
                            mentorEntity.getEmail(),
                            mentorEntity.getMobileNo());

                    retData.put("mentor", userInfo);
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
    public ResponseData changePassword(UserPasswordChange userPasswordChange) {
        String userName = userPasswordChange.getUserName();
        String oldPassword = userPasswordChange.getPassword();
        String newPassword = userPasswordChange.getNewPassword();

        MentorEntity mentorEntity = null;
        try{
            mentorEntity = mentorRepository.findMentorByEmail(userName);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(mentorEntity == null){
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        } else {
            try {
                String hashedPassword = mentorEntity.getPassword();
                boolean validCredentials = BCrypt.checkpw(oldPassword, hashedPassword);
                if(validCredentials){

                    mentorEntity.setPassword(newPassword);
                    mentorRepository.save(mentorEntity);

                    responseData = new ResponseData(Response.Status.ACCEPTED.getStatusCode(), ResponseCode.SUCCESS.getCode(), "Password Changed Successfully");
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
    public ResponseData update(MentorUpdate mentorInfo) {
        String userName = mentorInfo.getName();
        String userEmail = mentorInfo.getEmail();
        String userMobileNo = mentorInfo.getMobileNo();
        String userCompanyName = mentorInfo.getCompanyName();
        String userJobTitle = mentorInfo.getJobTitle();
        List<Long> areaOfInterestIds = mentorInfo.getAreasOfInterest();

        Map<String, Object> retData = new LinkedHashMap<>();
        MentorEntity mentorEntity = null;
        try{
            mentorEntity = mentorRepository.findMentorByEmail(userEmail);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(mentorEntity == null){
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        } else {
            try{
                if(userName != null){
                    mentorEntity.setName(userName);
                }

                if(userMobileNo != null){
                    if (userMobileNo.length() != Utils.MOB_NUM_LEN || !StringUtils.isNumeric(userMobileNo)) {
                        throw new CustomApplicationException(HttpStatus.BAD_REQUEST, ResponseCode.CLIENT_INVALID_REQ_PARAM_MOBILE_NUM.toString());
                    } else {
                        MentorEntity mobileNoExistsForAnotherStudent = mentorRepository.findMentorByMobileNo(userMobileNo);
                        if (mobileNoExistsForAnotherStudent == null || Objects.equals(mobileNoExistsForAnotherStudent.getMobileNo(), userMobileNo))
                            mentorEntity.setMobileNo(userMobileNo);
                        else {
                            throw new CustomApplicationException(HttpStatus.CONFLICT, ResponseCode.CLIENT_USER_MOBILE_EXISTING.toString());
                        }
                    }
                }

                if(userCompanyName != null){
                    mentorEntity.setCompanyName(userCompanyName);
                }

                if(userJobTitle != null){
                    mentorEntity.setJobTitle(userJobTitle);
                }

                if(areaOfInterestIds != null){
                    List<AreaOfInterestEntity> areasOfInterestListEntity = areaOfInterestRepository.findAllById(areaOfInterestIds);
                    mentorEntity.setAreasOfInterest(areasOfInterestListEntity);
                }

                mentorEntity = mentorRepository.save(mentorEntity);

                MentorUpdateDTO mentorUpdateDTO = new MentorUpdateDTO();
                mentorUpdateDTO.setId(mentorEntity.getId());
                mentorUpdateDTO.setName(mentorEntity.getName());
                mentorUpdateDTO.setEmail(mentorEntity.getEmail());
                mentorUpdateDTO.setMobileNo(mentorEntity.getMobileNo());
                mentorUpdateDTO.setCompanyName(mentorEntity.getCompanyName());
                mentorUpdateDTO.setJobTitle(mentorEntity.getJobTitle());

                List<AreaOfInterestEntityDTO> areaOfInterestEntityDTOList = new ArrayList<>();
                for(AreaOfInterestEntity areaOfInterestEntity : mentorEntity.getAreasOfInterest()){
                    AreaOfInterestEntityDTO areaOfInterestEntityDTO = new AreaOfInterestEntityDTO(
                            areaOfInterestEntity.getId(),
                            areaOfInterestEntity.getName(),
                            areaOfInterestEntity.getDescription()
                    );

                    areaOfInterestEntityDTOList.add(areaOfInterestEntityDTO);
                }

                mentorUpdateDTO.setAreaOfInterestEntityDTO(areaOfInterestEntityDTOList);

                retData.put("mentor", mentorUpdateDTO);
                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        }

        return responseData;
    }
}
