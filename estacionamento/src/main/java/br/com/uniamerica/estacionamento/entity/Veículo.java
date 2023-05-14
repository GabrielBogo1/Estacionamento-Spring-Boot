package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "veiculos", schema = "public")
public class Veículo extends AbstractEntity {
    @Getter @Setter
    @Column (name = "placa", length = 8, nullable = false, unique = true)
    private String placa;
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Modelo modelo;
    @Getter @Setter
    @Column (name = "ano" , nullable = false)
    private int ano;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", length = 20, nullable = false)
    private Cor cor;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column (name = "tipo" , length = 20, nullable = false)
    private Tipo tipo;
}
