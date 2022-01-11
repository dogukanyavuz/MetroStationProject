package com.example.MetroStationProject.serviceTest;

import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.enumeration.UserRole;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.repository.UserRepository;
import com.example.MetroStationProject.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    UserDetailsService userDetailsService ;

    User user = new User(1L, "cansu", "123456", UserRole.NORMAL);

    @Test
    public void testLoadUserByUsername() throws Exception {
        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        when(userRepository.findTopByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertEquals(userDetailsServiceImpl.loadUserByUsername(user.getUsername()).getUsername(),userDetailsDto.getUsername());
    }
}
