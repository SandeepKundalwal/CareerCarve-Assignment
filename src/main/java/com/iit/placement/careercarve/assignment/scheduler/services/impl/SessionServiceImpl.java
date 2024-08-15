package com.iit.placement.careercarve.assignment.scheduler.services.impl;

import com.iit.placement.careercarve.assignment.scheduler.domain.entities.*;
import com.iit.placement.careercarve.assignment.scheduler.exception.CustomApplicationException;
import com.iit.placement.careercarve.assignment.scheduler.models.*;
import com.iit.placement.careercarve.assignment.scheduler.repositories.*;
import com.iit.placement.careercarve.assignment.scheduler.services.SessionService;
import com.iit.placement.careercarve.assignment.scheduler.utils.ResponseCode;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {
    ResponseData responseData;

    private final MentorRepository mentorRepository;
    private final StudentRepository studentRepository;
    private final SessionRepository sessionRepository;
    private final AvailabilityRepository availabilityRepository;
    private final AreaOfInterestRepository areaOfInterestRepository;

    public SessionServiceImpl(MentorRepository mentorRepository, StudentRepository studentRepository, SessionRepository sessionRepository, AvailabilityRepository availabilityRepository, AreaOfInterestRepository areaOfInterestRepository) {
        this.mentorRepository = mentorRepository;
        this.studentRepository = studentRepository;
        this.sessionRepository = sessionRepository;
        this.availabilityRepository = availabilityRepository;
        this.areaOfInterestRepository = areaOfInterestRepository;
    }

    @Override
    public ResponseData createNewSession(Long mentorId, Long studentId, LocalDateTime sessionStartTime, long duration, Long areaOfInterestId) {

        MentorEntity mentorEntity = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new CustomApplicationException(HttpStatus.CONFLICT, "Mentor does not exist"));

        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomApplicationException(HttpStatus.CONFLICT, "Student does not exist"));

        AreaOfInterestEntity areaOfInterestEntity = areaOfInterestRepository.findById(areaOfInterestId)
                .orElseThrow(() -> new CustomApplicationException(HttpStatus.CONFLICT, "Area Of Interest does not exist"));


        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setMentorEntity(mentorEntity);
        sessionEntity.setStudentEntity(studentEntity);
        sessionEntity.setSessionStartTime(sessionStartTime);
        sessionEntity.setDuration(duration);
        sessionEntity.setAreaOfInterest(areaOfInterestEntity);
        sessionEntity.setStatus(SessionStatus.SCHEDULED);

        try{
            sessionRepository.save(sessionEntity);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), "Session Created Successfully.");
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData previousSessionsOfStudent(Long studentId) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<StudentEntity> studentEntity;
        try{
            studentEntity = studentRepository.findById(studentId);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity.isPresent()){
            try {
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionBeforeCurrentTimeAndByIdForStudent(currentDateTime, studentId);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }

                List<StudentSessionDTO> allStudentSessionDTos = new ArrayList<>();
                for(SessionEntity sessionEntity : allSessionEntities){
                    StudentSessionDTO studentSessionDTO = new StudentSessionDTO();
                    studentSessionDTO.setId(sessionEntity.getId());

                    MentorEntityDTO mentorEntityDTO = new MentorEntityDTO(sessionEntity.getMentorEntity().getId(),
                            sessionEntity.getMentorEntity().getName(),
                            sessionEntity.getMentorEntity().getEmail(),
                            sessionEntity.getMentorEntity().getMobileNo(),
                            sessionEntity.getMentorEntity().getCompanyName(),
                            sessionEntity.getMentorEntity().getJobTitle()
                    );

                    studentSessionDTO.setMentorEntityDTO(mentorEntityDTO);

                    AreaOfInterestEntityDTO areaOfInterestEntityDTO = new AreaOfInterestEntityDTO(
                            sessionEntity.getAreaOfInterest().getId(),
                            sessionEntity.getAreaOfInterest().getName(),
                            sessionEntity.getAreaOfInterest().getDescription()
                    );

                    studentSessionDTO.setAreaOfInterestEntityDTO(areaOfInterestEntityDTO);
                    studentSessionDTO.setSessionStartTime(sessionEntity.getSessionStartTime());
                    studentSessionDTO.setDuration(sessionEntity.getDuration());
                    studentSessionDTO.setStatus(sessionEntity.getStatus());

                    allStudentSessionDTos.add(studentSessionDTO);
                }

                retData.put("sessions", allStudentSessionDTos);
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
    public ResponseData upcomingSessionsOfStudent(Long studentId) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        Map<String, Object> retData = new LinkedHashMap<>();
        Optional<StudentEntity> studentEntity;
        try{
            studentEntity = studentRepository.findById(studentId);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        if(studentEntity.isPresent()){
            try {
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionAfterCurrentTimeAndByIdForStudent(currentDateTime, studentId);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }

                List<StudentSessionDTO> allStudentSessionDTos = new ArrayList<>();
                for(SessionEntity sessionEntity : allSessionEntities){
                    StudentSessionDTO studentSessionDTO = new StudentSessionDTO();
                    studentSessionDTO.setId(sessionEntity.getId());

                    MentorEntityDTO mentorEntityDTO = new MentorEntityDTO(sessionEntity.getMentorEntity().getId(),
                            sessionEntity.getMentorEntity().getName(),
                            sessionEntity.getMentorEntity().getEmail(),
                            sessionEntity.getMentorEntity().getMobileNo(),
                            sessionEntity.getMentorEntity().getCompanyName(),
                            sessionEntity.getMentorEntity().getJobTitle()
                    );

                    studentSessionDTO.setMentorEntityDTO(mentorEntityDTO);

                    AreaOfInterestEntityDTO areaOfInterestEntityDTO = new AreaOfInterestEntityDTO(
                            sessionEntity.getAreaOfInterest().getId(),
                            sessionEntity.getAreaOfInterest().getName(),
                            sessionEntity.getAreaOfInterest().getDescription()
                    );

                    studentSessionDTO.setAreaOfInterestEntityDTO(areaOfInterestEntityDTO);
                    studentSessionDTO.setSessionStartTime(sessionEntity.getSessionStartTime());
                    studentSessionDTO.setDuration(sessionEntity.getDuration());
                    studentSessionDTO.setStatus(sessionEntity.getStatus());

                    allStudentSessionDTos.add(studentSessionDTO);
                }

                retData.put("sessions", allStudentSessionDTos);
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
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionBeforeCurrentTimeAndByIdForMentor(currentDateTime, id);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }

                List<MentorSessionDTO> allMentorSessionDTos = new ArrayList<>();
                for(SessionEntity sessionEntity : allSessionEntities){
                    MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
                    mentorSessionDTO.setId(sessionEntity.getId());

                    StudentEntityDTO studentEntityDTO = new StudentEntityDTO(sessionEntity.getStudentEntity().getId(),
                            sessionEntity.getStudentEntity().getName(),
                            sessionEntity.getStudentEntity().getEmail(),
                            sessionEntity.getStudentEntity().getMobileNo(),
                            sessionEntity.getStudentEntity().getCollegeName());

                    mentorSessionDTO.setStudentEntityDTO(studentEntityDTO);


                    AreaOfInterestEntityDTO areaOfInterestEntityDTO = new AreaOfInterestEntityDTO(
                            sessionEntity.getAreaOfInterest().getId(),
                            sessionEntity.getAreaOfInterest().getName(),
                            sessionEntity.getAreaOfInterest().getDescription()
                    );

                    mentorSessionDTO.setAreaOfInterestEntityDTO(areaOfInterestEntityDTO);
                    mentorSessionDTO.setSessionStartTime(sessionEntity.getSessionStartTime());
                    mentorSessionDTO.setDuration(sessionEntity.getDuration());
                    mentorSessionDTO.setStatus(sessionEntity.getStatus());

                    allMentorSessionDTos.add(mentorSessionDTO);
                }

                retData.put("sessions", allMentorSessionDTos);
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
                List<SessionEntity> allSessionEntities = sessionRepository.findSessionAfterCurrentTimeAndByIdForMentor(currentDateTime, id);
                if(allSessionEntities.isEmpty()){
                    throw new CustomApplicationException(HttpStatus.NOT_FOUND, "No session found.");
                }

                List<MentorSessionDTO> allMentorSessionDTos = new ArrayList<>();
                for(SessionEntity sessionEntity : allSessionEntities){
                    MentorSessionDTO mentorSessionDTO = new MentorSessionDTO();
                    mentorSessionDTO.setId(sessionEntity.getId());

                    StudentEntityDTO studentEntityDTO = new StudentEntityDTO(sessionEntity.getStudentEntity().getId(),
                            sessionEntity.getStudentEntity().getName(),
                            sessionEntity.getStudentEntity().getEmail(),
                            sessionEntity.getStudentEntity().getMobileNo(),
                            sessionEntity.getStudentEntity().getCollegeName());

                    mentorSessionDTO.setStudentEntityDTO(studentEntityDTO);


                    AreaOfInterestEntityDTO areaOfInterestEntityDTO = new AreaOfInterestEntityDTO(
                            sessionEntity.getAreaOfInterest().getId(),
                            sessionEntity.getAreaOfInterest().getName(),
                            sessionEntity.getAreaOfInterest().getDescription()
                    );

                    mentorSessionDTO.setAreaOfInterestEntityDTO(areaOfInterestEntityDTO);
                    mentorSessionDTO.setSessionStartTime(sessionEntity.getSessionStartTime());
                    mentorSessionDTO.setDuration(sessionEntity.getDuration());
                    mentorSessionDTO.setStatus(sessionEntity.getStatus());

                    allMentorSessionDTos.add(mentorSessionDTO);
                }

                retData.put("sessions", allMentorSessionDTos);
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
    public ResponseData updateSessionStatus(Long id, SessionStatus status) {
        SessionEntity sessionEntity = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if(LocalDateTime.now().isAfter(sessionEntity.getSessionStartTime())){
            throw new CustomApplicationException(HttpStatus.CONFLICT, "Cannot change the status of previous sessions.");
        }

        sessionEntity.setStatus(status);

        try {
            sessionRepository.save(sessionEntity);
            responseData = new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), "Successfully Updated.");
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        return responseData;
    }

    // should also handle if the mentor is not available on this particular date
    @Override
    public ResponseData findSessionsBasedOnAreaOfInterestAndDate(Long areaOfInterestId, LocalDate date) {
        if(date.getDayOfWeek() == java.time.DayOfWeek.SUNDAY){
            throw new CustomApplicationException(HttpStatus.CONFLICT, "Scheduling Sessions on Sunday is not allowed");
        }

        AreaOfInterestEntity areaOfInterestEntity = areaOfInterestRepository.findById(areaOfInterestId)
                .orElseThrow(() -> new RuntimeException("Area of Interest not found"));

        List<MentorEntity> allMentors = areaOfInterestEntity.getMentorEntities();

        List<MentorEntityDTO> allMentorsDTO = new ArrayList<>();
        for(MentorEntity mentorEntity : allMentors){
            MentorEntityDTO mentorEntityDTO = new MentorEntityDTO(
                    mentorEntity.getId(),
                    mentorEntity.getName(),
                    mentorEntity.getEmail(),
                    mentorEntity.getMobileNo(),
                    mentorEntity.getCompanyName(),
                    mentorEntity.getJobTitle()
            );

            allMentorsDTO.add(mentorEntityDTO);
        }

        Map<String, Object> retData = new LinkedHashMap<>();
        retData.put("mentors", allMentorsDTO);
        return new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
    }

    @Override
    public ResponseData showMentorAvailabilityOnParticularDate(Long mentorId, LocalDate date, long duration) {
        MentorEntity mentorEntity = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        List<SessionEntity> mentorsBookedSessions;
        try{
            mentorsBookedSessions = sessionRepository.findBookedSessionOfMentorForAreaOfInterestAndDate(mentorId, date);
        } catch (DataAccessException e){
            throw new CustomApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SERVER_INTERNAL_SERVER_ERROR.toString());
        }

        LocalDateTime earliestPossibleStart;
        LocalDateTime latestPossibleEnd;
        java.time.DayOfWeek day = date.getDayOfWeek();

        if (day == java.time.DayOfWeek.SATURDAY) {
            earliestPossibleStart = date.atTime(9, 0);
            latestPossibleEnd = date.atTime(21, 0);
        } else {
            earliestPossibleStart = date.atTime(19, 0);
            latestPossibleEnd = date.atTime(23, 0);
        }

        Map<String, Object> retData = new LinkedHashMap<>();
        if (mentorsBookedSessions.isEmpty()) {
            MentorAvailability mentorAvailability = new MentorAvailability(mentorId, earliestPossibleStart);
            retData.put("mentorAvailability", mentorAvailability);
            return new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        }

        mentorsBookedSessions.sort(Comparator.comparing(SessionEntity::getSessionStartTime));

        // Check for gaps between existing sessions
        LocalDateTime candidateStart = earliestPossibleStart;
        for (SessionEntity session : mentorsBookedSessions) {
            LocalDateTime currentSessionStart = session.getSessionStartTime();
            LocalDateTime currentSessionEnd = currentSessionStart.plusMinutes(session.getDuration());

            if (!candidateStart.plusMinutes(duration).isAfter(currentSessionStart)) {
                MentorAvailability mentorAvailability = new MentorAvailability(mentorId, earliestPossibleStart);
                retData.put("mentorAvailability", mentorAvailability);
                return new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
            }

            candidateStart = currentSessionEnd;
        }

        // If no gaps found, check after the last session
        if (!candidateStart.plusMinutes(duration).isAfter(latestPossibleEnd)) {
            MentorAvailability mentorAvailability = new MentorAvailability(mentorId, candidateStart);
            retData.put("mentorAvailability", mentorAvailability);
            return new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), retData);
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(), Response.Status.OK.getStatusCode(), "Mentor is not available for the selected date and duration");
    }
}
