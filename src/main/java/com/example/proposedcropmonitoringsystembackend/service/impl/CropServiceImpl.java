package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.CropDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.service.CropService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void save(CropDTO dto) {
        FieldEntity fieldEntity = mapping.toFieldEntity(dto.getFieldDTO());
        CropEntity cropEntity = mapping.toCropEntity(dto);

        cropEntity.setFieldEntity(fieldEntity);

        cropDao.save(cropEntity);
    }


    @Override
    public void delete(String id) {
        Optional<CropEntity> byId = cropDao.findById(id);
        if (byId.isPresent()) {
            cropDao.deleteById(id);
        }
    }

    @Override
    public void update(String id, CropDTO dto) {
        Optional<CropEntity> byId = cropDao.findById(id);
        if (byId.isPresent()) {
            byId.get().setCategory(dto.getCategory());
            byId.get().setImage(dto.getImage());
            byId.get().setSeason(dto.getSeason());
            byId.get().setCommonName(dto.getCommonName());
            byId.get().setScientificName(dto.getScientificName());
        }
    }

    @Override
    public CropDTO get(String id) {
        if (cropDao.existsById(id)){
            CropEntity referenceById = cropDao.getReferenceById(id);
            return mapping.toCropDTO(referenceById);
        }

        return null;
    }

    @Override
    public List<CropDTO> getAll() {
        return mapping.asCropDTOList(cropDao.findAll());
    }
}
