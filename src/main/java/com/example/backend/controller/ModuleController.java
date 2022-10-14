package com.example.backend.controller;

import com.example.backend.dto.ModuleRequest;
import com.example.backend.entity.Module;
import com.example.backend.entity.Subject;
import com.example.backend.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/module")
@CrossOrigin("http://localhost:4200/")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @GetMapping("")
    public List<Module> getAllModule(){
        return moduleService.getAllModule();
    }

    @PostMapping("/create")
    public Module createModule( @RequestBody @Valid ModuleRequest request){
        return moduleService.createModule(request);
    }

    @PostMapping("/update/{id}")
    public Module updateModule( @PathVariable("id") int id, @Valid @RequestBody ModuleRequest request ){
        return moduleService.updateModule(id, request);
    }

    @GetMapping("/remove/{id}")
    public Boolean removeModule(@PathVariable("id") int id){
        return moduleService.removeModuleById(id);
    }
}
