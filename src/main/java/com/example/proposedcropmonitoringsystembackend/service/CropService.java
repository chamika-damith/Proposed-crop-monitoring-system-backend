package com.example.proposedcropmonitoringsystembackend.service;

import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;

import java.util.List;

public interface CropService extends CRUDUtil<CropDTO>{
    List<CropDTO> getCropsByField(String fieldCode);
}
