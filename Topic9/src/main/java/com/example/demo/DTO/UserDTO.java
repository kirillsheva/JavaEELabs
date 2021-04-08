package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = "Login is empty!")
    @Pattern(regexp = "\\w+", message ="You should use only Latin characters and numbers!")
    private String login;
    @NotEmpty(message = "Password is empty!")
    @Size(min = 8, max = 20, message = "Password should contain 8 to 20 characters")
    private String password;
}
