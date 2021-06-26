package com.afrd.limar.model;

import com.afrd.limar.activity.MateriaisActivity;

import java.io.Serializable;

public class Materiais implements Serializable, Comparable<Materiais>{
    private String key;
    private int id;
    private int quantidade;
    private String fornecedor;
    private String descricao;
    private String unidade;
    private double valorCusto;
    private double valorVenda;

    public Materiais() {
    }

    public Materiais(int id, int quantidade, String fornecedor, String descricao, String unidade, double valorCusto, double valorVenda) {
        this.id = id;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
        this.descricao = descricao;
        this.unidade = unidade;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public int compareTo(Materiais o) {
        if(this.getDescricao().compareToIgnoreCase(o.getDescricao()) > 0){
            return 1;
        }else if(this.getDescricao().compareToIgnoreCase(o.getDescricao()) < 0){
            return -1;
        }
        return 0;
    }
}
