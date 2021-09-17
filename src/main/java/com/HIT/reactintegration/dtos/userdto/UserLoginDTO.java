package com.HIT.reactintegration.dtos.userdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {

    @NotBlank(message = "nickname must not be empty")
    private String nickname;
    @Length(min = 6, max = 32, message = "password must have from 6 to 32 chars")
    private String password;
}
