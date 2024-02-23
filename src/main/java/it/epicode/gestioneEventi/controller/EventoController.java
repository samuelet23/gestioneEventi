package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.Exception.HandlerException;
import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Request.EventoRequest;
import it.epicode.gestioneEventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evento")
public class EventoController {


    @Autowired
    private EventoService eventoService;

    @PostMapping("")
    public Evento creaEvento(@RequestBody EventoRequest eventoRequest, BindingResult bindingResult){
        HandlerException.badRequestException(bindingResult);
        return eventoService.creaEvento(eventoRequest);
    }
}
