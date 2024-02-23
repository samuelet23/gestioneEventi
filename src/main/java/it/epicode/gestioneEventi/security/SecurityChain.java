package it.epicode.gestioneEventi.security;

import it.epicode.gestioneEventi.model.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityChain {
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtTools jwtTools;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable); //disabilita csrf
        httpSecurity.cors(AbstractHttpConfigurer::disable); // disabilita cors

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth")
                .permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utenti/**")
                .hasAuthority(Ruolo.ORGANIZZATORE.name()));
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**")
                .denyAll());
        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.addAllowedOrigin("http://www.example.com");
        cors.addAllowedMethod(HttpMethod.GET);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("*/**", cors);
        return configurationSource;
    }

}
