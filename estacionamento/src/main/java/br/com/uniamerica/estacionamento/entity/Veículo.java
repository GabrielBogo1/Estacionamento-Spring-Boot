package br.com.uniamerica.estacionamento.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Table (name = "veiculos", schema = "public")
public class Veículo extends AbstractEntity {
    @Getter @Setter
    @Column (name = "placa", length = 10, nullable = false, unique = true)
    @NotNull (message = "Placa não pode ser nulo")
    private String placa;
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull (message = "Modelo não pode ser nulo")
    private Modelo modelo;
    @Getter @Setter
    @Column (name = "ano" , nullable = false)
    @NotNull (message = "Ano não pode ser nulo")
    @Range (min = 1900, max = 2024, message = "Ano inválido")
    private int ano;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", nullable = false)
    @NotNull (message = "Cor não pode ser nula")
    private Cor cor;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column (name = "tipo" , nullable = false)
    @NotNull (message = "Tipo não pode ser nulo")
    private Tipo tipo;


}
