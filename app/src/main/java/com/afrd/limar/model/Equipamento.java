package com.afrd.limar.model;

import java.io.Serializable;

public class Equipamento implements Serializable {

    private String codEquipamento;
    private String ambiente;
    private String tipoEquipamento;
    private String modelo;
    private String capcidade;
    private String fabricante;
    private String tensaoEmOperação;
    private String nivelDeRuido;
    private String condicoesGerais;
    private String key;

    public Equipamento() {
    }

    public Equipamento(String codEquipamento, String ambiente, String tipoEquipamento, String modelo, String capcidade, String fabricante, String tensaoEmOperação, String nivelDeRuido, String condicoesGerais) {
        this.codEquipamento = codEquipamento;
        this.ambiente = ambiente;
        this.tipoEquipamento = tipoEquipamento;
        this.modelo = modelo;
        this.capcidade = capcidade;
        this.fabricante = fabricante;
        this.tensaoEmOperação = tensaoEmOperação;
        this.nivelDeRuido = nivelDeRuido;
        this.condicoesGerais = condicoesGerais;
    }

    public String getCodEquipamento() {
        return codEquipamento;
    }

    public void setCodEquipamento(String codEquipamento) {
        this.codEquipamento = codEquipamento;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCapcidade() {
        return capcidade;
    }

    public void setCapcidade(String capcidade) {
        this.capcidade = capcidade;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getTensaoEmOperação() {
        return tensaoEmOperação;
    }

    public void setTensaoEmOperação(String tensaoEmOperação) {
        this.tensaoEmOperação = tensaoEmOperação;
    }

    public String getNivelDeRuido() {
        return nivelDeRuido;
    }

    public void setNivelDeRuido(String nivelDeRuido) {
        this.nivelDeRuido = nivelDeRuido;
    }

    public String getCondicoesGerais() {
        return condicoesGerais;
    }

    public void setCondicoesGerais(String condicoesGerais) {
        this.condicoesGerais = condicoesGerais;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
