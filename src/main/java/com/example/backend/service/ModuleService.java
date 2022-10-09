package com.example.backend.service;

import com.example.backend.entity.Module;

import java.util.List;

public interface ModuleService {

    Module getModuleById(int id);

    List<Module> getAllModule();

}
