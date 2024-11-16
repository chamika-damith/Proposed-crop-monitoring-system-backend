package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dao.CropDao;
import com.example.proposedcropmonitoringsystembackend.dao.FieldDao;
import com.example.proposedcropmonitoringsystembackend.dao.StaffDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.LogDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.CropEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.FieldEntity;
import com.example.proposedcropmonitoringsystembackend.entity.impl.StaffEntity;
import com.example.proposedcropmonitoringsystembackend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/log")
public class LogController {

    @Autowired
    private LogService logService;



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestBody LogDTO logDTO) {
        logService.save(logDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{logId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateLog(@RequestBody LogDTO logDTO, @PathVariable("logId") String logId) {
        LogDTO existingLog = logService.get(logId);
        if (existingLog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logService.update(logId, logDTO);
        return new ResponseEntity<>(HttpStatus.OK);
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
        return logService.getAll();
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
}

