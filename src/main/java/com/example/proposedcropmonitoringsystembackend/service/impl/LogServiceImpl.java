package com.example.proposedcropmonitoringsystembackend.service.impl;

import com.example.proposedcropmonitoringsystembackend.dao.LogDao;
import com.example.proposedcropmonitoringsystembackend.dto.impl.LogDTO;
import com.example.proposedcropmonitoringsystembackend.service.LogService;
import com.example.proposedcropmonitoringsystembackend.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void save(LogDTO dto) {
        logDao.save(mapping.toLogEntity(dto));
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(String id, LogDTO dto) {

    }

    @Override
    public LogDTO get(String id) {
        return null;
    }

    @Override
    public List<LogDTO> getAll() {
        return null;
    }
}
