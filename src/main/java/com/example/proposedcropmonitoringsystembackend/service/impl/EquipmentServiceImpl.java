package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.EquipmentDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.EquipmentDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.EquipmentEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.EquipmentService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    }

    @Override
    public void update(String id, EquipmentDTO dto) {

    }

    @Override
    public EquipmentDTO get(String id) {
        return null;
    }

    @Override
    public List<EquipmentDTO> getAll() {
        return null;
    }
}
