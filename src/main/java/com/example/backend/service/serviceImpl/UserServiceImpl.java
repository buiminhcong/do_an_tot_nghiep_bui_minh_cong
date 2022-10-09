package com.example.backend.service.serviceImpl;

import com.example.backend.dto.UserRequest;
import com.example.backend.entity.User;
import com.example.backend.entity.UserRole;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserRoleRepository;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public boolean checkExitUser(String username)  {

        User u = userRepository.findUserByUserName(username);
        if(u != null){
            return true;
        }
        return false;
    }

    @Override
    public User getUserByUserNameAndPassword(String username, String password) throws NotFoundException {

        User u = userRepository.findUserByUserNameAndPassword(username, password);
        if(u != null){
            return u;
        }
        throw new NotFoundException("Not found user : " + username);
    }

    @Override
    public UserRole getRoleByIdUser(int id_user) throws NotFoundException{
        UserRole userRole = userRoleRepository.getUserRoleByIdUser(id_user);
        if(userRole != null){
            return userRole;
        }else {
            throw new NotFoundException("Not found role user with id  : " + id_user);
        }
    }

    @Override
    public UserRole login(UserRequest request) throws NotFoundException{
        User u = userRepository.findUserByUserNameAndPassword(request.getUsername(), request.getPassword());
        if(u != null){
            UserRole userRole = userRoleRepository.getUserRoleByIdUser(u.getId());
            return userRole;
        }
        throw new NotFoundException("Login fail with user : " + request.getUsername());
    }


}
