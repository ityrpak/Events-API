package com.HIT.reactintegration.services;

import com.HIT.reactintegration.configs.BCryptConfig;
import com.HIT.reactintegration.configs.DelegatePasswordEncode;
import com.HIT.reactintegration.dtos.responsesdto.SuccessResponseDTO;
import com.HIT.reactintegration.dtos.userdto.UserRegistrationDTO;
import com.HIT.reactintegration.repositories.RoleRepository;
import com.HIT.reactintegration.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private DelegatePasswordEncode passwordEncode;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void registerUser_success(){

        UserRegistrationDTO userRegistrationDTO = UserRegistrationDTO.builder()
                .email("mockEmail@email.com")
                .firstName("John")
                .lastName("Doe")
                .nickname("johnDoe")
                .password("johnDoe123")
                .build();

        Mockito.when(passwordEncode.getDelegatingEncoder(ArgumentMatchers.eq("bcrypt"))).thenReturn(new BCryptConfig().getEncoder());
        SuccessResponseDTO testResponseDTO = userServiceImpl.registerUser(userRegistrationDTO);

        Mockito.verify(userRepository).save(ArgumentMatchers.any());
        assertEquals(200, testResponseDTO.getCode());
        assertEquals("OK", testResponseDTO.getStatus());
        assertEquals("{userRegistration=User johnDoe registered}", testResponseDTO.getData().toString());

    }

}