package com.lavajatoapi.projeto.controller;

import com.lavajatoapi.projeto.entities.Produtos;
import com.lavajatoapi.projeto.service.ProdutosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.lavajatoapi.projeto.controller.ClientesController.getStringStringMap;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @GetMapping
    public ResponseEntity<List<Produtos>> listarProdutos(){
        return ResponseEntity.status(200).body(produtosService.findAll());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Produtos>> buscarCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(produtosService.listarProdutosPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<Produtos> cadastrarProduto(@Valid @RequestBody Produtos produto){
        return ResponseEntity.status(201).body(produtosService.cadastrarProduto(produto));
    }

    @PutMapping
    public ResponseEntity<Produtos> atualizarProduto(@Valid @RequestBody Produtos produto){
        return ResponseEntity.status(200).body(produtosService.editarProduto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produtos> excluirProduto(@PathVariable Long id){
        produtosService.excluirProduto(id);
        return ResponseEntity.status(204).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }
}
