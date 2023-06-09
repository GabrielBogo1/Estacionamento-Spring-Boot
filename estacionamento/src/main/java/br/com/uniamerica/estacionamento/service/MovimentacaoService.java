package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ConfiguracaoService configuracaoService;


    public void validaMovimentacao (Movimentacao movimentacao)
    {
        Assert.isTrue(!movimentacao.getVeiculo().equals(""), "Veículo não pode ser nulo");

        Assert.isTrue(!movimentacao.getCondutor().equals(""), "Condutor não pode ser nulo");
    }
    public ResponseEntity<?> finalizarMovimentacao(Movimentacao movimentacao, Long id){

        final Movimentacao movimentacao1 = this.movimentacaoRepository.findById(id).orElse(null);

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

        float valorAPagar = (segundosParaHoras + minutosParaHoras + horasF) * valorTotal;

        movimentacao.setAtivo(false);

        this.movimentacaoRepository.save(movimentacao);

        return ResponseEntity.ok("Horas a pagar: "+ (segundosParaHoras + minutosParaHoras + horasF) +"\n Total a pagar: " + valorAPagar + "\n Hora da entrada: " + movimentacao.getEntrada() + "\n Hora da saida: " + movimentacao.getSaida() +
                "\n Placa do veiculo: " + movimentacao1.getVeiculo().getPlaca()+ "\n Modelo do veiculo: "+ movimentacao1.getVeiculo().getModelo().getNome()+
                "\n Ano do veiculo: "+ movimentacao1.getVeiculo().getAno()+"\n Cor do veiculo: "+ movimentacao1.getVeiculo().getCor()+ "\n Tipo do veiculo: "+movimentacao1.getVeiculo().getTipo()
                +"\n Nome do condutor: "+ movimentacao1.getCondutor().getNome());
    }
}
