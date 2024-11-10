package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.FieldDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    }

    @Override
    public FieldDTO get(String id) {
        return null;
    }

    @Override
    public List<FieldDTO> getAll() {
        return null;
    }
}
