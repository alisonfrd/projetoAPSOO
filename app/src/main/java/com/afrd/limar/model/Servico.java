package com.afrd.limar.model;

import java.io.Serializable;

public class Servico implements Serializable, Comparable<Servico>{
    private String codigo;
    private String descricao;
    private double valor;
    private String key;
    public Servico(String codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public int compareTo(Servico o) {
        if(this.getDescricao().compareToIgnoreCase(o.getDescricao()) > 0){
            return 1;
        }else if(this.getDescricao().compareToIgnoreCase(o.getDescricao()) < 0){
            return -1;
        }
        return 0;
    }
}
