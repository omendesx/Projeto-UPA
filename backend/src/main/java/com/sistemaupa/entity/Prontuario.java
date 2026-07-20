package com.sistemaupa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "atendimento_id", unique = true)
    private Atendimento atendimento;

    @Column(columnDefinition = "TEXT")
    private String observacaoMedica;

    @Column(columnDefinition = "TEXT")
    private String hipoteseClinica;

    @Column(columnDefinition = "TEXT")
    private String conduta;

    @Column(nullable = false)
    private LocalDateTime dataHoraAtendimento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Atendimento getAtendimento() { return atendimento; }
    public void setAtendimento(Atendimento atendimento) { this.atendimento = atendimento; }

    public String getObservacaoMedica() { return observacaoMedica; }
    public void setObservacaoMedica(String observacaoMedica) { this.observacaoMedica = observacaoMedica; }

    public String getHipoteseClinica() { return hipoteseClinica; }
    public void setHipoteseClinica(String hipoteseClinica) { this.hipoteseClinica = hipoteseClinica; }

    public String getConduta() { return conduta; }
    public void setConduta(String conduta) { this.conduta = conduta; }

    public LocalDateTime getDataHoraAtendimento() { return dataHoraAtendimento; }
    public void setDataHoraAtendimento(LocalDateTime dataHoraAtendimento) { this.dataHoraAtendimento = dataHoraAtendimento; }
}
