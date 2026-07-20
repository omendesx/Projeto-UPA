package com.sistemaupa.config;

import com.sistemaupa.entity.Usuario;
import com.sistemaupa.enums.Cargo;
import com.sistemaupa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public DataInitializer(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        if (!usuarioRepository.existsByEmail(adminEmail)) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail(adminEmail);
            admin.setSenha(adminPassword);
            admin.setCargo(Cargo.ADMIN);
            admin.setAtivo(true);

            usuarioRepository.save(admin);
        }
    }
}
