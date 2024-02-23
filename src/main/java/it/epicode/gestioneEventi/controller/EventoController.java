package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.Exception.BadRequestException;
import it.epicode.gestioneEventi.Exception.HandlerException;
import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Request.EventoRequest;
import it.epicode.gestioneEventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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
    @GetMapping("/{id}")
    public Evento getEventoById(@PathVariable long id){
        return eventoService.getEventoById(id);
    }
    @GetMapping("/{luogo}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public Evento getEventoByLuogo (@PathVariable String luogo){
        return eventoService.getEventoByLuogo(luogo);
    }
    @GetMapping("/{data}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public List<Evento> getEventiByDate (@PathVariable LocalDate data){
        return eventoService.getEventiByDate(data);
    }
    @GetMapping("/{titolo}")
    @PreAuthorize("hasAuthority('UTENTE')")
    public Evento getEventoByTitolo (@PathVariable String titolo){
        return eventoService.getEventoByTitolo(titolo);
    }
    @PostMapping("")
    public Evento creaEvento(@RequestBody EventoRequest eventoRequest, BindingResult bindingResult){
        HandlerException.badRequestException(bindingResult);
        return eventoService.creaEvento(eventoRequest);
    }

    @PutMapping("/{id}")
    public Evento modifica(@PathVariable long id,@RequestBody EventoRequest eventoRequest, BindingResult bindingResult){
        HandlerException.badRequestException(bindingResult);
        return eventoService.modificaEvento(id, eventoRequest);
    }
    @PatchMapping("/{titolo}/disponibile")
    public Evento setDisponibile(@PathVariable String titolo){
        return eventoService.setEventoDisponibile(titolo);
    }
    @PatchMapping("/{titolo}/nonDisponibile")
    public Evento setNonDisponibile(@PathVariable String titolo){
        return eventoService.setEventoNonDisponibile(titolo);
    }

    @DeleteMapping("/{id}")
    public void elimina(@PathVariable long id){
        eventoService.eliminaEvento(id);
    }
}
