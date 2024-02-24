package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Ruolo;
import it.epicode.gestioneEventi.model.Utente;
import it.epicode.gestioneEventi.service.UtenteService;
import jakarta.persistence.*;
import jakarta.websocket.server.PathParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController  {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public List<Utente> getAll(){
        return utenteService.getAll();
    }

    @GetMapping("/id")
    public Utente getById(@RequestParam long id){
        return utenteService.getUtenteById(id);
    }

    @GetMapping("/username")
    public Utente getByUsername(@RequestParam String username){
        return utenteService.getUtenteByUsername(username);
    }
    @PostMapping("/prenota/titoloEvento/username")
    public Evento prenotaPosto(@RequestParam("titoloEvento") String titoloEvento,
                               @RequestParam("username") String username) {
        return utenteService.prenotaPosto(titoloEvento, username);
    }


}
