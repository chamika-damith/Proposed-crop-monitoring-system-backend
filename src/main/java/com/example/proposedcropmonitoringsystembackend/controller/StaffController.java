package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO){

        List<FieldDTO> fieldDTOS=new ArrayList<>();

        for (FieldDTO field : staffDTO.getFields()) {
            fieldDTOS.add(fieldService.get(field.getFieldCode()));
        }
        staffDTO.setFields(fieldDTOS);

        staffService.save(staffDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{staffId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@RequestBody StaffDTO staffDTO,@PathVariable("staffId") String staffId){

        List<FieldDTO> fieldDTOS=new ArrayList<>();

        for (FieldDTO field : staffDTO.getFields()) {
            fieldDTOS.add(fieldService.get(field.getFieldCode()));
        }
        staffDTO.setFields(fieldDTOS);

        staffService.update(staffId,staffDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{staffId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffDTO getStaff(@PathVariable("staffId") String staffId){
        return staffService.get(staffId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaffs(){
        return staffService.getAll();
    }

    @DeleteMapping(value = "/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String staffId){
        staffService.delete(staffId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
