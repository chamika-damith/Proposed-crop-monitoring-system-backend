package com.example.proposedcropmonitoringsystembackend.entity.impl;

import com.example.proposedcropmonitoringsystembackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LogEntity implements SuperEntity {
    @Id
    String logCode;
    Date date;
    String observation;
    @Column(columnDefinition = "LONGTEXT")
    String observationImage;

    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL)
    List<FieldEntity> fieldEntityList;

    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL)
    List<CropEntity> cropEntityList;

    @OneToMany(mappedBy = "log",cascade = CascadeType.ALL)
    List<StaffEntity> staffEntityList;
}
