package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(value = "/api/marca")
public class
MarcaController {

    @Autowired // Permite a utilização dos metodos de determinada classe, sem precisar instanciala, fazendo a injeçaõ de dependência
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaService marcaService;
    @GetMapping("/{id}")
        public ResponseEntity<Marca> findByIDPath (@PathVariable("id") final Long id) {
            final Marca marca = this.marcaRepository.findById(id).orElse(null);
            return ResponseEntity.ok(marca);
        }

        // http://localhost:8080/api/marca/1

        // http://localhost:8080/api/marca?id=1


        @GetMapping("/lista")
        public ResponseEntity<?> ListaCompleta() {
            return ResponseEntity.ok(this.marcaRepository.findAll());

        }

        @PostMapping
        public ResponseEntity<?> cadastrar (@Valid @RequestBody final Marca marca) {
            try {
                marcaService.validaMarca(marca);
                return ResponseEntity.ok("Marca cadastrada com sucesso");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
            }
        }

        @PutMapping
        public ResponseEntity<?> editar(@RequestParam("id") final Long id, @Valid @RequestBody final Marca marca) {
            try {
                marcaService.validaMarca(marca);

                final Marca marca1 = this.marcaRepository.findById(id).orElse(null);
                if (marca1 == null || !marca1.getId().equals(marca.getId())) {
                    throw new RuntimeException("Nao foi possivel indentificar o registro informado");
                }
                this.marcaRepository.save(marca);
                return ResponseEntity.ok("Marca atualiza com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError()
                        .body("Error: " + e.getMessage());
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
            }
        }

    @DeleteMapping ("delete/{id}")

    public void deletaMarca(@PathVariable Long id) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if (marcaOptional.isPresent()) {
            Marca marca = marcaOptional.get();
            if (!marca.isAtivo()) {
                marcaRepository.delete(marca);
            } else {
                marca.setAtivo(false);
                marcaRepository.save(marca);
            }
        }
    }

    }



