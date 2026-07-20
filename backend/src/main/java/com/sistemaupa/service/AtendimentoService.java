package com.sistemaupa.service;

import com.sistemaupa.dto.AtendimentoRequest;
import com.sistemaupa.entity.Atendimento;
import com.sistemaupa.entity.Paciente;
import com.sistemaupa.enums.StatusAtendimento;
import com.sistemaupa.repository.AtendimentoRepository;
import com.sistemaupa.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final PacienteRepository pacienteRepository;

    public AtendimentoService(
            AtendimentoRepository atendimentoRepository,
            PacienteRepository pacienteRepository
    ) {
        this.atendimentoRepository = atendimentoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public Atendimento criar(AtendimentoRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNome(request.nome());
        paciente.setCpf(request.cpf());
        paciente.setDataNascimento(request.dataNascimento());
        paciente.setTelefone(request.telefone());

        pacienteRepository.save(paciente);

        Atendimento atendimento = new Atendimento();
        atendimento.setPaciente(paciente);
        atendimento.setSenha("TEMP");
        atendimento.setMotivo(request.motivo());
        atendimento.setAmbulancia(request.ambulancia());
        atendimento.setDataHoraEntrada(LocalDateTime.now());
        atendimento.setStatus(StatusAtendimento.AGUARDANDO_TRIAGEM);

        atendimentoRepository.save(atendimento);

        atendimento.setSenha("A-" + String.format("%03d", atendimento.getId()));

        return atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> listar() {
        return atendimentoRepository.findAll();
    }

    public Atendimento buscar(Long id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Atendimento não encontrado."));
    }

    @Transactional
    public Atendimento atualizar(Long id, AtendimentoRequest request) {
        Atendimento atendimento = buscar(id);
        Paciente paciente = atendimento.getPaciente();

        paciente.setNome(request.nome());
        paciente.setCpf(request.cpf());
        paciente.setDataNascimento(request.dataNascimento());
        paciente.setTelefone(request.telefone());
        pacienteRepository.save(paciente);

        atendimento.setMotivo(request.motivo());
        atendimento.setAmbulancia(request.ambulancia());

        return atendimentoRepository.save(atendimento);
    }

    @Transactional
    public void cancelar(Long id) {
        Atendimento atendimento = buscar(id);
        atendimento.setStatus(StatusAtendimento.CANCELADO);
        atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> filaTriagem() {
        return atendimentoRepository
                .findByStatusOrderByDataHoraEntradaAsc(StatusAtendimento.AGUARDANDO_TRIAGEM);
    }

    public List<Atendimento> filaMedico() {
        return atendimentoRepository
                .findByStatusOrderByDataHoraEntradaAsc(StatusAtendimento.AGUARDANDO_MEDICO)
                .stream()
                .sorted(
                        Comparator.comparing(Atendimento::isAmbulancia).reversed()
                                .thenComparing(
                                        atendimento -> atendimento.getClassificacaoRisco().getPrioridade()
                                )
                                .thenComparing(Atendimento::getDataHoraEntrada)
                )
                .toList();
    }

    @Transactional
    public Atendimento alterarStatus(Long id, StatusAtendimento status) {
        Atendimento atendimento = buscar(id);
        atendimento.setStatus(status);
        return atendimentoRepository.save(atendimento);
    }
}
