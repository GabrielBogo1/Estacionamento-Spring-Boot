package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table (name = "movimentacao", schema = "public")
public class Movimentacao  extends AbstractEntity{
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo", nullable = false, unique = true)
    private Veículo veiculo;

    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "condutor", nullable = false)
    private Condutor condutor;
    @Getter @Setter
    @Column (name = "entrada", nullable = false)
    private LocalDateTime entrada;

    @Getter @Setter
    @Column (name = "saida")
    private LocalDateTime saida;
    @Getter @Setter
    @Column (name = "tempo")
    private LocalTime tempo;
    @Getter @Setter
    @Column (name = "tempo_desconto")
    private LocalTime tempoDesconto;
    @Getter @Setter
    @Column (name = "tempo_multa")
    private LocalTime tempoMulta;
    @Getter @Setter
    @Column (name = "valor_desconto")
    private BigDecimal valorDesconto;
    @Getter @Setter
    @Column (name = "valor_multa")
    private BigDecimal valorMulta;
    @Getter @Setter
    @Column (name = "valor_total")
    private BigDecimal valorTotal;
    @Getter @Setter
    @Column (name = "valor_hora")
    private BigDecimal valorHora;
    @Getter @Setter
    @Column (name = "valor_hora_multa")
    private BigDecimal valorHoraMulta;

}
