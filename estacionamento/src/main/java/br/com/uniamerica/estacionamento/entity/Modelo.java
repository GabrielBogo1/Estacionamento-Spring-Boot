package br.com.uniamerica.estacionamento.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "modelo", schema = "public")
public class Modelo extends  AbstractEntity {
    @Getter @Setter
    @Column (name = "nome", unique = true, length = 50, nullable = false)
    @NotNull (message = "Nome do modelo não pode ser nulo")
    private String nome;
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull (message = "Marca não pode ser nulo")
    private Marca marca;

}
