package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "modelo", schema = "public")
public class Modelo extends  AbstractEntity {
    @Getter @Setter
    @Column (name = "nome", length = 50, nullable = false)
    private String nome;
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Marca marca;

}
