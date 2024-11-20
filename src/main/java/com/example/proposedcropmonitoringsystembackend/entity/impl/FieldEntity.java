package com.example.proposedcropmonitoringsystembackend.entity.impl;

import com.example.proposedcropmonitoringsystembackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FieldEntity implements SuperEntity {
    @Id
    String fieldCode;
    String fieldName;
    String fieldLocation;
    double fieldSize;

    @Column(columnDefinition = "LONGTEXT")
    String fieldImage;

    @OneToMany(mappedBy = "fieldEntity", cascade = CascadeType.ALL)
    List<CropEntity> cropEntityList;

    @ManyToMany(mappedBy = "fields", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<StaffEntity> staffEntityList;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    List<EquipmentEntity> equipmentEntityList;

    @OneToOne(mappedBy = "fieldEntity", cascade = CascadeType.ALL)
    LogEntity log;
}