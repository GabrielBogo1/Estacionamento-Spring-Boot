package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    static float configTotal;

    public void total (Configuracao configuracao)
    {
        Configuracao configuracao1 = configuracaoRepository.findByValorHora(configuracao.getValorHora());

        configTotal = configuracao.getValorHora();
    }

}
