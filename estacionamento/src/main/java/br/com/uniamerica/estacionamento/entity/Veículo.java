package br.com.uniamerica.estacionamento.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Entity
@Table (name = "veiculos", schema = "public")
public class Veículo extends AbstractEntity {
    @Getter @Setter
    @Column (name = "placa", length = 10, nullable = false, unique = true)
    @Size (min = 1,message = "Placa não pode ser nulo")
    @Pattern(regexp = "/^[a-zA-Z-ZÀ-ÿ\\s]/;", message = "Placa não pode conter caracteres especiais")
    private String placa;
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    private Modelo modelo;
    @Getter @Setter
    @Column (name = "ano" , nullable = false)
    @Min(1886)
    private int ano;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column(name = "cor", nullable = false)
    private Cor cor;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    @Column (name = "tipo" , nullable = false)
    private Tipo tipo;


}
