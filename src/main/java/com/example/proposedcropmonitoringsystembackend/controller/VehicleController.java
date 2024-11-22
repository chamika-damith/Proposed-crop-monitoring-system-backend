package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.StaffDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.VehicleDTO;
import com.example.proposedcropmonitoringsystembackend.service.VehicleService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public ResponseEntity<Void> saveVehicle(@RequestBody VehicleDTO vehicleDTO){

        vehicleService.save(vehicleDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{vehicleCode}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public ResponseEntity<Void> updateVehicle(@RequestBody VehicleDTO vehicleDTO,@PathVariable("vehicleCode") String vehicleCode){

        vehicleService.update(vehicleCode,vehicleDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{vehicleCode}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDTO getVehicle(@PathVariable("vehicleCode") String vehicleCode){
        return vehicleService.get(vehicleCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicle(){
        return vehicleService.getAll();
    }

    @DeleteMapping(value = "/{vehicleCode}")
    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    public ResponseEntity<Void> deleteCrop(@PathVariable("vehicleCode") String vehicleCode){
        vehicleService.delete(vehicleCode);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generateId")
    public String generateVehicleId(){
        return AppUtil.generateVehicleId();
    }
}
