package br.com.uniamerica.estacionamento.controller;


import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/api/modelo")
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRep;

    @Autowired
    private ModeloService modeloService;
    @GetMapping("/{id}")
    public ResponseEntity<Modelo> findByIDPath (@PathVariable("id") final Long id) {
        final Modelo modelo = this.modeloRep.findById(id).orElse(null);
        return ResponseEntity.ok(modelo);
    }

    // http://localhost:8080/api/modelo/1

    // http://localhost:8080/api/modelo?id=1


    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.modeloRep.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar (@Valid @RequestBody final Modelo modelo) {
        try {
            modeloService.validaModelo(modelo);
            return ResponseEntity.ok("Modelo cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping ({"/{id}"})
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @Valid @RequestBody final Modelo modelo) {
        try {
            modeloService.validaModelo(modelo);

            final Modelo modelo1 = this.modeloRep.findById(id).orElse(null);
            if (modelo1 == null || !modelo1.getId().equals(modelo.getId())) {
                throw new RuntimeException("Nao foi possivel identificar o modelo informado");
            }
            this.modeloRep.save(modelo);
            return ResponseEntity.ok("Modelo cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ){
        try {
            this.modeloService.excluirModelo(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

//    @DeleteMapping ("/{id}")
//    public void deletaModelo(@PathVariable ("id") final Long id) {
//        Optional<Modelo> modeloOptional = modeloRep.findById(id);
//        if (modeloOptional.isPresent()) {
//            Modelo modelo = modeloOptional.get();
//            if (!modelo.isAtivo()) {
//                modeloRep.delete(modelo);
//            } else {
//                modelo.setAtivo(false);
//                modeloRep.save(modelo);
//            }
//        }
//        }
    }

