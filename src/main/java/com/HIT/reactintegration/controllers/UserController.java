package com.HIT.reactintegration.controllers;

import com.HIT.reactintegration.dtos.userdto.UserLoginDTO;
import com.HIT.reactintegration.dtos.userdto.UserRegistrationDTO;
import com.HIT.reactintegration.exceptions.EntityAlreadyExistsException;
import com.HIT.reactintegration.exceptions.EntityNotFoundException;
import com.HIT.reactintegration.services.UserServiceImpl;
import com.HIT.reactintegration.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User", description = "User related endpoints")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody @Validated UserRegistrationDTO userRegistrationDTO){
        try {
            userService.loadUserByUsername(userRegistrationDTO.getNickname());
            throw new EntityAlreadyExistsException("nickname");
        } catch (UsernameNotFoundException ex){
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userRegistrationDTO));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody @Validated UserLoginDTO userLoginDTO){
        try {
            UserDetails userDetails = userService.loadUserByUsername(userLoginDTO.getNickname());
            return ResponseEntity.status(HttpStatus.OK).body(jwtTokenUtil.generateToken(userLoginDTO, userDetails));

        } catch (UsernameNotFoundException ex){
            throw new EntityNotFoundException("nickname", userLoginDTO.getNickname());
        }
    }

}
