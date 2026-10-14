package com.matheus.Artifact.loja_virtual.demo;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UsuarioDetailsService usuarioDetailsService,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha())
        );

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(request.getEmail());
        return jwtService.gerarToken(userDetails);
    }
}
