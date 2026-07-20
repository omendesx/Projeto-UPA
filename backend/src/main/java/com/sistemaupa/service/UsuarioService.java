package com.sistemaupa.service;

import com.sistemaupa.dto.LoginRequest;
import com.sistemaupa.dto.UsuarioRequest;
import com.sistemaupa.dto.UsuarioResponse;
import com.sistemaupa.entity.Usuario;
import com.sistemaupa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponse login(LoginRequest request) {
        Usuario usuario = repository
                .findByEmailAndSenhaAndAtivoTrue(request.email(), request.senha())
                .orElseThrow(() -> new IllegalArgumentException("E-mail ou senha inválidos."));

        return toResponse(usuario);
    }

    public List<UsuarioResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public UsuarioResponse buscar(Long id) {
        return toResponse(buscarEntidade(id));
    }

    public UsuarioResponse criar(UsuarioRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();
        aplicarDados(usuario, request);

        return toResponse(repository.save(usuario));
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = buscarEntidade(id);

        if (!usuario.getEmail().equals(request.email())
                && repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        aplicarDados(usuario, request);

        return toResponse(repository.save(usuario));
    }

    public void excluir(Long id) {
        Usuario usuario = buscarEntidade(id);
        repository.delete(usuario);
    }

    private Usuario buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    private void aplicarDados(Usuario usuario, UsuarioRequest request) {
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setCargo(request.cargo());
        usuario.setAtivo(request.ativo() == null || request.ativo());
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo(),
                usuario.isAtivo()
        );
    }
}
