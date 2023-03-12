package com.example.dynamodb.controller.request;

import com.example.dynamodb.documents.TipoContato;

public class ContatoRequest {
    private TipoContato tipo;
    private String valor;

    public TipoContato getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

}
