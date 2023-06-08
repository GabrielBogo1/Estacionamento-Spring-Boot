package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.service.ConfiguracaoService;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/api/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRep;

    @Autowired
    private MovimentacaoService movimentacaoService;
    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> findByIDPath(@PathVariable("id") final Long id) {
        final Movimentacao movimentacao = this.movimentacaoRep.findById(id).orElse(null);
        return ResponseEntity.ok(movimentacao);
    }

    // http://localhost:8080/api/movimentacao/1

    // http://localhost:8080/api/movimentacao?id=1


    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.movimentacaoRep.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody final Movimentacao movimentacao) {
        try {
            this.movimentacaoRep.save(movimentacao);
            return ResponseEntity.ok("Movimentação cadastrada com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @Valid @RequestBody final Movimentacao movimentacao) {
        try {
            final Movimentacao movimentacao1 = this.movimentacaoRep.findById(id).orElse(null);

            if (movimentacao1 == null || !movimentacao1.getId().equals(movimentacao.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return movimentacaoService.finalizarMovimentacao(movimentacao, id);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")

    public void deletaMovimentacao(@PathVariable Long id) {
        Optional<Movimentacao> movimentacaoOptional = movimentacaoRep.findById(id);
        if (movimentacaoOptional.isPresent()) {
            Movimentacao movimentacao = movimentacaoOptional.get();
            if (!movimentacao.isAtivo()) {
                movimentacaoRep.delete(movimentacao);
            } else {
                movimentacao.setAtivo(false);
                movimentacaoRep.save(movimentacao);
            }

        }
    }
}


