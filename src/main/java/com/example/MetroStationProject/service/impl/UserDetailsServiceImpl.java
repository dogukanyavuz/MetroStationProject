package com.example.MetroStationProject.service.impl;

import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findTopByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
        return new UserDetailsDto(user);
    }
}
