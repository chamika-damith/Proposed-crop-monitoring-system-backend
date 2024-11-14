package com.example.proposedcropmonitoringsystembackend.dao;

import com.example.proposedcropmonitoringsystembackend.entity.impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDao extends JpaRepository<VehicleEntity,String> {
}
