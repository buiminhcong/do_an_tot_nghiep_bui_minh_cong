package com.example.backend.service;

import com.example.backend.dto.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.entity.UserRole;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean checkExitUser(String username);

    User getUserByUserNameAndPassword(String username, String password);

    UserRole getRoleByIdUser(int id_user);

    UserRole login(UserRequest request);


}
