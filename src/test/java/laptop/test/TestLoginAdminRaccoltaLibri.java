package laptop.test;

import laptop.controller.*;
import laptop.database.LibroDao;
import laptop.database.UsersDao;
import laptop.model.User;
import laptop.model.raccolta.Libro;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginAdminRaccoltaLibri {
    private final ControllerLogin cL=new ControllerLogin();
    private final ControllerReportRaccolta cRR=new ControllerReportRaccolta();

    private final ControllerGestionePage cGP=new ControllerGestionePage();

    private final ControllerAggiungiPage cAP=new ControllerAggiungiPage();

    private final ControllerModifPage cMP=new ControllerModifPage();


    private final User u=User.getInstance();

    private final static ControllerSystemState vis=ControllerSystemState.getInstance();


    private final LibroDao lD=new LibroDao();
    private final ResourceBundle rBUser=ResourceBundle.getBundle("configurations/users");
    private final ResourceBundle rBLibro=ResourceBundle.getBundle("configurations/books");

    //test for controller login

    @Test
    void testControllaAdmin() throws SQLException {
        assertTrue(cL.controlla(rBUser.getString("emailA"), rBUser.getString("passA")));
    }
    @Test
    void testGetRuoloTempUSer() throws SQLException {
        assertEquals("A",cL.getRuoloTempUSer(rBUser.getString("emailA")));
    }
    //test for controller report raccolta
    @Test
    void testGetTipo() throws SQLException {
        u.setEmail(rBUser.getString("emailA"));
        u.setIdRuolo(UsersDao.getRuolo(u));
        assertEquals("ADMIN",cRR.getTipo());
    }
    //test for controller Gestione Page
    @Test
    void testGetLista() throws SQLException {
        vis.setTypeAsBook();
        assertNotNull(cGP.getLista(vis.getType()));
    }
    //test for controller aggiungi page
    @Test
    void testCheckDataInsert() throws SQLException {
        String[] info=new String[6];
        String []infoCosti=new String[6];

        info[0]=rBLibro.getString("titolo");
        infoCosti[0]=rBLibro.getString("numPag");
        infoCosti[1]=rBLibro.getString("isbn");
        info[4]=rBLibro.getString("editore");
        info[2]=rBLibro.getString("autore");
        info[3]=rBLibro.getString("lingua");
        info[5]=rBLibro.getString("categoria");
        infoCosti[3]=rBLibro.getString("disp");
        infoCosti[4]= rBLibro.getString("prezzo");
        infoCosti[5]= rBLibro.getString("nrCopie");
        LocalDate date=LocalDate.of(2024,1,12);
       assertTrue(cAP.checkData(info,rBLibro.getString("recensione"), rBLibro.getString("descrizione"),date,infoCosti ));



    }
    //test for controller modif page


    @Test
    void testCheckDataModif() throws SQLException {

        // first try not
        //second try ok
        //id taken from dao

        Libro l=new Libro();
        LocalDate date = LocalDate.of(2024,2,2);


        l.setTitolo(rBLibro.getString("titoloMod"));
		l.setNrPagine(Integer.parseInt(rBLibro.getString("numPagMod")));
		l.setCodIsbn(rBLibro.getString("isbnMod"));
		l.setEditore(rBLibro.getString("editoreMod"));
		l.setAutore(rBLibro.getString("autoreMod"));
		l.setLingua(rBLibro.getString("linguaMod"));
		l.setCategoria(rBLibro.getString("categoriaMod"));
		l.setDataPubb(date);
		l.setRecensione(rBLibro.getString("recensioneMod"));
		l.setDesc(rBLibro.getString("descrizioneMod"));
		l.setDisponibilita(Integer.parseInt(rBLibro.getString("dispMod")));
		l.setPrezzo(Float.parseFloat(rBLibro.getString("prezzoMod")));
		l.setNrCopie(Integer.parseInt(rBLibro.getString("nrCopieMod")));
        l.setId(Integer.parseInt(rBLibro.getString("idMod")));

        lD.aggiornaLibro(l);
        assertNotEquals(0,l.getId());




    }


    @Test
    void testGetLibriById() throws SQLException {

        assertNotNull(cMP.getLibriById(vis.getId()));
    }





}
