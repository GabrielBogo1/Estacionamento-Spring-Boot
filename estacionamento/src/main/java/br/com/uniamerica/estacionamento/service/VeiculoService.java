package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veículo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaVeiculo (Veículo veiculo)
    {
        Assert.isTrue(veiculo.getPlaca().length() <= 7, "Placa inválida.");
        this.veiculoRepository.save(veiculo);
    }


}
