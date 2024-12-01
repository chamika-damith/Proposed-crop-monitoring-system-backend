package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.UserDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.UserDTO;
import com.example.proposedcropmonitoringsystembackend.entity.impl.UserEntity;
import com.example.proposedcropmonitoringsystembackend.exception.UserNotFoundException;
import com.example.proposedcropmonitoringsystembackend.service.UserService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void save(UserDTO dto) {
        userDao.save(mapping.toUserEntity(dto));
    }

    @Override
    public void delete(String id) {
        Optional<UserEntity> byId = userDao.findById(id);
        if (byId.isPresent()) {
            userDao.deleteById(id);
        }
    }

    @Override
    public void update(String id, UserDTO dto) {
        Optional<UserEntity> byId = userDao.findById(id);
        if (byId.isPresent()) {
            UserEntity userEntity = byId.get();
            userEntity.setPassword(dto.getPassword());
            userEntity.setUserRole(dto.getUserRole());
        }
    }

    @Override
    public UserDTO get(String id) {
        if (userDao.existsById(id)){
            return mapping.toUserDTO(userDao.getReferenceById(id));
        }
        return null;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> all = userDao.findAll();

        return all.stream().map(user ->{
            UserDTO userDTO = mapping.toUserDTO(user);
            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userName ->
                (UserDetails) userDao.findByEmail(userName)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found"));
    }

    @Override
    public UserDTO getActiveUsers() {
        return mapping.toUserDTO(userDao.findByStatusTrue());
    }
}
