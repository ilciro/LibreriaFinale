package web.bean;

import javafx.collections.ObservableList;
import laptop.model.raccolta.Raccolta;

public class RivistaBean {
    private ObservableList<Raccolta> listaRivisteB;
    private int idB;
    private String titoloB;
    private Exception mexB;

    public ObservableList<Raccolta> getListaRivisteB() {
        return listaRivisteB;
    }

    public void setListaRivisteB(ObservableList<Raccolta> listaRivisteB) {
        this.listaRivisteB = listaRivisteB;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public String getTitoloB() {
        return titoloB;
    }

    public void setTitoloB(String titoloB) {
        this.titoloB = titoloB;
    }

    public Exception getMexB() {
        return mexB;
    }

    public void setMexB(Exception mexB) {
        this.mexB = mexB;
    }
}
