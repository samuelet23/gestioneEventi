package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Ruolo;
import it.epicode.gestioneEventi.model.Utente;
import it.epicode.gestioneEventi.service.UtenteService;
import jakarta.persistence.*;
import jakarta.websocket.server.PathParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{username}")
    public Utente getByUsername(String username){
        return utenteService.getUtenteByUsername(username);
    }
    @PostMapping("/prenota/titoloEvento/username")
    public Evento prenotaPosto(@RequestParam String titoloEvento, @RequestParam String username){
        return utenteService.prenotaPosto(titoloEvento, username);
    }

}
