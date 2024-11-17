package com.example.proposedcropmonitoringsystembackend.dao;

import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropDao extends JpaRepository<CropEntity,String> {
    @Query("SELECT c FROM CropEntity c WHERE c.fieldEntity.fieldCode = :fieldCode")
    List<CropEntity> findCropsByFieldCode(@Param("fieldCode") String fieldCode);
}
