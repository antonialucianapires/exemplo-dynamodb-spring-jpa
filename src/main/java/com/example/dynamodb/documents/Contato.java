package com.example.dynamodb.documents;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;


@DynamoDBDocument
public class Contato {

    private TipoContato tipo;
    private String valor;

    public Contato(TipoContato tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Contato() {
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "tipo")
    public TipoContato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContato tipo) {
        this.tipo = tipo;
    }

    @DynamoDBAttribute(attributeName = "valor")
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}