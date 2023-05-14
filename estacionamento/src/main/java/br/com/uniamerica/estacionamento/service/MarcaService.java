package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MarcaService {

    private MarcaRepository marcaRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaMarca (Marca marca)
    {
        Assert.isTrue(marca.getNome().equals(""), "Marca não pode ser nulo");
        Assert.isTrue(marca.getNome().length() > 50, "Marca maior que 50 caracteres");


        this.marcaRepository.save(marca);
    }





}
