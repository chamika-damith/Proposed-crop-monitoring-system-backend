package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.EquipmentDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.VehicleDTO;
import com.example.proposedcropmonitoringsystembackend.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO){

        equipmentService.save(equipmentDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{equipmentCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEquipment(@RequestBody EquipmentDTO equipmentDTO,@PathVariable("equipmentCode") String equipmentCode){

        equipmentService.update(equipmentCode,equipmentDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{equipmentCode}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDTO getEquipment(@PathVariable("equipmentCode") String equipmentCode ){
        return equipmentService.get(equipmentCode);
    }
}
