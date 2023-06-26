package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Veículo;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Year;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    private Veículo veiculo;
    final int anoLimite = Year.now().getValue() + 1;
    @Transactional(rollbackFor = Exception.class)
    public void validaVeiculo (Veículo veiculo)
    {
        Assert.isTrue(veiculo.getPlaca().length() <= 7, "Placa inválida.");

        Assert.isTrue(veiculo.getAno() <= anoLimite, "Ano limite excedido");

        veiculo.setAtivo(true);

        this.veiculoRepository.save(veiculo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editarVeiculo (final Long id, final Veículo veiculo) {

        final Veículo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);

        if (veiculoBanco == null || !veiculoBanco.getId().equals(veiculo.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.veiculoRepository.save(veiculo);
    }
    @Transactional(rollbackFor = Exception.class)
    public void excluirVeiculo(final Long id) {

        final Veículo veiculoBanco = this.veiculoRepository.findById(id).orElse(null);

        if (veiculoBanco == null || !veiculoBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.veiculoRepository.delete(veiculoBanco);
    }


}
