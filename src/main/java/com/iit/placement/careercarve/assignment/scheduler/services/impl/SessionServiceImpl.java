package com.iit.placement.careercarve.assignment.scheduler.services.impl;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.*;
import com.iit.placement.careercarve.assignment.scheduler.exception.CustomApplicationException;
import com.iit.placement.careercarve.assignment.scheduler.models.ResponseData;
import com.iit.placement.careercarve.assignment.scheduler.repositories.AreaOfInterestRepository;
import com.iit.placement.careercarve.assignment.scheduler.repositories.MentorRepository;
import com.iit.placement.careercarve.assignment.scheduler.repositories.SessionRepository;
import com.iit.placement.careercarve.assignment.scheduler.repositories.StudentRepository;
import com.iit.placement.careercarve.assignment.scheduler.services.SessionService;
import com.iit.placement.careercarve.assignment.scheduler.utils.ResponseCode;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SessionServiceImpl implements SessionService {
    ResponseData responseData;

    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final SessionRepository sessionRepository;
    private final AreaOfInterestRepository areaOfInterestRepository;

    public SessionServiceImpl(MentorRepository mentorRepository, StudentRepository studentRepository, SessionRepository sessionRepository, AreaOfInterestRepository areaOfInterestRepository) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.sessionRepository = sessionRepository;
        this.areaOfInterestRepository = areaOfInterestRepository;
    }

    @Override
    public ResponseData createNewSession(Long mentorId, Long studentId, LocalDateTime startTime, LocalDateTime endTime, Integer duration, Long areaOfInterestId) {
        MentorEntity mentorEntity = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new CustomApplicationException(HttpStatus.CONFLICT, "Mentor does not exist"));

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomApplicationException(HttpStatus.CONFLICT, "Student does not exist"));

        AreaOfInterestEntity areaOfInterestEntity = areaOfInterestRepository.findById(areaOfInterestId)
                .orElseThrow(() -> new CustomApplicationException(HttpStatus.CONFLICT, "Area Of Interest does not exist"));

        Map<String, Object> retData = new LinkedHashMap<>();
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setMentorEntity(mentorEntity);
        sessionEntity.setStudentEntity(studentEntity);
        sessionEntity.setSessionStartTime(startTime);
        sessionEntity.setSessionEndTime(endTime);
        sessionEntity.setDuration(duration);
        sessionEntity.setAreaOfInterest(areaOfInterestEntity);
        sessionEntity.setStatus(SessionStatus.SCHEDULED);

        try{
            sessionEntity = sessionRepository.save(sessionEntity);
            retData.put("session", retData);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData previousSessionsOfStudent(Long id) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<StudentEntity> studentEntity;
        try{
            studentEntity = studentRepository.findById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity.isPresent()){
            try {
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionBeforeCurrentTimeAndById(currentDateTime, id);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }
                retData.put("sessions", allSessionEntities);
                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        } else {
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData upcomingSessionsOfStudent(Long id) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<StudentEntity> studentEntity;
        try{
            studentEntity = studentRepository.findById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity.isPresent()){
            try {
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionAfterCurrentTimeAndById(currentDateTime, id);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }
                retData.put("sessions", allSessionEntities);
                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        } else {
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData previousSessionsOfMentor(Long id) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<MentorEntity> mentorEntity;
        try{
            mentorEntity = mentorRepository.findById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(mentorEntity.isPresent()){
            try {
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionBeforeCurrentTimeAndById(currentDateTime, id);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }
                retData.put("sessions", allSessionEntities);
                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        } else {
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData upcomingSessionsOfMentor(Long id) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<MentorEntity> mentorEntity;
        try{
            mentorEntity = mentorRepository.findById(id);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(mentorEntity.isPresent()){
            try {
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionAfterCurrentTimeAndById(currentDateTime, id);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }
                retData.put("sessions", allSessionEntities);
                responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            } catch (DataAccessException e){
                throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
            }
        } else {
            throw new CustomApplicationException(HttpStatus.UNAUTHORIZED, ResponseCode.CLIENT_INVALID_REQ_PARAM_USERID_PASSWORD.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData updateSessionStatus(Long sessionId, SessionStatus status) {
        SessionEntity sessionEntity = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        Map<String, Object> retData = new LinkedHashMap<>();
        sessionEntity.setStatus(status);

        try {
            sessionEntity = sessionRepository.save(sessionEntity);
            retData.put("session", sessionEntity);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }
}
