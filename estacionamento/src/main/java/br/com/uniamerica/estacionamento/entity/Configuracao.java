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
    @Column (name = "valor, hora")
    private BigDecimal valorHora;
    @Getter @Setter
    @Column (name = "valo, minuto, hora")
    private BigDecimal valorMinutoMulta;
    @Getter @Setter
    @Column (name = "inicio, expediente")
    private LocalTime inicioExpediente;
    @Getter @Setter
    @Column (name = "fim, expediente")
    private LocalTime fimExpediente;
    @Getter @Setter
    @Column (name = "tempo, para, desconto")
    private LocalTime tempoParaDesconto;
    @Getter @Setter
    @Column (name = "tempo, desconto")
    private LocalTime tempoDeDesconto;
    @Getter @Setter
    @Column (name = "gerar, desconto")
    private boolean gerarDesconto;
}
