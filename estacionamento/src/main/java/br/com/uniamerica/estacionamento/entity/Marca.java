package br.com.uniamerica.estacionamento.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table (name = "marcas", schema = "public")
public class Marca extends AbstractEntity {
    @Getter @Setter
    @Column (name = "nome_marca" , unique = true, length = 50, nullable = false)
    @NotNull(message = "Nome da marca não pode ser nulo")
    private String nome;
}
