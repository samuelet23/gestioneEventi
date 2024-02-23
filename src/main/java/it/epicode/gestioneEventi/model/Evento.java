package it.epicode.gestioneEventi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento_id")
    @SequenceGenerator(name = "evento_id", allocationSize = 1, initialValue = 1)
    private long id;
    @Column(nullable = false)
    private String titolo;

    private String descrizione;
    @Column(nullable = false)
    private LocalDate dataEvento;

    @Column(nullable = false)
    private String luogo;

    private int numeroPostiDisponibili;

    @ManyToMany(mappedBy = "eventiPrenotati")
    private Set<Utente> partecipanti;

    private boolean disponibile;

    public boolean getDisponibile(){
        return disponibile;
    }

}
