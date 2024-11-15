package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.EquipmentDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.EquipmentDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.EquipmentEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.EquipmentService;
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
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void save(EquipmentDTO dto) {
        StaffEntity staffEntity = mapping.toStaffEntity(dto.getStaff());
        FieldEntity fieldEntity = mapping.toFieldEntity(dto.getField());
        EquipmentEntity equipmentEntity = mapping.toEquipmentEntity(dto);
        equipmentEntity.setStaff(staffEntity);
        equipmentEntity.setField(fieldEntity);

        equipmentDao.save(equipmentEntity);
    }

    @Override
    public void delete(String id) {
        Optional<EquipmentEntity> byId = equipmentDao.findById(id);
        if (byId.isPresent()) {
            equipmentDao.deleteById(id);
        } else {
            throw new EntityNotFoundException("Equipment entity with ID " + id + " not found.");
        }
    }

    @Override
    public void update(String id, EquipmentDTO dto) {
        Optional<EquipmentEntity> byId = equipmentDao.findById(id);

        if (byId.isPresent()) {
            EquipmentEntity equipmentEntity = byId.get();

            equipmentEntity.setName(dto.getName());
            equipmentEntity.setEquipmentType(dto.getEquipmentType());
            equipmentEntity.setStatus(dto.getStatus());

            if (dto.getStaff() != null) {
                StaffEntity staffEntity = mapping.toStaffEntity(dto.getStaff());
                equipmentEntity.setStaff(staffEntity);
            }

            if (dto.getField() != null) {
                FieldEntity fieldEntity = mapping.toFieldEntity(dto.getField());
                equipmentEntity.setField(fieldEntity);
            }

            equipmentDao.save(equipmentEntity);
        } else {
            throw new EntityNotFoundException("Equipment entity with ID " + id + " not found.");
        }
    }

    @Override
    public EquipmentDTO get(String id) {
        if (equipmentDao.existsById(id)) {
            EquipmentEntity equipmentEntity = equipmentDao.getReferenceById(id);
            EquipmentDTO equipmentDTO = mapping.toEquipmentDTO(equipmentEntity);

            if (equipmentEntity.getStaff() != null) {
                StaffDTO staffDTO = mapping.toStaffDTO(equipmentEntity.getStaff());
                equipmentDTO.setStaff(staffDTO);
            }

            if (equipmentEntity.getField() != null) {
                FieldDTO fieldDTO = mapping.toFieldDTO(equipmentEntity.getField());
                equipmentDTO.setField(fieldDTO);
            }

            return equipmentDTO;
        }
        return null;
    }

    @Override
    public List<EquipmentDTO> getAll() {
        List<EquipmentEntity> equipmentEntities = equipmentDao.findAll();

        return equipmentEntities.stream().map(equipmentEntity -> {
            EquipmentDTO equipmentDTO = mapping.toEquipmentDTO(equipmentEntity);

            if (equipmentEntity.getStaff() != null) {
                StaffDTO staffDTO = mapping.toStaffDTO(equipmentEntity.getStaff());
                equipmentDTO.setStaff(staffDTO);
            }

            if (equipmentEntity.getField() != null) {
                FieldDTO fieldDTO = mapping.toFieldDTO(equipmentEntity.getField());
                equipmentDTO.setField(fieldDTO);
            }

            return equipmentDTO;
        }).collect(Collectors.toList());
    }
}
