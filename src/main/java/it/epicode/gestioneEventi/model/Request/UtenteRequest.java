package it.epicode.gestioneEventi.model.Request;

import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Ruolo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UtenteRequest {

    @NotBlank(message = "Il campo nome non può essere vuoto/null")
    private String nome;
    @NotBlank(message = "Il campo cognome non può essere vuoto/null")
    private String cognome;

    @Email(message = "Inserisci un formato email corretto")
    private String email;
    @NotBlank(message = "La password non può essere vuota/null")
    @Size(min = 5, message = "La password deve essere di almeno 5 caratteri")
    private String password;

    @NotBlank(message = "username non può essere vuota/null")
    private String username;

    private List<Evento> eventiPrenotati;
}
