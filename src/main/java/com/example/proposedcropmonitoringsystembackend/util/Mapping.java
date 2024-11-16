package com.example.proposedcropmonitoringsystembackend.util;

import com.example.proposedcropmonitoringsystembackend.dao.CropDao;
import com.example.proposedcropmonitoringsystembackend.dao.StaffDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.*;
import com.example.proposedcropmonitoringsystembackend.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //for field mapping
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }
    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }
    public List<FieldDTO> asFieldDTOList(List<FieldEntity> fieldEntities) {
        return modelMapper.map(fieldEntities, new TypeToken<List<FieldDTO>>() {}.getType());
    }
    public List<FieldEntity> asFieldEntityList(List<FieldDTO> fieldDTOList) {
        return modelMapper.map(fieldDTOList, new TypeToken<List<FieldEntity>>() {}.getType());
    }

    public List<FieldDTO> toFieldEntityList(List<FieldEntity> fieldEntityList) {
        // Convert to a regular List
        List<FieldEntity> regularList = fieldEntityList.stream().collect(Collectors.toList());
        return modelMapper.map(regularList, new TypeToken<List<FieldDTO>>() {}.getType());
    }


    //for crop mapping
    public CropEntity toCropEntity(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, CropEntity.class);
    }
    public CropDTO toCropDTO(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDTO.class);
    }
    public List<CropDTO> asCropDTOList(List<CropEntity> cropEntityList) {
        // Convert to a regular List
        List<CropEntity> regularList = cropEntityList.stream().collect(Collectors.toList());
        return modelMapper.map(regularList, new TypeToken<List<CropDTO>>() {}.getType());
    }



    //for staff mapping
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }
    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }
    public List<StaffDTO> asStaffDTOList(List<StaffEntity> staffEntityList) {
        // Convert to a regular List
        List<StaffEntity> regularList = staffEntityList.stream().collect(Collectors.toList());
        return modelMapper.map(regularList, new TypeToken<List<StaffDTO>>() {}.getType());
    }

    //for vehicle mapping
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }
    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }
    public List<VehicleDTO> asVehicleDTOList(List<VehicleEntity> vehicleEntities) {
        return modelMapper.map(vehicleEntities, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    //for equipment mapping
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }
    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
    }
    public List<EquipmentDTO> asEquipmentDTOList(List<EquipmentEntity> equipmentEntityList) {
        return modelMapper.map(equipmentEntityList, new TypeToken<List<EquipmentDTO>>() {}.getType());
    }

    //for log mapping
    public LogEntity toLogEntity(LogDTO logDTO) {
        return modelMapper.map(logDTO, LogEntity.class);
    }
    public LogDTO toLogDTO(LogEntity logEntity) {
        return modelMapper.map(logEntity, LogDTO.class);
    }

    public List<LogDTO> asLogDTOList(List<LogEntity> logEntityList) {
        return modelMapper.map(logEntityList, new TypeToken<List<LogDTO>>() {}.getType());
    }
}
