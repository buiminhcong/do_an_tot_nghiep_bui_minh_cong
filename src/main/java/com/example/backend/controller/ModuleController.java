package com.example.backend.controller;

import com.example.backend.entity.Module;
import com.example.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("")
    public List<Module> getAllModule(){
        return moduleService.getAllModule();
    }

}
