package web.bean;

import javafx.collections.ObservableList;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Raccolta;

import java.sql.SQLException;

public class RivistaBean {
    private ObservableList<Raccolta> listaRivisteB;
    private int idB;
    private String titoloB;
    private Exception mexB;
    private final RivistaDao rD=new RivistaDao();

    public ObservableList<Raccolta> getListaRivisteB() {
        return listaRivisteB;
    }

    public void setListaRivisteB(ObservableList<Raccolta> listaRivisteB) {
        this.listaRivisteB = listaRivisteB;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) throws IdException, SQLException {
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

    public Exception getMexB() {
        return mexB;
    }

    public void setMexB(Exception mexB) {
        this.mexB = mexB;
    }
    private int lunghezzaLista() throws SQLException {
        return  rD.getRiviste().size();

    }
}
