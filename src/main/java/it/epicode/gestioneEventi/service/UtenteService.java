package it.epicode.gestioneEventi.service;

import it.epicode.gestioneEventi.Exception.BadRequestException;
import it.epicode.gestioneEventi.model.Evento;
import it.epicode.gestioneEventi.model.Request.UtenteRequest;
import it.epicode.gestioneEventi.model.Ruolo;
import it.epicode.gestioneEventi.model.Utente;
import it.epicode.gestioneEventi.repository.EventoRepository;
import it.epicode.gestioneEventi.repository.UtenteRepository;
import it.epicode.gestioneEventi.security.JwtTools;
import jdk.jfr.Event;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder encoder;




public List<Utente> getAll(){
    return utenteRepository.findAll();
}
public Utente getUtenteByUsername(String username){
    return utenteRepository.findByUsername(username)
            .orElseThrow(()-> new BadRequestException("Utente non trovato"));
    }
public Utente getUtenteById(long id ){
        return utenteRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Utente non trovato"));
    }


    public Utente save(UtenteRequest utenteRequest){
        Utente utente = new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setUsername(utenteRequest.getUsername());
        utente.setEmail(utenteRequest.getEmail());
        utente.setRuolo(Ruolo.UTENTE);
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));

        return utenteRepository.save(utente);
    }
    public Evento prenotaPosto(String titolo, String username){
        Evento evento = eventoRepository.findByTitolo(titolo)
                .orElseThrow(()-> new BadRequestException("l'evento:"+ titolo+" non esiste"));
        int postiDisponibili = evento.getNumeroPostiDisponibili();

        if (postiDisponibili >  0 && evento.getDisponibile()) {
            Utente utente = getUtenteByUsername(username);
            if (evento.getPartecipanti().contains(utente)) {
                throw new BadRequestException("L'utente: "+utente.getNome()+"è gia prenotato per questo evento");
            }
            evento.setNumeroPostiDisponibili(postiDisponibili - 1);
            evento.setPartecipanti(Set.of(utente));
            System.out.println("Hai prenotato un posto per l'evento: "+evento.getTitolo()+" in data: "+ evento.getDataEvento());
            return evento;
        }else {
            evento.setDisponibile(false);
            throw new BadRequestException("l'evento "+evento.getTitolo()+"è SOLD OUT"  );
        }
    }

}
