package com.goldFish.payload;

import com.goldFish.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {

    private long id;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String lastName;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @NotEmpty
    @Email
    @Size(min = 7, max = 50, message = "Invalid Email!!")
    private String email;

    @NotNull
    private long contact;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotEmpty
    private String password;


}

