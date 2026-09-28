package com.diego.auth_service.controlador;

import com.diego.auth_service.modelo.Usuario;
import com.diego.auth_service.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// @RestController le dice a Spring: "esta clase maneja peticiones HTTP y devuelve
// las respuestas directamente como texto/JSON, no como páginas HTML"
@RestController
// @RequestMapping define el prefijo de la URL para todos los métodos de esta clase
// (ej: http://localhost:8080/api/auth/registro)
@RequestMapping("/api/auth")
public class AuthController {

    // @Autowired le pide a Spring: "dame automáticamente una instancia de UsuarioRepository"
    // (es como si Spring hiciera "new UsuarioRepository()" por ti, pero conectado a la BD real)
    @Autowired
    private UsuarioRepository usuarioRepository;

    // --- ENDPOINT DE REGISTRO ---
    // @PostMapping indica que este método responde a peticiones POST en /api/auth/registro
    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        // @RequestBody toma el JSON que envía Postman (usuario y contrasena)
        // y lo convierte automáticamente en un objeto Usuario

        // Verificamos si ya existe un usuario con ese nombre, para no duplicarlo
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsuario(usuario.getUsuario());

        if (usuarioExistente.isPresent()) {
            // Si ya existe, devolvemos un error 409 (Conflict) con un mensaje claro
            return ResponseEntity.status(409).body("Error: el usuario ya existe");
        }

        // Si no existe, lo guardamos en la base de datos usando el método save()
        // que ya viene incluido gratis por heredar de JpaRepository
        usuarioRepository.save(usuario);

        // Devolvemos código 201 (Created) junto con un mensaje de éxito
        return ResponseEntity.status(201).body("Usuario registrado exitosamente");
    }

    // --- ENDPOINT DE LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuarioIngresado) {
        // Buscamos en la base de datos si existe un usuario con ese nombre
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByUsuario(usuarioIngresado.getUsuario());

        // Validamos dos cosas: que el usuario exista Y que la contraseña coincida
        if (usuarioEncontrado.isPresent()
                && usuarioEncontrado.get().getContrasena().equals(usuarioIngresado.getContrasena())) {
            // Autenticación correcta
            return ResponseEntity.ok("Autenticación satisfactoria");
        } else {
            // Usuario no existe o la contraseña no coincide: en ambos casos
            // devolvemos el mismo mensaje genérico (por seguridad, no decimos cuál falló)
            return ResponseEntity.status(401).body("Error de autenticación");
        }
    }
}