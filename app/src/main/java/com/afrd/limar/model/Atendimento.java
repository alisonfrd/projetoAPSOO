package com.afrd.limar.model;

import java.util.Date;

public class Atendimento {
    private int numAntendimento;
    private String descricaoAtendimento;
    private Date dataIncio;
    private Date horaInicio;
    private Date horaFim;
    private String status;

    public Atendimento(int numAntendimento, String descricaoAtendimento, Date dataIncio, Date horaInicio, Date horaFim, String status) {
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

    public Date getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(Date dataIncio) {
        this.dataIncio = dataIncio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
