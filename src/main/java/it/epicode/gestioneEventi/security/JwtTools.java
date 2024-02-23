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
        long currentMills = System.currentTimeMillis();
        return Jwts.builder()
                .subject(utente.getUsername())
                .issuedAt(new Date(currentMills))
                .expiration(new Date(currentMills-Long.parseLong(durata)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public void validaToken(String token) throws UnAuthorizedException {
        try{
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parse(token);
        }catch (Exception e){
            throw new UnAuthorizedException(e.getMessage());
        }
    }

    public String estraiUsernameDalToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}

