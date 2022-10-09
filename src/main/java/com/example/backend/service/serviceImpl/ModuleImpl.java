package com.example.backend.service.serviceImpl;

import com.example.backend.entity.Module;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.ModuleRepository;
import com.example.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public Module getModuleById(int id) throws NotFoundException {

        if(moduleRepository.findModuleById(id) != null){
            return moduleRepository.findModuleById(id);
        }

        throw new NotFoundException("Not found module with id: "+ id);
    }
}
