package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
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
@RequestMapping("api/v1/fields")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveField(@RequestParam("fieldCode") String fieldCode,
                                          @RequestParam("fieldName") String fieldName,
                                          @RequestParam("fieldLocation") String fieldLocation,
                                          @RequestParam("fieldSize") String fieldSize,
                                          @RequestParam("fieldImage") MultipartFile fieldImage) {

        // profilePic ----> Base64
        String base64ProPic = "";

        try {
            byte [] bytesProPic = fieldImage.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

            double parsedFieldSize = Double.parseDouble(fieldSize);

            FieldDTO fieldDTO = new FieldDTO(fieldCode,fieldName,fieldLocation,parsedFieldSize,base64ProPic);
            fieldService.save(fieldDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateField(@RequestParam("fieldCode") String fieldCode,
                                            @RequestParam("fieldName") String fieldName,
                                            @RequestParam("fieldLocation") String fieldLocation,
                                            @RequestParam("fieldSize") String fieldSize,
                                            @RequestParam("fieldImage") MultipartFile fieldImage){

        String base64ProPic = "";

        try {
            byte [] bytesProPic = fieldImage.getBytes();
            base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

            double parsedFieldSize = Double.parseDouble(fieldSize);

            FieldDTO fieldDTO = new FieldDTO(fieldCode,fieldName,fieldLocation,parsedFieldSize,base64ProPic);
            fieldService.update(fieldCode,fieldDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<>(HttpStatus.OK);

    }


    @GetMapping(value = "/{fieldId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldDTO getField(@PathVariable("fieldId") String fieldId){
        return fieldService.get(fieldId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields(){
        return fieldService.getAll();
    }

    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldId") String fieldId){
        fieldService.delete(fieldId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
