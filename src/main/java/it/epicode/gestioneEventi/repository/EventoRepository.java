package it.epicode.gestioneEventi.repository;

import it.epicode.gestioneEventi.model.Evento;
import jdk.jfr.Event;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    Optional<Evento> findByTitolo(String titolo);
    Optional<List<Evento>> findByDataEvento(LocalDate dataEvento);
    Optional<Evento> findByLuogo(String luogo);

    @Query("SELECT e.numeroPostiDisponibili FROM Evento e WHERE e.titolo = :titolo")
    Integer findNumeroPostiDisponibiliByTitolo(String titolo);

    @Query("SELECT e.numeroPostiDisponibili FROM Evento e WHERE e.id = :id")
    Integer findNumeroPostiDisponibiliByTitolo(long id);
}
