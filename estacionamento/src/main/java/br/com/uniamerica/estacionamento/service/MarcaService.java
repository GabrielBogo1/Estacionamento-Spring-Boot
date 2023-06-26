package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;
    @Transactional(rollbackFor = Exception.class)
    public void validaMarca (final Marca marca)
    {
        Assert.isTrue(marca.getNome().length() <= 50, "Marca maior que 50 caracteres");
        this.marcaRepository.save(marca);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editarMarca(final Long id, final Marca marca) {

        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);

        if (marcaBanco == null || !marcaBanco.getId().equals(marca.getId())){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.marcaRepository.save(marca);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarMarca(final Long id) {

        final Marca marcaBanco = this.marcaRepository.findById(id).orElse(null);

        if (marcaBanco == null || !marcaBanco.getId().equals(id)){
            throw new RuntimeException("Não foi possivel identificar o registro informado.");
        }

        this.marcaRepository.delete(marcaBanco);
    }
}
