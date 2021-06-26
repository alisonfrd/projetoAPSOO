package com.afrd.limar.model;

import java.util.Date;

public class Atendimento {
    private int numAntendimento ;
    private String descricaoAtendimento;
    private String dataIncio;
    private String horaInicio;
    private String horaFim;
    private String status;

    public Atendimento(int numAntendimento, String descricaoAtendimento, String dataIncio, String horaInicio, String horaFim, String status) {
        this.numAntendimento = numAntendimento;
        this.descricaoAtendimento = descricaoAtendimento;
        this.dataIncio = dataIncio;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.status = status;
    }

    public int getNumAntendimento() {
        return numAntendimento;
    }

    public void setNumAntendimento(int numAntendimento) {
        this.numAntendimento = numAntendimento;
    }

    public String getDescricaoAtendimento() {
        return descricaoAtendimento;
    }

    public void setDescricaoAtendimento(String descricaoAtendimento) {
        this.descricaoAtendimento = descricaoAtendimento;
    }

    public String getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(String dataIncio) {
        this.dataIncio = dataIncio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
