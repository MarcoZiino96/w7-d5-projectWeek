package it.epicode.w7d5.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "username obbligatorio")
    private String email;
    @NotBlank(message = "password obbligatoria")
    private String password;
}
