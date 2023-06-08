package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/api/configuracao")
public class ConfiguracaoController {
    @Autowired
    private ConfiguracaoRepository configRep;

    @Autowired
    private ConfiguracaoService configuracaoService;
    @GetMapping("/{id}")
    public ResponseEntity<Configuracao> findByIDPath (@PathVariable("id") final Long id) {
        final Configuracao configuracao = this.configRep.findById(id).orElse(null);
        return ResponseEntity.ok(configuracao);
    }

    // http://localhost:8080/api/configuracao/1

    // http://localhost:8080/api/configuracao?id=1


    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.configRep.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Configuracao configuracao) {
        try {
            this.configRep.save(configuracao);
            this.configuracaoService.total(configuracao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Configuracao configuracao) {
        try {
            final Configuracao configuracao1 = this.configRep.findById(id).orElse(null);

            if (configuracao1 == null || !configuracao1.getId().equals(configuracao.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.configRep.save(configuracao);
            this.configuracaoService.total(configuracao);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping ("delete/{id}")

    public void deletaConfiguracao(@PathVariable Long id) {
        Optional<Configuracao> configuracaoOptional = configRep.findById(id);
        if (configuracaoOptional.isPresent()) {
            Configuracao configuracao = configuracaoOptional.get();
            if (!configuracao.isAtivo()) {
                configRep.delete(configuracao);
            } else {
                configuracao.setAtivo(false);
                configRep.save(configuracao);
            }
        }
    }

}


