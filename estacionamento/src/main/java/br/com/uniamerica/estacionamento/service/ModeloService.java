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

        Assert.isTrue(!modelo.getNome().equals(""), "Modelo não pode ser nulo");
        Assert.isTrue(modelo.getNome().length() < 50, "Modelo não pode passar de 50 caracteres");

        this.modeloRepository.save(modelo);

    }

    @Transactional(rollbackFor = Exception.class)
    public void editarModelo(final Long id, final Modelo modelo) {

        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

        if (modeloBanco == null || !modeloBanco.getId().equals(modelo.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.modeloRepository.save(modelo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void excluirModelo (final Long id) {

        final Modelo modeloBanco = this.modeloRepository.findById(id).orElse(null);

        if (modeloBanco == null || !modeloBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.modeloRepository.delete(modeloBanco);
    }
}


