package com.sistemaupa.entity;

import com.sistemaupa.enums.StatusSolicitacao;
import com.sistemaupa.enums.TipoSolicitacao;
import jakarta.persistence.*;

@Entity
@Table(name = "solicitacoes")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoSolicitacao tipo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 20)
    private String prioridade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusSolicitacao status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Prontuario getProntuario() { return prontuario; }
    public void setProntuario(Prontuario prontuario) { this.prontuario = prontuario; }

    public TipoSolicitacao getTipo() { return tipo; }
    public void setTipo(TipoSolicitacao tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }

    public StatusSolicitacao getStatus() { return status; }
    public void setStatus(StatusSolicitacao status) { this.status = status; }
}
