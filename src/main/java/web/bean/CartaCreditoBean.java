package web.bean;

import java.util.Date;

public class CartaCreditoBean {
    private String nomeB;
    private String civB;
    private String numeroCCB;
    private Date dataScadB;
    private float prezzoTransazioneB;
    private String cognomeB;

    public String getNomeB() {
        return nomeB;
    }

    public void setNomeB(String nomeB) {
        this.nomeB = nomeB;
    }

    public String getCivB() {
        return civB;
    }

    public void setCivB(String civB) {
        this.civB = civB;
    }

    public String getNumeroCCB() {
        return numeroCCB;
    }

    public void setNumeroCCB(String numeroCCB) {
        this.numeroCCB = numeroCCB;
    }

    public Date getDataScadB() {
        return dataScadB;
    }

    public void setDataScadB(Date dataScadB) {

        this.dataScadB = dataScadB;
    }

    public float getPrezzoTransazioneB() {
        return prezzoTransazioneB;
    }

    public void setPrezzoTransazioneB(float prezzoTransazioneB) {
        this.prezzoTransazioneB = prezzoTransazioneB;
    }

    public String getCognomeB() {
        return cognomeB;
    }

    public void setCognomeB(String cognomeB) {
        this.cognomeB = cognomeB;
    }
}
