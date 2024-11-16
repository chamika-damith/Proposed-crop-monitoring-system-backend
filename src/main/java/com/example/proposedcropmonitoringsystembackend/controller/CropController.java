package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.service.CropService;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestBody CropDTO cropDTO) {

        String base64ProPic = "";

        byte[] bytesProPic = cropDTO.getImage().getBytes();
        base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

        FieldDTO fieldDTO = fieldService.get(cropDTO.getFieldDTO().getFieldCode());
        if (fieldDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cropDTO.setFieldDTO(fieldDTO);
        cropDTO.setImage(base64ProPic);
        cropService.save(cropDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(value = "/{cropId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCrop(@RequestBody CropDTO cropDTO,@PathVariable("cropId") String cropId){


        String base64ProPic = "";

        byte[] bytesProPic = cropDTO.getImage().getBytes();
        base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

        FieldDTO fieldDTO = fieldService.get(cropDTO.getFieldDTO().getFieldCode());
        if (fieldDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cropDTO.setImage(base64ProPic);

        cropService.update(cropId,cropDTO);

        return new ResponseEntity<>(HttpStatus.OK);
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
    public ResponseEntity<Void> deleteCrop(@PathVariable("cropdId") String cropId){
        cropService.delete(cropId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
