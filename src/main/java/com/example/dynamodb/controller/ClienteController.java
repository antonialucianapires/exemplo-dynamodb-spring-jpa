package com.example.dynamodb.controller;

import com.example.dynamodb.controller.request.ClienteRequest;
import com.example.dynamodb.documents.Cliente;
import com.example.dynamodb.documents.Contato;
import com.example.dynamodb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<Object> criarCliente(@RequestBody ClienteRequest request) {

        try {

            var cliente =
                    new Cliente(request.getNome(), request.getContatos().stream()
                            .map(contatoRequest -> new Contato(contatoRequest.getTipo(), contatoRequest.getValor()))
                            .collect(Collectors.toList()));

            var clienteSalvo = this.repository.save(cliente);
            return ResponseEntity.status(CREATED).body(clienteSalvo);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Erro ao criar novo cliente.");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> consultarCliente(@PathVariable(name = "id") String id) {

        try {

            var cliente = this.repository.findById(id);

            if (cliente.isPresent()) {
                return ResponseEntity.status(OK).body(cliente.get());
            }

            return ResponseEntity.status(NOT_FOUND).body("Cliente não encontrado para o identificador " + id);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Erro ao consultar cliente.");
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> invativarCliente(@PathVariable(name = "id") String id) {

        try {

            var cliente = this.repository.findById(id);

            if (cliente.isPresent()) {

                cliente.get().inativar();

                this.repository.save(cliente.get());

                return ResponseEntity.status(OK).body(cliente.get());
            }

            return ResponseEntity.status(NOT_FOUND).body("Cliente não encontrado para o identificador " + id);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Erro ao inativar cliente.");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable(name = "id") String id) {

        try {

            var cliente = this.repository.findById(id);

            if (cliente.isPresent()) {

                this.repository.delete(cliente.get());

                return ResponseEntity.status(OK).body("Cliente com identificador " + id + " foi excluído com sucesso");
            }

            return ResponseEntity.status(NOT_FOUND).body("Cliente não encontrado para o identificador " + id);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body("Erro ao excluir cliente.");
        }

    }


}
