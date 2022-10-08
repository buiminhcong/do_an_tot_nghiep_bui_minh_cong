package com.example.backend.service.serviceImpl;

import com.example.backend.dto.AddressRequest;
import com.example.backend.entity.Address;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.AddressRepository;
import com.example.backend.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ModelMapper mapper;

    private AddressRepository addressRepository;

    @Override
    public Address createAddress(AddressRequest request) {
        Address address = mapper.map(request, Address.class);
        address.setDeleted(0);
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(int id, AddressRequest request) throws NotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            address.setPhone(request.getPhone());
            address.setNumberOfDistrict(request.getNumberOfDistrict());
            address.setEmail(request.getEmail());
            address.setCity(request.getCity());
            address.setNote(request.getNote());
            address.setDistrict(request.getDistrict());
            address.setDayOfBirth(request.getDayOfBirth());
            address.setAvatar(request.getAvatar());
            return addressRepository.save(address);
        }

        throw new NotFoundException("Not found address with id: "+  id);
    }

    @Override
    public Boolean removeAddress(int id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            address.setDeleted(1);
            return true;
        }
        throw new NotFoundException("Not found address with id: "+  id);
    }


}
