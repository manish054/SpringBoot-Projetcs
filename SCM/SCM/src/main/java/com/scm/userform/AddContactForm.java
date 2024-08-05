package com.scm.userform;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddContactForm {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Contact Number is required")
    private String contactNum;

    @Email(message = "Invalid Email")
    private String email;
    
    private String address;
    private String about;
    private String website;
    private String linkedIn;
    private Boolean favourite;
    private MultipartFile profilePic;
}
