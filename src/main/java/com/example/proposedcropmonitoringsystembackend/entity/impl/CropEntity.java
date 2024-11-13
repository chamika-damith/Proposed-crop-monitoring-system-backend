package com.example.proposedcropmonitoringsystembackend.entity.impl;

import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CropEntity {
    @Id
    String cropCode;
    String commonName;
    String scientificName;
    @Column(columnDefinition = "LONGTEXT")
    String image;
    String category;
    String season;

    @ManyToOne
    @JoinColumn(name = "crop_code" , nullable = false)
    FieldEntity fieldEntity;
}
