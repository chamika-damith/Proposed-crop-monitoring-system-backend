package com.example.proposedcropmonitoringsystembackend.dto.impl;

import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO implements CustomStatus {
    String logCode;
    Date date;
    String observation;
    String observationImage;
    FieldDTO fieldDTO;
    StaffDTO staffDTO;
    CropDTO cropDTO;
}
