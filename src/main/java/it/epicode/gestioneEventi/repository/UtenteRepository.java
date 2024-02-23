package it.epicode.gestioneEventi.repository;

import it.epicode.gestioneEventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findByUsername(String username);
    Utente findByNome(String nome);
}
