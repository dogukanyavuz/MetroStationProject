package com.example.MetroStationProject.service.impl;

import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.dto.UserDto;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.repository.UserRepository;
import com.example.MetroStationProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    /*
     * Create user can be called by anyone
     */
    @Override
    public User createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .userRole(userDto.getUserRole())
                .build();
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(UserDetailsDto userDetailsDto, Long userId) {
        checkUserPermission(userDetailsDto, userId);
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    public User updateUser(UserDetailsDto userDetailsDto, Long userId, UserDto userDto) {
        checkUserPermission(userDetailsDto, userId);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        user.setUsername(userDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUserRole(userDto.getUserRole());
        return this.userRepository.save(user);
    }

    @Override
    public User deleteUser(UserDetailsDto userDetailsDto, Long userId) {
        checkUserPermission(userDetailsDto, userId);
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        this.userRepository.delete(user);
        return user;
    }

    private static void checkUserPermission(UserDetailsDto userDetailsDto, Long userId) {
        if (!userDetailsDto.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
