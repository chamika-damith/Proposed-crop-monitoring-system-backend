package com.example.proposedcropmonitoringsystembackend.dto.impl;

import com.example.proposedcropmonitoringsystembackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO implements SuperDTO {
    String vehicleCode;
    String licensePlateNum;
    String category;
    String fuelType;
    String status;
    String remarks;
    StaffDTO staff;
}
