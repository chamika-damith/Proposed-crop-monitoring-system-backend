package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.customstatuscode.SuccessStatus;
import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.impl.EquipmentDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.VehicleDTO;
import com.example.proposedcropmonitoringsystembackend.service.EquipmentService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import com.example.proposedcropmonitoringsystembackend.util.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public CustomStatus saveEquipment(@RequestBody EquipmentDTO equipmentDTO){

        CustomStatus customStatus = ValidateData.validateEquipmentDTO(equipmentDTO);
        if(customStatus != null){
            return customStatus;
        }
        equipmentService.save(equipmentDTO);
        return new SuccessStatus( HttpStatus.CREATED.value(),"Equipment saved successfully!");
    }

    @PutMapping(value = "/{equipmentCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public CustomStatus updateEquipment(@RequestBody EquipmentDTO equipmentDTO,@PathVariable("equipmentCode") String equipmentCode){

        CustomStatus customStatus = ValidateData.validateEquipmentDTO(equipmentDTO);
        if(customStatus != null){
            return customStatus;
        }
        equipmentService.update(equipmentCode,equipmentDTO);

        return new SuccessStatus( HttpStatus.CREATED.value(),"Equipment update successfully!");
    }

    @GetMapping(value = "/{equipmentCode}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDTO getEquipment(@PathVariable("equipmentCode") String equipmentCode ){
        return equipmentService.get(equipmentCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipment(){
        return equipmentService.getAll();
    }

    @DeleteMapping(value = "/{equipmentCode}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentCode") String equipmentCode){
        equipmentService.delete(equipmentCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/generateId")
    public String generateEquipmentId(){
        return AppUtil.generateEquipmentId();
    }
}
