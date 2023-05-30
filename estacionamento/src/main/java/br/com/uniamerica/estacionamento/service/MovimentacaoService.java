package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public void validaMovimentacao (Movimentacao movimentacao)
    {
        Assert.isTrue(!movimentacao.getVeiculo().equals(""), "Veículo não pode ser nulo");

        Assert.isTrue(!movimentacao.getCondutor().equals(""), "Condutor não pode ser nulo");
    }
}
