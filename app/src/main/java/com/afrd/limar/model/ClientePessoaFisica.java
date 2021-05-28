package com.afrd.limar.model;

import java.util.Date;

public class ClientePessoaFisica extends Cliente{
    private String cpf;
    private String dataNascimento;

    public ClientePessoaFisica(String nome, String endereco, String telefoneFixo, String celular, String cidade, String email, String cpf, String dataNascimento) {
        super(nome, endereco, telefoneFixo, celular, cidade, email);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
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
}
