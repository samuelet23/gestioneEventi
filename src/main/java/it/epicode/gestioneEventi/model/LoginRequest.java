package it.epicode.gestioneEventi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Il campo non può essere null")
    @NotEmpty(message = "Il valore  username non può essere vuoto")
    private String username;

    @NotBlank(message = "Il campo non può essere null")
    @NotEmpty(message = "Il valore  username non può essere vuoto")
    private String password;

}
