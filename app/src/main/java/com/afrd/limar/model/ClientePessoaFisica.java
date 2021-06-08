package com.afrd.limar.model;

import java.io.Serializable;
import java.util.Date;

public class ClientePessoaFisica extends Cliente implements Serializable, Comparable<ClientePessoaFisica> {
    private String cpf;
    private String dataNascimento;
    private String key;

    public ClientePessoaFisica() {
    }

    public ClientePessoaFisica(String nome, String endereco, String telefoneFixo, String celular, String cidade, String email, String cpf, String dataNascimento) {
        super(nome, endereco, telefoneFixo, celular, cidade, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int compareTo(ClientePessoaFisica o) {
        if(this.getNome().compareToIgnoreCase(o.getNome()) > 0){
            return 1;
        }else if(this.getNome().compareToIgnoreCase(o.getNome()) < 0){
            return -1;
        }
        return 0;
    }
}
