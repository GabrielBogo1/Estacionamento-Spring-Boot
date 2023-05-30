package br.com.uniamerica.estacionamento.entity;

import br.com.uniamerica.estacionamento.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]*$", message = "Nome não pode conter caracteres especiais")
    @Size (min= 1, message = "Nome não pode ser nulo")
    private String nome;
    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true , length = 14)
    @CPF (message = "CPF inválido!")
    private String cpf;
    @Getter @Setter
    @Column (name = "telefone", nullable = false, length = 17)
    @Size (max= 14, min = 14, message = "Telefone inválido")
    @Pattern(regexp = "\\(?\\d{2,}\\)?[ -]?\\d{4,}[\\-\\s]?\\d{4}", message = "Formato de telefone inválido")
    private String telefone;

    @Getter @Setter
    @Column (name = "tempo_pago")
    private LocalTime tempoPago;
    @Getter @Setter
    @Column (name = "tempo_desconto")
    private LocalTime tempoDesconto;
}

