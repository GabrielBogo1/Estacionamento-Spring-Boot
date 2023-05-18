package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "modelo", schema = "public")
public class Modelo extends  AbstractEntity {
    @Getter @Setter
    @Column (name = "nome", unique = true, length = 50, nullable = false)
    private String nome;
    @Getter @Setter
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column (name = "marca", nullable = false)
    private Marca marca;

}
