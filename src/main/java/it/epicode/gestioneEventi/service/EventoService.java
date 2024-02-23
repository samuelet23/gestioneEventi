package it.epicode.gestioneEventi.service;

import it.epicode.gestioneEventi.Exception.BadRequestException;
import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Request.EventoRequest;
import it.epicode.gestioneEventi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;


    public List<Evento> getAllEventi(){
        return eventoRepository.findAll();
    }

    public Evento getEventoById(long id){
        return eventoRepository.findById(id)
                .orElseThrow(()->new BadRequestException("Evento non trovato"));
    }
    public Evento getEventoByLuogo (String luogo){
        return eventoRepository.findByLuogo(luogo)
                .orElseThrow(()-> new BadRequestException("Evento non trovato"));
    }
    public List<Evento> getEventiByDate (LocalDate data){
        return eventoRepository.findByDataEvento(data)
                .orElseThrow(()-> new BadRequestException("Nessun evento in questa data"));
    }
    public Evento getEventoByTitolo (String titolo){
        return eventoRepository.findByTitolo(titolo)
                .orElseThrow(()-> new BadRequestException("Evento non trovato"));
    }
    public Evento creaEvento(EventoRequest eventoRequest){
        Evento evento = new Evento();

        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setDataEvento(eventoRequest.getDataEvento());
        evento.setDisponibile(true);
        evento.setNumeroPostiDisponibili(eventoRequest.getNumeroPostiDisponibili());

        return eventoRepository.save(evento);
    }

    public Evento modificaEvento(long id ,EventoRequest eventoRequest){
        Evento evento  = getEventoById(id);

        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setDataEvento(eventoRequest.getDataEvento());
        evento.setDisponibile(true);
        evento.setNumeroPostiDisponibili(eventoRequest.getNumeroPostiDisponibili());

        return eventoRepository.save(evento);
    }

    public Evento setEventoNonDisponibile(String titolo){
        Evento evento =getEventoByTitolo(titolo);
        evento.setDisponibile(false);
        return eventoRepository.save(evento);
    }
    public Evento setEventoDisponibile(String titolo){
        Evento evento =getEventoByTitolo(titolo);
        evento.setDisponibile(true);
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(long id){
        eventoRepository.delete(getEventoById(id));
    }

}
