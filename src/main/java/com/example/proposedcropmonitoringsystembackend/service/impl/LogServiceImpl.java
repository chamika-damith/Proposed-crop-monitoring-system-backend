package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.CropDao;
import com.example.proposedcropmonitoringsystembackend.dao.FieldDao;
import com.example.proposedcropmonitoringsystembackend.dao.LogDao;
import com.example.proposedcropmonitoringsystembackend.dao.StaffDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.*;
import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.LogEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.LogService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private StaffDao staffDao;

    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void save(LogDTO dto) {
        FieldEntity field = null;
        if (dto.getFieldDTO() != null && dto.getFieldDTO().getFieldCode() != null) {
            field = fieldDao.getReferenceById(dto.getFieldDTO().getFieldCode());
        }

        StaffEntity staff = null;
        if (dto.getStaffDTO() != null && dto.getStaffDTO().getId() != null) {
            staff = staffDao.getReferenceById(dto.getStaffDTO().getId());
        }

        CropEntity crop = null;
        if (dto.getCropDTO() != null && dto.getCropDTO().getCropCode() != null) {
            crop = cropDao.getReferenceById(dto.getCropDTO().getCropCode());
        }

        LogEntity logEntity = mapping.toLogEntity(dto);

        if (field != null) {
            logEntity.setFieldEntity(field);
        }

        if (staff != null) {
            logEntity.setStaffEntity(staff);
        }

        if (crop != null) {
            logEntity.setCropEntity(crop);
        }

        logDao.save(logEntity);
    }



    @Override
    public void delete(String id) {
        Optional<LogEntity> byId = logDao.findById(id);
        if (byId.isPresent()) {
            logDao.deleteById(id);
        } else {
            throw new EntityNotFoundException("Log entity with ID " + id + " not found.");
        }
    }

    @Override
    public void update(String id, LogDTO dto) {
        Optional<LogEntity> byId = logDao.findById(id);

        if (byId.isPresent()) {
            LogEntity logEntity = byId.get();

            logEntity.setDate(dto.getDate());
            logEntity.setObservation(dto.getObservation());
            logEntity.setObservationImage(dto.getObservationImage());

            logDao.save(logEntity);
        } else {
            throw new EntityNotFoundException("Log entity with ID " + id + " not found.");
        }
    }

    @Override
    public LogDTO get(String id) {
        if (logDao.existsById(id)){
            LogEntity referenceById = logDao.getReferenceById(id);
            LogDTO logDTO = mapping.toLogDTO(referenceById);
            return logDTO;
        }

        return null;
    }

    public List<LogDTO> getAll() {
        List<LogEntity> logEntities = logDao.findAll();
        return logEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LogDTO convertToDTO(LogEntity logEntity) {
        LogDTO logDTO = new LogDTO();
        logDTO.setLogCode(logEntity.getLogCode());
        logDTO.setDate(logEntity.getDate());
        logDTO.setObservation(logEntity.getObservation());
        logDTO.setObservationImage(logEntity.getObservationImage());

        if (logEntity.getFieldEntity() != null) {
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(logEntity.getFieldEntity().getFieldCode());
            fieldDTO.setFieldName(logEntity.getFieldEntity().getFieldName());
            fieldDTO.setFieldLocation(logEntity.getFieldEntity().getFieldLocation());
            fieldDTO.setFieldSize(logEntity.getFieldEntity().getFieldSize());
            fieldDTO.setFieldImage(logEntity.getFieldEntity().getFieldImage());
            fieldDTO.setFieldImage2(logEntity.getFieldEntity().getFieldImage2());

            logDTO.setFieldDTO(fieldDTO);
        }

        if (logEntity.getCropEntity() != null) {
            CropDTO cropDTO = new CropDTO();
            cropDTO.setCropCode(logEntity.getCropEntity().getCropCode());
            cropDTO.setCommonName(logEntity.getCropEntity().getCommonName());
            cropDTO.setScientificName(logEntity.getCropEntity().getScientificName());
            cropDTO.setImage(logEntity.getCropEntity().getImage());
            cropDTO.setCategory(logEntity.getCropEntity().getCategory());
            cropDTO.setSeason(logEntity.getCropEntity().getSeason());
            logDTO.setCropDTO(cropDTO);
        }

        if (logEntity.getStaffEntity() != null) {
            StaffDTO staffDTO = new StaffDTO();
            StaffEntity staffEntity = logEntity.getStaffEntity();

            staffDTO.setId(staffEntity.getId());
            staffDTO.setFirstName(staffEntity.getFirstName());
            staffDTO.setLastName(staffEntity.getLastName());
            staffDTO.setDesignation(staffEntity.getDesignation());
            staffDTO.setGender(staffEntity.getGender());
            staffDTO.setJoinedDate(staffEntity.getJoinedDate());
            staffDTO.setDob(staffEntity.getDob());
            staffDTO.setAddress(staffEntity.getAddress());
            staffDTO.setContact(staffEntity.getContact());
            staffDTO.setEmail(staffEntity.getEmail());
            staffDTO.setRole(staffEntity.getRole());


            logDTO.setStaffDTO(staffDTO);
        }


        return logDTO;
    }
}
