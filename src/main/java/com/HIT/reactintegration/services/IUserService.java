package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.responsesdto.SuccessResponseDTO;
import com.HIT.reactintegration.dtos.userdto.UserRegistrationDTO;

public interface IUserService {
    SuccessResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO);
}
