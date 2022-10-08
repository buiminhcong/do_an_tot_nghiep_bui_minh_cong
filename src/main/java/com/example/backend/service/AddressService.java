package com.example.backend.service;

import com.example.backend.dto.AddressRequest;
import com.example.backend.entity.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address createAddress(AddressRequest request);

    Address updateAddress(int id, AddressRequest request);

    Boolean removeAddress (int id);

}
