package it.epicode.gestioneEventi.model.Request;

import it.epicode.gestioneEventi.model.Utente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventoRequest {

    @NotBlank(message = "Il campo titolo non può essere vuoto/null")
    private String titolo;
    @NotBlank(message = "Il campo descrizione non può essere vuoto/null")
    private String descrizione;
    private LocalDate dataEvento;
    @NotBlank(message = "Il campo luogo non può essere vuoto/null")
    private String luogo;

    @NotBlank(message = "Il campo non può essere vuoto/null")
    private int numeroPostiDisponibili;


}
