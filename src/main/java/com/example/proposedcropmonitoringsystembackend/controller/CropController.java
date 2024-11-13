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

@RestController
@RequestMapping("api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestParam("cropCode") String cropCode,
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestParam("image") MultipartFile image,
            @RequestParam("fieldCode") String fieldCode) {

        String base64ProPic = "";

        try {
            byte[] bytesProPic = image.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

            FieldDTO fieldDTO = fieldService.get(fieldCode);
            if (fieldDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            CropDTO cropDTO = new CropDTO(cropCode, commonName, scientificName, base64ProPic, category, season, fieldDTO);

            cropService.save(cropDTO);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCrop(
            @RequestParam("cropCode") String cropCode,
            @RequestParam("commonName") String commonName,
            @RequestParam("scientificName") String scientificName,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestParam("image") MultipartFile image,
            @RequestParam("fieldCode") String fieldCode){


        String base64ProPic = "";

        try {
            byte[] bytesProPic = image.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

            FieldDTO fieldDTO = fieldService.get(fieldCode);
            if (fieldDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }


            CropDTO cropDTO = new CropDTO(cropCode, commonName, scientificName, base64ProPic, category, season, fieldDTO);

            cropService.update(cropCode,cropDTO);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        return new ResponseEntity<>(HttpStatus.OK);
    }


}
