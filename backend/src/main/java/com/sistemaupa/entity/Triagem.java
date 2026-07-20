package com.sistemaupa.entity;

import com.sistemaupa.enums.ClassificacaoRisco;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "triagens")
public class Triagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "atendimento_id", unique = true)
    private Atendimento atendimento;

    @Column(columnDefinition = "TEXT")
    private String sintomas;

    @Column(length = 20)
    private String pressaoArterial;

    private Double temperatura;

    private Integer frequenciaCardiaca;

    private Integer nivelDor;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ClassificacaoRisco classificacaoRisco;

    @Column(nullable = false)
    private LocalDateTime dataHoraTriagem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Atendimento getAtendimento() { return atendimento; }
    public void setAtendimento(Atendimento atendimento) { this.atendimento = atendimento; }

    public String getSintomas() { return sintomas; }
    public void setSintomas(String sintomas) { this.sintomas = sintomas; }

    public String getPressaoArterial() { return pressaoArterial; }
    public void setPressaoArterial(String pressaoArterial) { this.pressaoArterial = pressaoArterial; }

    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

    public Integer getFrequenciaCardiaca() { return frequenciaCardiaca; }
    public void setFrequenciaCardiaca(Integer frequenciaCardiaca) { this.frequenciaCardiaca = frequenciaCardiaca; }

    public Integer getNivelDor() { return nivelDor; }
    public void setNivelDor(Integer nivelDor) { this.nivelDor = nivelDor; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public ClassificacaoRisco getClassificacaoRisco() { return classificacaoRisco; }
    public void setClassificacaoRisco(ClassificacaoRisco classificacaoRisco) { this.classificacaoRisco = classificacaoRisco; }

    public LocalDateTime getDataHoraTriagem() { return dataHoraTriagem; }
    public void setDataHoraTriagem(LocalDateTime dataHoraTriagem) { this.dataHoraTriagem = dataHoraTriagem; }
}
