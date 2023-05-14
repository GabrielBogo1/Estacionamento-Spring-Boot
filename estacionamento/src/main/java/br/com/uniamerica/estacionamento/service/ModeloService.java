package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import java.beans.Transient;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional (rollbackFor = Exception.class)
    public void validaModelo (final Modelo modelo){

        Assert.isTrue(modelo.getNome().equals(""), "Modelo não pode ser nulo");
        Assert.isTrue(modelo.getNome().length() < 50, "Modelo não pode passar de 50 caracteres");

        this.modeloRepository.save(modelo);

    }

}

