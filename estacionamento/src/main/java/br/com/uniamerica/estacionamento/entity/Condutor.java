package br.com.uniamerica.estacionamento.entity;

import br.com.uniamerica.estacionamento.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Label;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
//@Audited
@Table(name = "condutores", schema = "public")
//@AuditTable (value = "condutores_audit", schema = "audit")
public class Condutor extends AbstractEntity
{
    @Getter @Setter
    @Column (name = "nome", nullable = false, length = 50)
    private String nome;

    @Getter @Setter
    @Column (name = "cpf", nullable = false, unique = true , length = 15)
    private String cpf;
    @Getter @Setter
    @Column (name = "telefone", nullable = false, unique = true, length = 17)
    private String telefone;

    @Getter @Setter
    @Column (name = "tempo,pago")
    private LocalTime tempoPago;
    @Getter @Setter
    @Column (name = "tempo, desconto")
    private LocalTime tempoDesconto;
}

