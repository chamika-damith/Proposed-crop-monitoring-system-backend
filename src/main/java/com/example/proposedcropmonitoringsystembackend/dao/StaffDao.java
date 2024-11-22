package com.example.proposedcropmonitoringsystembackend.dao;

import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
    @Query("SELECT s FROM StaffEntity s JOIN s.fields f WHERE f.fieldCode = :fieldCode")
    List<StaffEntity> findStaffByFieldCode(@Param("fieldCode") String fieldCode);
}
