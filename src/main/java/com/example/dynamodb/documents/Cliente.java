package com.example.dynamodb.documents;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.List;
import java.util.UUID;

@DynamoDBTable(tableName = "tabela_clientes")
public class Cliente {

    private String id;
    private String nome;
    private SituacaoCliente situacao;
    private List<Contato> contatos;

    public Cliente(String nome, List<Contato> contatos) {
        this.id = UUID.randomUUID().toString();
        this.situacao = SituacaoCliente.ATIVO;
        this.nome = nome;
        this.contatos = contatos;
    }

    @Deprecated
    public Cliente() {
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    public SituacaoCliente getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCliente situacao) {
        this.situacao = situacao;
    }

    @DynamoDBAttribute(attributeName = "contatos")
    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void inativar() {
        this.situacao = SituacaoCliente.INATIVO;
    }
}