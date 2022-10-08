package com.example.backend.service.serviceImpl;

import com.example.backend.dto.NameRequest;
import com.example.backend.entity.Name;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.NameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NameServiceImpl implements com.example.backend.service.NameService {

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Name createName(NameRequest request) {
        Name name = mapper.map(request, Name.class);
        name.setDeleted(0);
        return nameRepository.save(name);
    }

    @Override
    public Name updateName(int id, NameRequest request) throws NotFoundException {
        Optional<Name> optionalName = nameRepository.findById(id);
        if (optionalName.isPresent()) {
            Name name = optionalName.get();
            name.setFirstName(request.getFirstName());
            name.setLastName(request.getLastName());
            name.setMiddleName(request.getMiddleName());
            return nameRepository.save(name);
        }
        throw new NotFoundException("Not found name with id: " + id);
    }

    @Override
    public Boolean removeName(int id) throws NotFoundException {
        Optional<Name> optionalName = nameRepository.findById(id);
        if (optionalName.isPresent()) {
            Name name = optionalName.get();
            name.setDeleted(1);
            return true;
        }
        throw new NotFoundException("Not found name with id: " + id);
    }

}
