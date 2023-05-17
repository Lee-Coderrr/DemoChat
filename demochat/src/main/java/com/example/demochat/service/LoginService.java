package com.example.demochat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demochat.dto.UserDTO;
import com.example.demochat.entity.User;
import com.example.demochat.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    public UserDTO saveUserDto(UserDTO userDto) {
        User savedUser = userRepo.save(userDto.toEntity());
        return new UserDTO(
                savedUser.getId(),
                savedUser.getName());
    }
}