package com.example.MetroStationProject.service;

import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.dto.UserDto;
import com.example.MetroStationProject.model.User;

public interface UserService {

    User createUser(UserDto userDto);

    User getUser(UserDetailsDto userDetailsDto, Long userId);

    User updateUser(UserDetailsDto userDetailsDto, Long userId, UserDto userDto);

    User deleteUser(UserDetailsDto userDetailsDto, Long userId);

}
