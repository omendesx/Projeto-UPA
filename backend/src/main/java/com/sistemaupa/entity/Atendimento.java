package com.sistemaupa.entity;

import com.sistemaupa.enums.ClassificacaoRisco;
import com.sistemaupa.enums.StatusAtendimento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @Column(nullable = false, unique = true, length = 20)
    private String senha;

    @Column(nullable = false, length = 255)
    private String motivo;

    @Column(nullable = false)
    private boolean ambulancia;

    @Column(nullable = false)
    private LocalDateTime dataHoraEntrada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private StatusAtendimento status;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ClassificacaoRisco classificacaoRisco;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public boolean isAmbulancia() { return ambulancia; }
    public void setAmbulancia(boolean ambulancia) { this.ambulancia = ambulancia; }

    public LocalDateTime getDataHoraEntrada() { return dataHoraEntrada; }
    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) { this.dataHoraEntrada = dataHoraEntrada; }

    public StatusAtendimento getStatus() { return status; }
    public void setStatus(StatusAtendimento status) { this.status = status; }

    public ClassificacaoRisco getClassificacaoRisco() { return classificacaoRisco; }
    public void setClassificacaoRisco(ClassificacaoRisco classificacaoRisco) { this.classificacaoRisco = classificacaoRisco; }
}
