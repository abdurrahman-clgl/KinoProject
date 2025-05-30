package UnsereWelt.dto;

import UnsereWelt.enums.Role;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegistrationDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be valid"
    )
    private String phone;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must be secure"
    )
    private String password;

    private Role role;
}
