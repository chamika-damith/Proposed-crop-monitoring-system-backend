package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.LogDao;
import com.example.proposedcropmonitoringsystembackend.dao.StaffDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.LogDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.LogEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.StaffService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;


    @Autowired
    private Mapping mapping;

    @Override
    public void save(StaffDTO dto) {
        List<FieldEntity> fieldEntityList = mapping.asFieldEntityList(dto.getFields());
        StaffEntity staffEntity = mapping.toStaffEntity(dto);

        staffEntity.setFields(fieldEntityList);

        staffDao.save(staffEntity);
    }

    @Override
    public void delete(String id) {
        Optional<StaffEntity> byId = staffDao.findById(id);
        if (byId.isPresent()) {
            staffDao.deleteById(id);
        } else {
            throw new EntityNotFoundException("Staff entity with ID " + id + " not found.");
        }
    }

    @Override
    public void update(String id, StaffDTO dto) {
        Optional<StaffEntity> byId = staffDao.findById(id);

        if (byId.isPresent()) {
            StaffEntity staffEntity = byId.get();

            staffEntity.setFirstName(dto.getFirstName());
            staffEntity.setLastName(dto.getLastName());
            staffEntity.setDesignation(dto.getDesignation());
            staffEntity.setGender(dto.getGender());
            staffEntity.setJoinedDate(dto.getJoinedDate());
            staffEntity.setDob(dto.getDob());
            staffEntity.setAddress(dto.getAddress());
            staffEntity.setContact(dto.getContact());
            staffEntity.setEmail(dto.getEmail());
            staffEntity.setRole(dto.getRole());

            if (dto.getFields() != null) {
                List<FieldEntity> fieldEntityList = mapping.asFieldEntityList(dto.getFields());
                staffEntity.setFields(fieldEntityList);
            }

            staffDao.save(staffEntity);
        } else {
            throw new EntityNotFoundException("Staff entity with ID " + id + " not found.");
        }
    }

    @Override
    public StaffDTO get(String id) {
        if (staffDao.existsById(id)) {
            StaffEntity staffEntityById = staffDao.getReferenceById(id);

            StaffDTO staffDTO = mapping.toStaffDTO(staffEntityById);
            List<FieldDTO> fieldDTOList = mapping.asFieldDTOList(staffEntityById.getFields());
            staffDTO.setFields(fieldDTOList);

            return staffDTO;
        } else {
            throw new EntityNotFoundException("Staff entity with ID " + id + " not found.");
        }
    }

    @Override
    public List<StaffDTO> getAll() {
        List<StaffEntity> staffEntities = staffDao.findAll();

        return staffEntities.stream().map(staffEntity -> {
            StaffDTO staffDTO = mapping.toStaffDTO(staffEntity);
            List<FieldDTO> fieldDTOList = mapping.asFieldDTOList(staffEntity.getFields());
            staffDTO.setFields(fieldDTOList);
            return staffDTO;
        }).collect(Collectors.toList());
    }
}
