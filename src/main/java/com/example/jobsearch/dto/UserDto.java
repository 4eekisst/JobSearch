package com.example.jobsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot be longer than 255 characters")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(max = 255, message = "Surname cannot be longer than 255 characters")
    private String surname;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    @Max(value = 50, message = "Age must be less than or equal to 50")
    private Byte age;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password can only contain alphanumeric characters")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "Invalid phone number format")
    private String phoneNumber;

    private String avatar;

    private Boolean enabled;

    private Long roleId;
}
