package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.CropDTO;
import com.example.proposedcropmonitoringsystembackend.service.CropService;
import com.example.proposedcropmonitoringsystembackend.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
@CrossOrigin
public class CropController {

    @Autowired
    private CropService cropService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> saveCrop(@RequestBody CropDTO cropDTO) {

        String base64ProPic = "";

        //byte[] bytesProPic = cropDTO.getImage().getBytes();
        //base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

        //cropDTO.setImage(base64ProPic);
        cropService.save(cropDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(value = "/{cropId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> updateCrop(@RequestBody CropDTO cropDTO,@PathVariable("cropId") String cropId){


        String base64ProPic = "";

        //byte[] bytesProPic = cropDTO.getImage().getBytes();
        //base64ProPic = AppUtil.profilePicToBase64(bytesProPic);

        //cropDTO.setImage(base64ProPic);

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
