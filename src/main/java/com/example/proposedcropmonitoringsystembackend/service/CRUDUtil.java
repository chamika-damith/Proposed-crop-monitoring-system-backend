package com.example.proposedcropmonitoringsystembackend.service;

import java.util.List;

public interface CRUDUtil <T>{
    void save(T dto);
    void delete(String id);
    void update(String id, T dto);
    T get(String id);
    List<T> getAll();
}
