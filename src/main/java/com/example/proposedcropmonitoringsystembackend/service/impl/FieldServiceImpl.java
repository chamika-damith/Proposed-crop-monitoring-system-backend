package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.FieldDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
        fieldDao.save(fieldMapping.toFieldEntity(dto));
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, FieldDTO dto) {
        Optional<FieldEntity> byId = fieldDao.findById(id);
        if (byId.isPresent()) {
            byId.get().setFieldName(dto.getFieldName());
            byId.get().setFieldLocation(dto.getFieldLocation());
            byId.get().setFieldSize(dto.getFieldSize());
            byId.get().setFieldImage(dto.getFieldImage());
        }
    }

    @Override
    public FieldDTO get(String id) {
        if (fieldDao.existsById(id)){
            FieldEntity referenceById = fieldDao.getReferenceById(id);
            return fieldMapping.toFieldDTO(referenceById);
        }
        return null;
    }

    @Override
    public List<FieldDTO> getAll() {
        return null;
    }
}
