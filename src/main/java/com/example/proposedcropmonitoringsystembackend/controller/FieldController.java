package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.customstatuscode.SuccessStatus;
import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.impl.FieldDTO;
import com.example.proposedcropmonitoringsystembackend.service.FieldService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import com.example.proposedcropmonitoringsystembackend.util.ValidateData;
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
    public CustomStatus saveField(@RequestBody FieldDTO fieldDTO) {

        CustomStatus customStatus = ValidateData.validateFieldDTO(fieldDTO);
        if (customStatus != null){
            return customStatus;
        }
        fieldService.save(fieldDTO);



        return new SuccessStatus(HttpStatus.CREATED.value(),"Crop saved successfully!");
    }

    @PutMapping(value = "/{fieldId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public CustomStatus updateField(@RequestBody FieldDTO fieldDTO,@PathVariable("fieldId") String fieldId){

        CustomStatus customStatus = ValidateData.validateFieldDTO(fieldDTO);
        if (customStatus != null){
            return customStatus;
        }
        fieldService.update(fieldId,fieldDTO);

        return new SuccessStatus(HttpStatus.OK.value(),"Crop update successfully!");

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
