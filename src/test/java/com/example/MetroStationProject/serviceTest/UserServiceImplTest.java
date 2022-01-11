package com.example.MetroStationProject.serviceTest;

import com.example.MetroStationProject.dto.UserDetailsDto;
import com.example.MetroStationProject.dto.UserDto;
import com.example.MetroStationProject.enumeration.UserRole;
import com.example.MetroStationProject.model.User;
import com.example.MetroStationProject.repository.UserRepository;
import com.example.MetroStationProject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    UserRepository userRepository;

    User user = new User(1L, "dogukan", "123456", UserRole.NORMAL);

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("dogukan");
        userDto.setPassword(bCryptPasswordEncoder.encode("123456"));
        userDto.setUserRole(UserRole.NORMAL);

        User result = User.builder()
                .id(null)
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .userRole(UserRole.NORMAL)
                .build();
        when(userRepository.save(result)).thenReturn(user);
        assertEquals(userService.createUser(userDto).getUsername(), result.getUsername());
    }

    @Test
    public void testGetUser() throws Exception {
        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        assertEquals(userService.getUser(userDetailsDto, 1L), user);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = User.builder()
                .id(1L)
                .username("dogukan")
                .password("123456")
                .userRole(UserRole.NORMAL)
                .build();

        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        UserDto userDto = new UserDto();
        userDto.setUsername("cansu");
        userDto.setPassword("123456");
        userDto.setUserRole(UserRole.DISCOUNT);

        User userUpdated = User.builder()
                .id(1L)
                .username("cansu")
                .password(bCryptPasswordEncoder.encode("123456"))
                .userRole(UserRole.DISCOUNT)
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(userUpdated)).thenReturn(userUpdated);
        assertEquals(userService.updateUser(userDetailsDto,1L,userDto),userUpdated);
    }

    @Test
    public void testDeleteUser() throws Exception {
        UserDetailsDto userDetailsDto = new UserDetailsDto(user);

        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        assertEquals(userService.deleteUser(userDetailsDto, 1L), user);
    }
}
