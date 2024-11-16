package com.example.proposedcropmonitoringsystembackend.dto.impl;

import com.example.proposedcropmonitoringsystembackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldDTO implements SuperDTO {
    String fieldCode;
    String fieldName;
    String fieldLocation;
    double  fieldSize;
    String fieldImage;
}
