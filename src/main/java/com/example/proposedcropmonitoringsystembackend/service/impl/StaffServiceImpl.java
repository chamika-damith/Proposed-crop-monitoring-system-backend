package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.StaffDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.StaffService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    }

    @Override
    public void update(String id, StaffDTO dto) {

    }

    @Override
    public StaffDTO get(String id) {
        return null;
    }

    @Override
    public List<StaffDTO> getAll() {
        return null;
    }
}
