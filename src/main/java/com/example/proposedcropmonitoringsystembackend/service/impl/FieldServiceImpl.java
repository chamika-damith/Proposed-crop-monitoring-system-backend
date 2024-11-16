package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.FieldDao;
import com.example.proposedcropmonitoringsystembackend.dao.LogDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.LogDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.LogEntity;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;


    @Autowired
    private Mapping fieldMapping;
    @Override
    public void save(FieldDTO dto) {
        FieldEntity fieldEntity = fieldMapping.toFieldEntity(dto);

        fieldDao.save(fieldEntity);
    }

    @Override
    public void delete(String id) {
        Optional<FieldEntity> byId = fieldDao.findById(id);
        if (byId.isPresent()) {
            fieldDao.deleteById(id);
        }
    }

    @Override
    public void update(String id, FieldDTO dto) {
        Optional<FieldEntity> byId = fieldDao.findById(id);
        if (byId.isPresent()) {
            FieldEntity fieldEntity = byId.get();

            fieldEntity.setFieldName(dto.getFieldName());
            fieldEntity.setFieldLocation(dto.getFieldLocation());
            fieldEntity.setFieldSize(dto.getFieldSize());
            fieldEntity.setFieldImage(dto.getFieldImage());

            fieldDao.save(fieldEntity);
        }
    }

    @Override
    public FieldDTO get(String id) {
        if (fieldDao.existsById(id)){
            FieldEntity referenceById = fieldDao.getReferenceById(id);
            FieldDTO fieldDTO = fieldMapping.toFieldDTO(referenceById);

            return fieldDTO;
        }
        return null;
    }

    @Override
    public List<FieldDTO> getAll() {
        return fieldMapping.asFieldDTOList(fieldDao.findAll());
    }
}
