package com.example.proposedcropmonitoringsystembackend.service;

import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;

import java.util.List;

public interface StaffService extends CRUDUtil<StaffDTO>{
    List<StaffDTO> getStaffByField(String fieldCode);
}
