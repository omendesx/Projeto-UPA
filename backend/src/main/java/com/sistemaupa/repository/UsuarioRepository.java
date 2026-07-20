package com.sistemaupa.repository;

import com.sistemaupa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailAndSenhaAndAtivoTrue(String email, String senha);

    boolean existsByEmail(String email);
}
