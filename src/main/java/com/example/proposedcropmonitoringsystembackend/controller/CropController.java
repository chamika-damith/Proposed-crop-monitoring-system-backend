package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.customstatuscode.ErrorStatus;
import com.example.proposedcropmonitoringsystembackend.customstatuscode.SuccessStatus;
import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.service.CropService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import com.example.proposedcropmonitoringsystembackend.util.Regex;
import com.example.proposedcropmonitoringsystembackend.util.ValidateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin
public class CropController {

    @Autowired
    private CropService cropService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public CustomStatus saveCrop(@RequestBody CropDTO cropDTO) {
        CustomStatus customStatus = ValidateData.validateCropDTO(cropDTO);
        if (customStatus != null) {
            return customStatus;
        }

        cropService.save(cropDTO);
        return new SuccessStatus( HttpStatus.CREATED.value(),"Crop saved successfully!");
    }


    @PutMapping(value = "/{cropId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public CustomStatus updateCrop(@RequestBody CropDTO cropDTO,@PathVariable("cropId") String cropId){

        CustomStatus customStatus = ValidateData.validateCropDTO(cropDTO);
        if (customStatus != null) {
            return customStatus;
        }

        cropService.update(cropId,cropDTO);

        return new SuccessStatus( HttpStatus.OK.value(),"Crop updated successfully!");
    }


    @GetMapping(value = "/{cropdId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public CropDTO getCrop(@PathVariable("cropdId") String cropId){
        return cropService.get(cropId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops(){
        return cropService.getAll();
    }

    @DeleteMapping(value = "/{cropdId}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropdId") String cropId){
        cropService.delete(cropId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generateId")
    public String generateCropId(){
        return AppUtil.generateCropId();
    }

    @GetMapping(value = "/field/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getCropsByField(@PathVariable("fieldCode") String fieldCode) {
        System.out.println(fieldCode);
        return cropService.getCropsByField(fieldCode);
    }
}
