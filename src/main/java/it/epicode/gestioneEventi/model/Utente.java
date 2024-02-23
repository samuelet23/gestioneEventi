package it.epicode.gestioneEventi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_id")
    @SequenceGenerator(name = "utente_id", allocationSize = 1, initialValue = 1)
    private long id;

    private String nome;
    private String cognome;
    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @ManyToMany()
    @JoinTable(
            name = "partecipanti_eventi",
            joinColumns = @JoinColumn(name = "partecipanti_id"),
            inverseJoinColumns = @JoinColumn(name = "eventiPrenotati_id")
    )
    private List<Evento> eventiPrenotati;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
