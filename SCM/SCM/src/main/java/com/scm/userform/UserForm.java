package com.scm.userform;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Min 3 characters are required")
    private String name;

    @Email(message = "Invalid Email Adderss")
    @NotBlank(message = "Email is Required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Minimum 8 Characters are required")
    private String password;

    @Size(min = 10, max = 12, message = "Invalid Phone Number")
    private String phoneNum;
    private String about;
}
