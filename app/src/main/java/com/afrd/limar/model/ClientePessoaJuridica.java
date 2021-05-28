package com.afrd.limar.model;

public class ClientePessoaJuridica extends Cliente{
    private String cnpj;
    private String inscricaoEstadual;

    public ClientePessoaJuridica(String nome, String endereco, String telefoneFixo, String celular, String cidade, String email, String cnpj, String inscricaoEstadual) {
        super(nome, endereco, telefoneFixo, celular, cidade, email);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
