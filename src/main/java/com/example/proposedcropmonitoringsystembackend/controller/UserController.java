package com.example.proposedcropmonitoringsystembackend.controller;

import com.example.proposedcropmonitoringsystembackend.dto.impl.UserDTO;
import com.example.proposedcropmonitoringsystembackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/signin")
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO){
        userService.save(userDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(value = "/{email}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO,@PathVariable("email") String email){
        userService.update(email,userDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable("email") String email){
        userService.delete(email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUser(@PathVariable("email") String email){
        return userService.get(email);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMINISTRATIVE')")
    public List<UserDTO> getAllUsers(){
        return userService.getAll();
    }
}
