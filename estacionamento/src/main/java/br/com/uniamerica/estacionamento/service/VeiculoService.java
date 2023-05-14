package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veículo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaVeiculo (Veículo veiculo)
    {
        Assert.isTrue(veiculo.getPlaca().length() > 15, "Placa inválida.");
        Assert.isTrue(veiculo.getPlaca().equals(""), "Placa não pode ser nula");

        this.veiculoRepository.save(veiculo);
    }


}
