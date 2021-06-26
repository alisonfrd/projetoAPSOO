package com.afrd.limar.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

    public class MaterialEmAtendimento implements Serializable {
    private String descricao, key;
    private double valorVenda;
    private int quantidade;

    public MaterialEmAtendimento() {
    }

    public MaterialEmAtendimento(String descricao, String key, double valorVenda, int quantidade) {
        this.descricao = descricao;
        this.key = key;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;


    }

    public String getDescricao() {
        return descricao;
    }

    public String getKey() {
        return key;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
