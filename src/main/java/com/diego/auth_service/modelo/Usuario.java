package com.diego.auth_service.modelo; // ajusta esto al paquete real que te generó IntelliJ

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity le dice a Spring: "esta clase representa una tabla en la base de datos"
@Entity
// @Table indica el nombre exacto de la tabla que Spring va a crear en MySQL
@Table(name = "usuarios")
public class Usuario {

    // @Id marca este campo como la llave primaria de la tabla
    @Id
    // @GeneratedValue le dice a MySQL que genere el id automáticamente (auto-incremental)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de usuario con el que se registra e inicia sesión
    private String usuario;

    // Contraseña del usuario
    private String contrasena;

    // Constructor vacío: Spring/JPA lo necesita obligatoriamente para poder crear objetos Usuario
    public Usuario() {
    }

    // Constructor con parámetros: para crear un Usuario nuevo fácilmente desde el código
    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // --- Getters y setters (igual que en tu clase Producto) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}