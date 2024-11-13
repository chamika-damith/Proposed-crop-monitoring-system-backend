package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.CropDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.service.CropService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            CropEntity cropEntity = byId.get();

            cropEntity.setCategory(dto.getCategory());
            cropEntity.setImage(dto.getImage());
            cropEntity.setSeason(dto.getSeason());
            cropEntity.setCommonName(dto.getCommonName());
            cropEntity.setScientificName(dto.getScientificName());

            if (dto.getFieldDTO() != null) {
                FieldEntity fieldEntity = mapping.toFieldEntity(dto.getFieldDTO());
                cropEntity.setFieldEntity(fieldEntity);
            }

            cropDao.save(cropEntity);
        } else {
            throw new EntityNotFoundException("Crop entity with ID " + id + " not found.");
        }
    }


    @Override
    public CropDTO get(String id) {
        if (cropDao.existsById(id)){
            CropEntity cropEntityById = cropDao.getReferenceById(id);
            FieldDTO fieldDTO = mapping.toFieldDTO(cropEntityById.getFieldEntity());
            CropDTO cropDTO = mapping.toCropDTO(cropEntityById);
            cropDTO.setFieldDTO(fieldDTO);

            return cropDTO;
        }

        return null;
    }

    @Override
    public List<CropDTO> getAll() {
        List<CropEntity> cropEntities = cropDao.findAll();

        List<CropDTO> cropDTOList = cropEntities.stream().map(cropEntity -> {
            FieldDTO fieldDTO = mapping.toFieldDTO(cropEntity.getFieldEntity());
            CropDTO cropDTO = mapping.toCropDTO(cropEntity);
            cropDTO.setFieldDTO(fieldDTO);
            return cropDTO;
        }).collect(Collectors.toList());

        return cropDTOList;
    }

}
