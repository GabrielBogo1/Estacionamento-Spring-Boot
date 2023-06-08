package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Veículo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import br.com.uniamerica.estacionamento.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/api/veiculo")
@Validated
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRep;

    @Autowired
    private VeiculoService veiculoService;
    @GetMapping("/{id}")
    public ResponseEntity<Veículo> findByIDPath (@PathVariable("id") final Long id) {
        final Veículo veiculo = this.veiculoRep.findById(id).orElse(null);
        return ResponseEntity.ok(veiculo);
    }

    // http://localhost:8080/api/veiculo/1

    // http://localhost:8080/api/veiculo?id=1


    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.veiculoRep.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@Valid @RequestBody final Veículo veiculo) {
        try {
            veiculoService.validaVeiculo(veiculo);
            return ResponseEntity.ok("Veículo cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @Valid @RequestBody final Veículo veiculo) {
        try {
            veiculoService.validaVeiculo(veiculo);
            final Veículo veiculo1 = this.veiculoRep.findById(id).orElse(null);
            if (veiculo1 == null || !veiculo1.getId().equals(veiculo.getId())) {
                throw new RuntimeException("Nao foi possivel identificar o registro informado");
            }
            this.veiculoRep.save(veiculo);
            return ResponseEntity.ok("Veículo atualizado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping ("delete/{id}")
    public void deletaVeiculo(@PathVariable Long id) {
        Optional<Veículo> veiculoOptional = veiculoRep.findById(id);
        if (veiculoOptional.isPresent()) {
            Veículo veiculo = veiculoOptional.get();
            if (!veiculo.isAtivo()) {
                veiculoRep.delete(veiculo);
            } else {
                veiculo.setAtivo(false);
                veiculoRep.save(veiculo);
            }
        }
    }

        }
