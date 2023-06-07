package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;


    public void validaMovimentacao (Movimentacao movimentacao)
    {
        Assert.isTrue(!movimentacao.getVeiculo().equals(""), "Veículo não pode ser nulo");

        Assert.isTrue(!movimentacao.getCondutor().equals(""), "Condutor não pode ser nulo");

         Duration duration = Duration.between(movimentacao.getEntrada(), movimentacao.getSaida());
         // A variável duration armazena os valores entre entrada e saida.

         String horasS = String.format("%02d", duration.toHoursPart());
         String minutosS = String.format("%02d", duration.toMinutesPart());
         String segundosS = String.format("%02d", duration.toSecondsPart());

         float horasF = Float.parseFloat(horasS);

         float minutosF = Float.parseFloat(minutosS);
         float minutosParaHoras = minutosF / 60;

         float segundosF = Float.parseFloat(segundosS);
         float segundosParaHoras = segundosF / 3600;

         float valorTotal = configuracaoService.configTotal;
    }
}
