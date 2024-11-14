package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaff(@RequestBody StaffDTO staffDTO){

        staffService.save(staffDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{staffId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateStaff(@RequestBody StaffDTO staffDTO,@PathVariable("staffId") String staffId){

        staffService.update(staffId,staffDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
