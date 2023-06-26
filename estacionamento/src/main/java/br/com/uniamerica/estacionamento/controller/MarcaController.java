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
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaService marcaService;

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        return marca == null
                ? ResponseEntity.badRequest().body("Nenhum condutor encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(marca);
    }

    /**
     *
     * @return
     */
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.marcaRepository.findAll());
    }

    /**
     *
     * @return
     */
//    @GetMapping("/lista/ativo")
//    public ResponseEntity<?> listaCompletaAtivo(){
//        return ResponseEntity.ok(this.marcaRepository.findByAtivoTrue());
//    }

    /**
     *
     * @param marca
     * @return
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Marca marca){
        try {
            this.marcaService.validaMarca(marca);
            return ResponseEntity.ok("Registro cadastrado com sucesso.");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param marca
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Marca marca){
        try {
            this.marcaService.editarMarca(id, marca);
            return ResponseEntity.ok("Registro atualizado com sucesso. ");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param id            this.marcaRepository.delete(marca);
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ){
        try {
            this.marcaService.deletarMarca(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}






