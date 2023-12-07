package web.bean;

import javafx.collections.ObservableList;
import laptop.database.LibroDao;
import laptop.exception.IdException;
import laptop.model.raccolta.CategorieLibro;
import laptop.model.raccolta.Raccolta;

import java.sql.SQLException;

public class LibroBean {

    private LibroDao lD=new LibroDao();

    private ObservableList<Raccolta> elencoLibriB;

    private Exception mexB;

    private int idB;
    private String titoloB;

    public String getTitoloB() {
        return titoloB;
    }

    public void setTitoloB(String titoloB) {
        this.titoloB = titoloB;
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


    public ObservableList<Raccolta> getElencoLibriB() {
        return elencoLibriB;
    }

    public void setElencoLibriB(ObservableList<Raccolta> elencoLibriB) {
        this.elencoLibriB = elencoLibriB;
    }

    public Exception getMexB() {
        return mexB;
    }

    public void setMexB(Exception mexB) {
        this.mexB = mexB;
    }

    //user for calculate length
    private int lunghezzaLista() throws SQLException {
      return  lD.getLibri().size();

    }

    public String getCategoriaB() {
        return categoriaB;
    }

    public void setCategoriaB(String categoriaB) {
        switch (categoriaB){
            case "ADOLESCENTI_RAGAZZI":
                this.categoriaB = CategorieLibro.ADOLESCENTI_RAGAZZI.toString();
                break;
            case "ARTE":
                this.categoriaB = CategorieLibro.ARTE.toString();
                break;
            case "CINEMA_FOTOGRAFIA" :
                this.categoriaB = CategorieLibro.CINEMA_FOTOGRAFIA.toString();
                break;
            case "BIOGRAFIE" :
                this.categoriaB = CategorieLibro.BIOGRAFIE.toString();
                break;
            case "DIARI_MEMORIE" :
                this.categoriaB = CategorieLibro.DIARI_MEMORIE.toString();
                break;
            case "CALENDARI_AGENDE" :
                this.categoriaB = CategorieLibro.CALENDARI_AGENDE.toString();
                break;
            case "DIRITTO" :
                this.categoriaB = CategorieLibro.DIRITTO.toString();
                break;
            case "DIZINARI_OPERE" :
                this.categoriaB = CategorieLibro.DIZINARI_OPERE.toString();
                break;
            case "ECONOMIA" :
                this.categoriaB = CategorieLibro.ECONOMIA.toString();
                break;
            case "FAMIGLIA" :
                this.categoriaB = CategorieLibro.FAMIGLIA.toString();
                break;
            case "SALUTE_BENESSERE" :
                this.categoriaB = CategorieLibro.SALUTE_BENESSERE.toString();
                break;
            case "FANTASCIENZA_FANTASY" :
                this.categoriaB = CategorieLibro.FANTASCIENZA_FANTASY.toString();
                break;
            case "FUMETTI_MANGA" :
                this.categoriaB = CategorieLibro.FUMETTI_MANGA.toString();
                break;
            case "GIALLI_THRILLER" :
                this.categoriaB = CategorieLibro.GIALLI_THRILLER.toString();
                break;
            case "COMPUTER_GIOCHI" :
                this.categoriaB = CategorieLibro.COMPUTER_GIOCHI.toString();
                break;
            case "HUMOR" :
                this.categoriaB = CategorieLibro.HUMOR.toString();
                break;
            case "INFORMATICA" :
                this.categoriaB = CategorieLibro.INFORMATICA.toString();
                break;
            case "WEB_DIGITAL_MEDIA" :
                this.categoriaB = CategorieLibro.WEB_DIGITAL_MEDIA.toString();
                break;
            case "LETTERATURA_NARRATIVA" :
                this.categoriaB = CategorieLibro.LETTERATURA_NARRATIVA.toString();
                break;
            case "LIBRI_BAMBINI" :
                this.categoriaB = CategorieLibro.LIBRI_BAMBINI.toString();
                break;
            case "LIBRI_SCOLASTICI" :
                this.categoriaB = CategorieLibro.LIBRI_SCOLASTICI.toString();
                break;
            case "LIBRI_UNIVERSITARI" :
                this.categoriaB = CategorieLibro.LIBRI_UNIVERSITARI.toString();
                break;
            case "RICETTARI_GENERALI" :
                this.categoriaB = CategorieLibro.RICETTARI_GENERALI.toString();
                break;
            case "LINGUISTICA_SCRITTURA" :
                this.categoriaB = CategorieLibro.LINGUISTICA_SCRITTURA.toString();
                break;
            case "POLITICA" :
                this.categoriaB = CategorieLibro.POLITICA.toString();
                break;
            case "RELIGIONE" :
                this.categoriaB = CategorieLibro.RELIGIONE.toString();
                break;
            case "ROMANZI_ROSA" :
                this.categoriaB = CategorieLibro.ROMANZI_ROSA.toString();
                break;
            case "SCIENZE" :
                this.categoriaB = CategorieLibro.SCIENZE.toString();
                break;
            case "TECNOLOGIA_MEDICINA" :
                this.categoriaB = CategorieLibro.TECNOLOGIA_MEDICINA.toString();
                break;

            default :
                this.categoriaB = null;
                break;
        }
    }

    private String categoriaB;
}
