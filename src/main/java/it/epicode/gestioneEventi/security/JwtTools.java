package it.epicode.gestioneEventi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.gestioneEventi.Exception.UnAuthorizedException;
import it.epicode.gestioneEventi.model.Utente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@PropertySource("application.properties")
public class JwtTools {

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.expiration}")
    private String durata;

    public String creaToken(Utente utente){
        return Jwts.builder().subject(utente.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(durata)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
    }

    public void validaToken(String token) throws UnAuthorizedException {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch (Exception e) {
            throw new UnAuthorizedException("Invalid access token");
        }
    }

    public String estraiUsernameDalToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
}

