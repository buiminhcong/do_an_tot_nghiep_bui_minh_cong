package com.example.backend.controller;

import com.example.backend.entity.Subject;
import com.example.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subject")
@CrossOrigin("http://localhost:4200")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{id}")
    public Subject getSubjectById( @PathVariable("id") int id){
        return subjectService.getSubjectById(id);
    }

    @GetMapping("")
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @GetMapping("/remove/{id}")
    public Boolean removeSubject(@PathVariable("id") int id){
        return subjectService.removeSubject(id);
    }

}
