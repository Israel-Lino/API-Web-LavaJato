package com.lavajatoapi.projeto.controller;

import com.lavajatoapi.projeto.entities.Clientes;
import com.lavajatoapi.projeto.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/hello")
public class ClientesController {

    private final ClienteService clienteService;

    public ClientesController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<Clientes>> listar() {
        return ResponseEntity.status(200).body(clienteService.listarCliente());
    }

    @PostMapping
    public ResponseEntity<Clientes> adicionarCliente(@Valid @RequestBody Clientes clientes) {
        return ResponseEntity.status(201).body(clienteService.cadastrarCliente(clientes));
    }

    @PutMapping
    public ResponseEntity<Clientes> atualizarCliente(@Valid @RequestBody Clientes clientes) {
        return ResponseEntity.status(200).body(clienteService.editarCliente(clientes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Clientes> login(@Valid @RequestBody Clientes clientes) {
        boolean valido = clienteService.validarSenha(clientes.getId(), clientes.getSenha());
        if(!valido){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
        return getStringStringMap(exception);
    }

    static Map<String, String> getStringStringMap(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
