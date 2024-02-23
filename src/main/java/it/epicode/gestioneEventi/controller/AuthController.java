package it.epicode.gestioneEventi.controller;

import it.epicode.gestioneEventi.Exception.HandlerException;
import it.epicode.gestioneEventi.Exception.LoginFaultException;
import it.epicode.gestioneEventi.model.LoginRequest;
import it.epicode.gestioneEventi.model.Request.UtenteRequest;
import it.epicode.gestioneEventi.model.Utente;
import it.epicode.gestioneEventi.security.JwtTools;
import it.epicode.gestioneEventi.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTools jwtTools;


    @Autowired
    private PasswordEncoder encode;


    @PostMapping("register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult) {
        HandlerException.badRequestException(bindingResult);
        return utenteService.save(utenteRequest);
    }

    @PostMapping("login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult)  {
        HandlerException.badRequestException(bindingResult);
        Utente utente = utenteService.getUtenteByUsername(loginRequest.getUsername());
        if (encode.matches(loginRequest.getPassword(), utente.getPassword())) {
            return jwtTools.creaToken(utente);
        }
        else{
            throw new LoginFaultException("username/password errata");
        }
    }

}
