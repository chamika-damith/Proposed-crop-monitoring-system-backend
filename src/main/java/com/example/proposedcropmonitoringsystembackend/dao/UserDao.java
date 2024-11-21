package com.example.proposedcropmonitoringsystembackend.dao;

import com.example.proposedcropmonitoringsystembackend.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity,String> {
}
