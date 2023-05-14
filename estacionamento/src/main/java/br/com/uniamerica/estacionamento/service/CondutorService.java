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

        Assert.isTrue(!condutor.getNome().equals(""), "Nome não pode ser nulo.");
        Assert.isTrue(!condutor.getCpf().equals(""), "CPF não pode ser nulo.");

        Assert.isTrue(!condutor.getTelefone().equals(""), "Telefone não pode ser nulo.");
        Assert.isTrue(condutor.getTelefone().length() <  17, "Tamanho de telefone inválido.");


        if (this.validarCPF.isCPF(condutor.getCpf()) == false) {
            throw new RuntimeException("Cpf inválido");
        }

        this.condutorRepository.save(condutor);
    }

}
