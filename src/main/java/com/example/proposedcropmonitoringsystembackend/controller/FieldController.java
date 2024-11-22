package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> saveField(@RequestBody FieldDTO fieldDTO) {

        String base64ProPic = "";

        byte [] bytesProPic = fieldDTO.getFieldImage().getBytes();
        base64ProPic = AppUtil.profilePicToBase64(bytesProPic);


        //fieldDTO.setFieldImage(base64ProPic);
        fieldService.save(fieldDTO);



        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{fieldId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> updateField(@RequestBody FieldDTO fieldDTO,@PathVariable("fieldId") String fieldId){

        String base64ProPic = "";

        byte [] bytesProPic = fieldDTO.getFieldImage().getBytes();
        base64ProPic = AppUtil.profilePicToBase64(bytesProPic);


        //fieldDTO.setFieldImage(base64ProPic);
        fieldService.update(fieldId,fieldDTO);

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
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldId") String fieldId){
        fieldService.delete(fieldId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generateId")
    public String generateFieldId(){
        return AppUtil.generateFieldId();
    }
}
