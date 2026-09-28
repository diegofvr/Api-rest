package com.diego.auth_service.repositorio;

import com.diego.auth_service.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository<Usuario, Long> le dice a Spring:
// "dame automáticamente los métodos CRUD para la entidad Usuario, cuyo id es de tipo Long"
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Este método NO lo implementamos nosotros: Spring lee el nombre del método
    // ("findByUsuario") y genera solo el SQL equivalente a:
    // SELECT * FROM usuarios WHERE usuario = ?
    Optional<Usuario> findByUsuario(String usuario);
}