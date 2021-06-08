package com.afrd.limar.model;

import java.io.Serializable;

public class ClientePessoaJuridica extends Cliente implements Serializable, Comparable<ClientePessoaJuridica> {
    private String cnpj;
    private String inscricaoEstadual;
    private String key;

    public ClientePessoaJuridica() {
    }

    public ClientePessoaJuridica(String nome, String endereco, String telefoneFixo, String celular, String cidade, String email, String cnpj, String inscricaoEstadual) {
        super(nome, endereco, telefoneFixo, celular, cidade, email);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    @Override
    public int compareTo(ClientePessoaJuridica o) {
        if(this.getNome().compareToIgnoreCase(o.getNome()) > 0){
            return 1;
        }else if(this.getNome().compareToIgnoreCase(o.getNome()) < 0){
            return -1;
        }
        return 0;
    }
}
