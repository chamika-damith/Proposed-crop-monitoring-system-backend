package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.VehicleDao;
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
        return null;
    }

    @Override
    public List<VehicleDTO> getAll() {
        return null;
    }
}
