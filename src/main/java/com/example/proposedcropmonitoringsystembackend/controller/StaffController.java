package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.customstatuscode.SuccessStatus;
import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.service.StaffService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import com.example.proposedcropmonitoringsystembackend.util.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public CustomStatus saveStaff(@RequestBody StaffDTO staffDTO){

        CustomStatus customStatus = ValidateData.validateStaffDTO(staffDTO);
        if (customStatus != null){
            return customStatus;
        }

        List<FieldDTO> fieldDTOS=staffDTO.getFields().stream()
                        .map(field ->fieldService.get(field.getFieldCode()))
                        .collect(Collectors.toList());

        staffDTO.setFields(fieldDTOS);

        staffService.save(staffDTO);

        return new SuccessStatus( HttpStatus.CREATED.value(),"staff saved successfully!");
    }

    @PutMapping(value = "/{staffId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public CustomStatus updateStaff(@RequestBody StaffDTO staffDTO,@PathVariable("staffId") String staffId){

        CustomStatus customStatus = ValidateData.validateStaffDTO(staffDTO);
        if (customStatus != null){
            return customStatus;
        }

        List<FieldDTO> fieldDTOS=staffDTO.getFields().stream()
                .map(field ->fieldService.get(field.getFieldCode()))
                .collect(Collectors.toList());

        staffDTO.setFields(fieldDTOS);

        staffService.update(staffId,staffDTO);

        return new SuccessStatus( HttpStatus.OK.value(),"staff Update successfully!");
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
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public ResponseEntity<Void> deleteStaff(@PathVariable("staffId") String staffId){
        staffService.delete(staffId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generateId")
    public String generateStaffId(){
        return AppUtil.generateStaffId();
    }

    @GetMapping(value = "/field/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getStaffByField(@PathVariable("fieldCode") String fieldCode) {
        return staffService.getStaffByField(fieldCode);
    }

}
