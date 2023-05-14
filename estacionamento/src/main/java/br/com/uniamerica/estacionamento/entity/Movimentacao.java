package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table (name = "movimentacao", schema = "public")

public class
Movimentacao  extends AbstractEntity{
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo", nullable = false, unique = true)
    private Veículo veiculo;

    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "condutor", nullable = false)
    private Condutor condutor;
    @Getter @Setter
    @Column (name = "entrada", nullable = false, length = 20)
    private LocalDateTime entrada;

    @Getter @Setter
    @Column (name = "saida")
    private LocalDateTime saida;
    @Getter @Setter
    @Column (name = "tempo")
    private LocalDateTime tempo;
    @Getter @Setter
    @Column (name = "tempo, desconto")
    private LocalDateTime tempoDesconto;
    @Getter @Setter
    @Column (name = "tempo, multa")
    private LocalDateTime tempoMulta;
    @Getter @Setter
    @Column (name = "valor, desconto")
    private BigDecimal valorDesconto;
    @Getter @Setter
    @Column (name = "valor, multa")
    private BigDecimal valorMulta;
    @Getter @Setter
    @Column (name = "valor, total")
    private BigDecimal valorTotal;
    @Getter @Setter
    @Column (name = "valor, hora")
    private BigDecimal valorHora;
    @Getter @Setter
    @Column (name = "valor, hora, multa")
    private BigDecimal valorHoraMulta;

}
