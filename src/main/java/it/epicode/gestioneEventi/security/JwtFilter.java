package it.epicode.gestioneEventi.security;

import it.epicode.gestioneEventi.Exception.UnAuthorizedException;
import it.epicode.gestioneEventi.model.Utente;
import it.epicode.gestioneEventi.service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTools jwtTools;

    private UtenteService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization =  request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer")) {
            try {
                throw new UnAuthorizedException("Token non presente");
            } catch (UnAuthorizedException e) {
                throw new RuntimeException(e);
            }
        }
        String token= authorization.substring(7);// parte da 7 perchè il token parte dopo il Bearer
        try {
            jwtTools.validaToken(token);
        } catch (UnAuthorizedException e) {
            throw new RuntimeException(e);
        }
        String username  = jwtTools.estraiUsernameDalToken(token);

        Utente utente = userService.getUtenteByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null,utente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
