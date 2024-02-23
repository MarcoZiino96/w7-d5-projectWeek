package it.epicode.w7d5.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "nome obbligatorio")
    private String name;

    @NotBlank(message = "cognome obbligatorio")
    private String surname;

    @NotBlank(message = "email obbligatorio")
    private String email;

    @NotBlank(message = "password obbligatoria")
    private String password;
}
