package it.epicode.w7d5.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventRequest {

    @NotBlank(message = "titolo obbligatorio")
    private String title;

    @NotBlank(message = "descrizione obbligatorio")
    private String description;

    @NotBlank(message = "luogo obbligatorio")
    private String location;

    @NotBlank(message = "data obbligatorio")
    private LocalDate data;

    @NotBlank(message = "place obbligatorio")
    private String place;

    @NotBlank(message = "numero massimo partecipanti obbligatorio")
    private int maximumNumberPartecipants;
}
