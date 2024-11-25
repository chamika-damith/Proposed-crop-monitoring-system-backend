package com.example.proposedcropmonitoringsystembackend.dto.impl;

import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.SuperDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO implements CustomStatus {
    String cropCode;
    String commonName;
    String scientificName;
    String image;
    String category;
    String season;
    FieldDTO fieldDTO;
}
