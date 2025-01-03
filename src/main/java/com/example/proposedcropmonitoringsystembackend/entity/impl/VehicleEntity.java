package com.example.proposedcropmonitoringsystembackend.entity.impl;

import com.example.proposedcropmonitoringsystembackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VehicleEntity implements SuperEntity {
    @Id
    String vehicleCode;
    String licensePlateNum;
    String category;
    String fuelType;
    String status;
    String remarks;

    @ManyToOne
    @JoinColumn(name = "id")
    StaffEntity staff;
}
