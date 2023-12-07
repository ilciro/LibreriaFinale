package web.bean;

import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Raccolta;

import java.sql.SQLException;

public class GiornaleBean {

    private GiornaleDao gD=new GiornaleDao();
    private ObservableList<Raccolta> listaGiornaliB;
    private int idB;
    private String titoloB;

    public ObservableList<Raccolta> getListaGiornaliB() {
        return listaGiornaliB;
    }

    public void setListaGiornaliB(ObservableList<Raccolta> listaGiornaliB) {
        this.listaGiornaliB = listaGiornaliB;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) throws SQLException {
        if(idB<1 || idB>lunghezzaLista())
        {
            this.idB=0;
            setMexB( new IdException("id incorrect"));

        }

        this.idB = idB;
    }

    public String getTitoloB() {
        return titoloB;
    }

    public void setTitoloB(String titoloB) {
        this.titoloB = titoloB;
    }

    private int lunghezzaLista() throws SQLException {
        return gD.getGiornali().size();
    }
    private Exception mexB;

    public Exception getMexB() {
        return mexB;
    }

    public void setMexB(Exception mexB) {
        this.mexB = mexB;
    }
}
