package it.epicode.w7d5.model;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "username obbligatorio")
    private String email;
    @NotBlank(message = "password obbligatoria")
    private String password;
}
