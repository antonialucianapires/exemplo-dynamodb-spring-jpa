package com.example.dynamodb.controller.request;

import java.util.List;

public class ClienteRequest {

    private String nome;
    private List<ContatoRequest> contatos;

    public String getNome() {
        return nome;
    }

    public List<ContatoRequest> getContatos() {
        return contatos;
    }
}
