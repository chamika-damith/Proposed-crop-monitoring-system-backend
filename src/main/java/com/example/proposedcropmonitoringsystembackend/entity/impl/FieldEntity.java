package com.example.proposedcropmonitoringsystembackend.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FieldEntity {
    @Id
    String fieldCode;
    String fieldName;
    String fieldLocation;
    double  fieldSize;
    @Column(columnDefinition = "LONGTEXT")
    String fieldImage;
    @OneToMany(mappedBy = "fieldEntity", cascade = CascadeType.ALL)
    List<CropEntity> cropEntityList;
}
