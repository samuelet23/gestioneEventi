package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Ruolo;
import it.epicode.gestioneEventi.service.UtenteService;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtenteController  {

    private String nome;
    private String cognome;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @ManyToMany()
    @JoinTable(
            name = "partecipanti_eventi",
            joinColumns = @JoinColumn(name = "partecipanti_id"),
            inverseJoinColumns = @JoinColumn(name = "eventiPrenotati_id")
    )
    private List<Evento> eventiPrenotati;
    @Autowired
    private UtenteService utenteService;
}
