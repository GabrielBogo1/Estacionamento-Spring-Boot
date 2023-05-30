package br.com.uniamerica.estacionamento.service;


import br.com.uniamerica.estacionamento.configs.ValidaCpf;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private ValidaCpf validarCPF;

    @Transactional(rollbackFor = Exception.class)
    public void validaCondutor (Condutor condutor)
    {
        Assert.isTrue(condutor.getNome().length() <= 50,"Nome maior do que 50 caracteres");

        Assert.isTrue(!condutor.getCpf().equals(""), "CPF não pode ser nulo.");

        Condutor condutorExistente = condutorRepository.findByCpf(condutor.getCpf());

        Assert.isTrue(condutorExistente == null || condutorExistente.equals(condutor.getCpf()), "CPF Já existente");

        this.condutorRepository.save(condutor);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaCondutor (Condutor condutor){

        Assert.isTrue(condutor.getNome().length() <= 50,"Nome maior do que 50 caracteres");
//        Assert.isTrue(!condutor.getNome().equals(""), "Nome não pode ser nulo");

        Assert.isTrue(!condutor.getCpf().equals(""), "CPF não pode ser nulo.");
        Assert.isTrue(condutor.getCpf().equals(condutor.getCpf()), "Já existe um condutor com esse CPF");

        final Condutor condutorBancoDeDados = this.condutorRepository.findById(condutor.getId()).orElse(null);
        condutor.setCadastro(condutorBancoDeDados.getCadastro());

        this.condutorRepository.save(condutor);
    }
}
