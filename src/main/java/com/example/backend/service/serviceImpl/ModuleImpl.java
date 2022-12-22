package com.example.backend.service.serviceImpl;

import com.example.backend.dto.ModuleRequest;
import com.example.backend.entity.Module;
import com.example.backend.entity.Subject;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.ModuleRepository;
import com.example.backend.repository.SubjectRepository;
import com.example.backend.service.ModuleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Module getModuleById(int id) throws NotFoundException {

        if (moduleRepository.findModuleById(id) != null) {
            return moduleRepository.findModuleById(id);
        }

        throw new NotFoundException("Not found module with id: " + id);
    }

    @Override
    public Module getModuleByCode(String code) throws NotFoundException {

        Optional<Module> optional = Optional.ofNullable(moduleRepository.findModuleByCode(code));
        if (optional.isPresent()) {
            Module module = optional.get();
            return module;
        }
        throw new NotFoundException("Not found module with code: " + code);
    }

    @Override
    public List<Module> getAllModule() {

        return moduleRepository.getListModule();
    }

    @Override
    public Module createModule(ModuleRequest request) throws NotFoundException {

        Optional<Module> optional =
                Optional.ofNullable(moduleRepository.findModuleByCode(request.getModuleCode()));

        if (!optional.isPresent()) {
            Module module = new Module();
            Subject subject = subjectRepository.findSubjectById(request.getId_subject());
            if(subject != null){
                module.setName(request.getName());
                module.setCredits(request.getCredits());
                module.setModuleCode(request.getModuleCode());
                module.setSubject(subject);
                return moduleRepository.save(module);
            }
            throw new NotFoundException("Subject not found with id: " + request.getId_subject());
        } else {
            throw new NotFoundException("Module exits in database with code: " + request.getModuleCode());
        }
    }

    @Override
    public Module updateModule(int id, ModuleRequest request) throws NotFoundException{
        Optional<Module> optional = Optional.ofNullable(moduleRepository.findModuleById(id));
        Optional<Subject> optional1 = Optional.ofNullable(subjectRepository.findSubjectById(request.getId_subject()));
        Optional<Module> optional2 = Optional.ofNullable(moduleRepository.findModuleByCode(request.getModuleCode()));
        if(optional.isPresent() && optional1.isPresent()){

            Module module = optional.get();

            if(!optional2.isPresent()){
                Subject subject = optional1.get();
                module.setName(request.getName());
                module.setModuleCode(request.getModuleCode());
                module.setCredits(request.getCredits());
                module.setSubject(subject);
                return moduleRepository.save(module);
            }else {
                Module m1 = optional2.get();
                if(request.getModuleCode().equals(m1.getModuleCode())){
                    Subject subject = optional1.get();
                    module.setName(request.getName());
                    module.setModuleCode(request.getModuleCode());
                    module.setCredits(request.getCredits());
                    module.setSubject(subject);
                    return moduleRepository.save(module);
                }
                throw new NotFoundException("Module code had database "+ request.getModuleCode());
            }
        }
        throw new NotFoundException("Not found module with id: "+ id);
    }

    @Override
    public Boolean removeModuleById(int id) throws NotFoundException{
        Optional<Module> optional = Optional.ofNullable(moduleRepository.findModuleById(id));
        if (optional.isPresent()) {
            Module module = optional.get();
            module.setDeleted(1);
            moduleRepository.save(module);


            return true;
        }
        throw new NotFoundException("Not found module with code: " + id);
    }
}
