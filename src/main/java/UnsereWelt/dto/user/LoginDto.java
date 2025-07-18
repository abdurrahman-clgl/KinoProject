package UnsereWelt.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @NotBlank(message = "Email darf nicht leer sein")
    @Email(message = "Bitte eine gültige E-Mail-Adresse angeben")
    private String email;

    @NotBlank(message = "Passwort darf nicht leer sein")
    private String password;
}
