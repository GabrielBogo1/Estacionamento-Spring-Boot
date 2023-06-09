package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table (name = "configuracao", schema = "public")
public class Configuracao extends AbstractEntity {
    @Getter @Setter
    @Column (name = "valor_hora")
    private float valorHora;
    @Getter @Setter
    @Column (name = "valor_minuto_hora")
    private BigDecimal valorMinutoMulta;
    @Getter @Setter
    @Column (name = "inicio_expediente")
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column (name = "fim_expediente")
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column (name = "tempo_para_desconto")
    private LocalTime tempoParaDesconto;
    @Getter @Setter
    @Column (name = "tempo_desconto")
    private LocalTime tempoDeDesconto;
    @Getter @Setter
    @Column (name = "gerar_desconto")
    private boolean gerarDesconto;
}
