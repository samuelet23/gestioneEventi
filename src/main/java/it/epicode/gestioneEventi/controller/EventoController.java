package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.Exception.BadRequestException;
import it.epicode.gestioneEventi.Exception.HandlerException;
import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Request.EventoRequest;
import it.epicode.gestioneEventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('UTENTE')")
    public List<Evento> getAll(){
        return eventoService.getAllEventi();
    }

    @GetMapping("/id")
    public Evento getEventoById(@RequestParam long id){
        return eventoService.getEventoById(id);
    }
    @GetMapping("/luogo")
    public Evento getEventoByLuogo (@RequestParam String luogo){
        return eventoService.getEventoByLuogo(luogo);
    }
    @GetMapping("/titolo")
    public Evento getEventoByTitolo (@RequestParam String titolo){
        return eventoService.getEventoByTitolo(titolo);
    }

    @DeleteMapping("/id")
    public void elimina(@RequestParam long id){
        eventoService.eliminaEvento(id);
    }
    @PostMapping("/evento/crea")
    public Evento creaEvento(@RequestBody  EventoRequest eventoRequest){
        return eventoService.creaEvento(eventoRequest);
    }
    @PutMapping("/id")
    public Evento modifica(@RequestParam long id, @RequestBody @Validated EventoRequest eventoRequest, BindingResult bindingResult){
        HandlerException.badRequestException(bindingResult);
        return eventoService.modificaEvento(id, eventoRequest);
    }
    @PatchMapping("/titolo/disponibile")
    public Evento setDisponibile(@RequestParam String titolo){
        return eventoService.setEventoDisponibile(titolo);
    }
    @PatchMapping("/titolo/nonDisponibile")
    public Evento setNonDisponibile(@RequestParam String titolo){
        return eventoService.setEventoNonDisponibile(titolo);
    }


}
