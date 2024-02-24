package it.epicode.gestioneEventi.repository;

import it.epicode.gestioneEventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByNome(String nome);
}
