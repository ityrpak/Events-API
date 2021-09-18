package com.HIT.reactintegration.services;

import com.HIT.reactintegration.configs.DelegatePasswordEncode;
import com.HIT.reactintegration.dtos.responsesdto.SuccessResponseDTO;
import com.HIT.reactintegration.dtos.userdto.UserRegistrationDTO;
import com.HIT.reactintegration.entities.User;
import com.HIT.reactintegration.repositories.RoleRepository;
import com.HIT.reactintegration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

import static com.HIT.reactintegration.enums.RoleEnum.*;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DelegatePasswordEncode passwordEncode;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        try {
            return userRepository.findByNickname(nickname).orElseThrow();
        } catch (NoSuchElementException ex){
            throw new UsernameNotFoundException(nickname);
        }

    }

    @Override
    public SuccessResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(
            User.builder()
                    .nickname(userRegistrationDTO.getNickname())
                    .email(userRegistrationDTO.getEmail())
                    .password(passwordEncode.getDelegatingEncoder("bcrypt").encode(userRegistrationDTO.getPassword()))
                    .firstName(userRegistrationDTO.getFirstName())
                    .lastName(userRegistrationDTO.getLastName())
                    .role(roleRepository.findByRoleName(ROLE_USER))
                    .enabled(Boolean.TRUE)
                    .build()
        );
        return getSuccessResponse(Map.of("userRegistration","User " + userRegistrationDTO.getNickname() + " registered"));
    }

    private SuccessResponseDTO getSuccessResponse(Object data) {
        return SuccessResponseDTO.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(data)
                .build();
    }
}
