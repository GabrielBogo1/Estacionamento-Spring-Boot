package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Veículo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository <Veículo, Long> {
}
