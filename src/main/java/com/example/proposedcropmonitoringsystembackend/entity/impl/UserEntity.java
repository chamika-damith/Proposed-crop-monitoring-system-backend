package com.example.proposedcropmonitoringsystembackend.entity.impl;

import com.example.proposedcropmonitoringsystembackend.entity.SuperEntity;
import com.example.proposedcropmonitoringsystembackend.util.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity implements SuperEntity {
    @Id
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role userRole;
}
