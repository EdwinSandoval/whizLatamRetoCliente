package com.example.cliente.security;


import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;


    // Usuarios de ejemplo (en un entorno real, usarías una base de datos)
    private final String VALID_USERNAME = "user";
    private final String VALID_PASSWORD = "password";

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
public String login(@RequestParam String username, @RequestParam String password) {
    // Verifica si el usuario y la contraseña son correctos
    if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
        return jwtService.generateToken(username);
    } else {
        throw new RuntimeException("Usuario o contraseña incorrectos");
    }
}

}
