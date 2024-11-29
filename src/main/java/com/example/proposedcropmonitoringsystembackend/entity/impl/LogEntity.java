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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "field_code")
    FieldEntity fieldEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "crop_code")
    CropEntity cropEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    StaffEntity staffEntity;
}
