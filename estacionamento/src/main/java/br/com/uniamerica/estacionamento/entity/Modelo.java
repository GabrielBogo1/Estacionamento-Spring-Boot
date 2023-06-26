package br.com.uniamerica.estacionamento.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "modelo", schema = "public")
public class Modelo extends  AbstractEntity {
    @Getter @Setter
    @Column (name = "nome", unique = true, length = 50, nullable = false)
    @Size(min = 1, message = "Nome do modelo não pode ser nulo")
    private String nome;
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "modelo_marca",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "modelo_id",
                            "marca_id"
                    }
            ),
            joinColumns = @JoinColumn(
                    name = "modelo_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "marca_id"
            )
    )
    private Marca marca;

}
