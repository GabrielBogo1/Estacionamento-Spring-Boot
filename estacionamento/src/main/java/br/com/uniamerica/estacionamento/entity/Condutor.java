package br.com.uniamerica.estacionamento.entity;

import br.com.uniamerica.estacionamento.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Label;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalTime;

@Entity
//@Audited
@Table(name = "condutores", schema = "public")
//@AuditTable (value = "condutores_audit", schema = "audit")
public class Condutor extends AbstractEntity
{
    @Getter @Setter
    @Column (name = "nome", nullable = false, length = 50)
    @NotNull (message = "Nome não pode ser nulo")
    private String nome;

    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true , length = 14)
    @CPF (message = "CPF inválido!")
    private String cpf;
    @Getter @Setter
    @Column (name = "telefone", nullable = false, length = 17)
    @NotNull (message = "Telefone não pode ser nulo")
    @Size (max= 11, min = 11, message = "Telefone inválido")
    private String telefone;

    @Getter @Setter
    @Column (name = "tempo_pago", nullable = false)
    private LocalTime tempoPago;
    @Getter @Setter
    @Column (name = "tempo_desconto", nullable = false)
    private LocalTime tempoDesconto;
}

