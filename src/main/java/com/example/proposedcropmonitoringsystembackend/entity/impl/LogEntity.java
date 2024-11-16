package com.example.proposedcropmonitoringsystembackend.entity.impl;

import com.example.proposedcropmonitoringsystembackend.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "field_code", referencedColumnName = "fieldCode")
    FieldEntity fieldEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crop_code", referencedColumnName = "cropCode")
    CropEntity cropEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    StaffEntity staffEntity;
}
