package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.VehicleDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.VehicleDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.VehicleEntity;
import com.example.proposedcropmonitoringsystembackend.service.VehicleService;
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
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void save(VehicleDTO dto) {
        StaffEntity staffEntity = mapping.toStaffEntity(dto.getStaff());
        VehicleEntity vehicleEntity = mapping.toVehicleEntity(dto);
        vehicleEntity.setStaff(staffEntity);

        vehicleDao.save(vehicleEntity);
    }

    @Override
    public void delete(String id) {
        Optional<VehicleEntity> byId = vehicleDao.findById(id);
        if (byId.isPresent()) {
            vehicleDao.deleteById(id);
        } else {
            throw new EntityNotFoundException("Vehicle entity with ID " + id + " not found.");
        }
    }

    @Override
    public void update(String id, VehicleDTO dto) {
        Optional<VehicleEntity> byId = vehicleDao.findById(id);

        if (byId.isPresent()) {
            VehicleEntity vehicleEntity = byId.get();

            vehicleEntity.setCategory(dto.getCategory());
            vehicleEntity.setStatus(dto.getStatus());
            vehicleEntity.setRemarks(dto.getRemarks());
            vehicleEntity.setFuelType(dto.getFuelType());
            vehicleEntity.setLicensePlateNum(dto.getLicensePlateNum());

            if (dto.getStaff() != null) {
                StaffEntity staffEntity = mapping.toStaffEntity(dto.getStaff());
                vehicleEntity.setStaff(staffEntity);
            }

            vehicleDao.save(vehicleEntity);
        } else {
            throw new EntityNotFoundException("Vehicle entity with ID " + id + " not found.");
        }
    }

    @Override
    public VehicleDTO get(String id) {
        if (vehicleDao.existsById(id)) {
            VehicleEntity vehicleEntity = vehicleDao.getReferenceById(id);

            VehicleDTO vehicleDTO = mapping.toVehicleDTO(vehicleEntity);
            StaffDTO staffDTO = mapping.toStaffDTO(vehicleEntity.getStaff());
            vehicleDTO.setStaff(staffDTO);

            return vehicleDTO;
        } else {
            throw new EntityNotFoundException("Vehicle entity with ID " + id + " not found.");
        }
    }

    @Override
    public List<VehicleDTO> getAll() {
        List<VehicleEntity> vehicleEntities = vehicleDao.findAll();

        return vehicleEntities.stream().map(vehicleEntity -> {
            VehicleDTO vehicleDTO = mapping.toVehicleDTO(vehicleEntity);
            StaffDTO staffDTO = mapping.toStaffDTO(vehicleEntity.getStaff());
            vehicleDTO.setStaff(staffDTO);
            return vehicleDTO;
        }).collect(Collectors.toList());
    }
}
