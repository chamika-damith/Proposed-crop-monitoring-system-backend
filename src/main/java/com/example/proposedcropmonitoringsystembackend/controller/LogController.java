package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.customstatuscode.ErrorStatus;
import com.example.proposedcropmonitoringsystembackend.customstatuscode.SuccessStatus;
import com.example.proposedcropmonitoringsystembackend.dao.CropDao;
import com.example.proposedcropmonitoringsystembackend.dao.FieldDao;
import com.example.proposedcropmonitoringsystembackend.dao.StaffDao;
import com.example.proposedcropmonitoringsystembackend.dto.CustomStatus;
import com.example.proposedcropmonitoringsystembackend.dto.impl.LogDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.LogService;
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
@RequestMapping("api/v1/log")
@CrossOrigin
public class LogController {

    @Autowired
    private LogService logService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MANAGER', 'SCIENTIST')")
    public CustomStatus saveLog(@RequestBody LogDTO logDTO) {

        CustomStatus customStatus = ValidateData.validateLogDTO(logDTO);
        if (customStatus != null) {
            return customStatus;
        }
        logService.save(logDTO);
        return new SuccessStatus(HttpStatus.CREATED.value(),"log saved successfully!");
    }

    @PutMapping(value = "/{logId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomStatus updateLog(@RequestBody LogDTO logDTO, @PathVariable("logId") String logId) {
        CustomStatus customStatus = ValidateData.validateLogDTO(logDTO);
        if (customStatus != null) {
            return customStatus;
        }
        LogDTO existingLog = logService.get(logId);
        if (existingLog == null) {
            return new ErrorStatus(HttpStatus.NOT_FOUND.value(), "Log not found");
        }
        logService.update(logId, logDTO);
        return new SuccessStatus(HttpStatus.CREATED.value(),"log saved successfully!");
    }

    @GetMapping(value = "/{logId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogDTO> getLog(@PathVariable("logId") String logId) {
        LogDTO logDTO = logService.get(logId);
        if (logDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(logDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDTO> getAllLogs() {
        List<LogDTO> all = logService.getAll();
        return all;
    }

    @DeleteMapping(value = "/{logId}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logId") String logId) {
        LogDTO existingLog = logService.get(logId);
        if (existingLog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logService.delete(logId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/generateId")
    public String generateFieldId(){
        return AppUtil.generateLogId();
    }
}

