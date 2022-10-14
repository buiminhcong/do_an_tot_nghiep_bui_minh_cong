package com.example.backend.service;

import com.example.backend.dto.ModuleRequest;
import com.example.backend.entity.Module;

import java.util.List;

public interface ModuleService {

    Module getModuleById(int id);

    Module getModuleByCode(String code);

    List<Module> getAllModule();

    Module createModule(ModuleRequest request);

    Module updateModule(int id, ModuleRequest request);

    Boolean removeModuleById(int id);

}
