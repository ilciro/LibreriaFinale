package laptop.test;

import com.itextpdf.text.DocumentException;
import laptop.controller.*;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Rivista;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
class TestRivista {
    private  final ResourceBundle rBBookCategories = ResourceBundle.getBundle("configurations/magazineCategories");
    private final ResourceBundle rBModificaRivista=ResourceBundle.getBundle("configurations/rivistaDaModificare");
    private final ResourceBundle rBRivistaDAInserire=ResourceBundle.getBundle("configurations/rivistaDaInserire");
    private final ResourceBundle rBSettaOggetto=ResourceBundle.getBundle("configurations/settaOggetto");
    private final ResourceBundle rBCartaCredito=ResourceBundle.getBundle("configurations/cartaCredito");



    private static ControllerSystemState vis= ControllerSystemState.getInstance();
    private String[] info=new String[7];

    private ControllerAggiungiPage cAP=new ControllerAggiungiPage();
    private ControllerAcquista cA=new ControllerAcquista();
    private ControllerDownload cD=new ControllerDownload();
    private ControllerModifPage cMP=new ControllerModifPage();
    private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();



    private Rivista r=new Rivista();

    private RivistaDao rD=new RivistaDao();

    @Test
    void testCategorieR()
    {


        Enumeration<String> keys = rBBookCategories.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            key.replace("=","");
            r.setTipologia(key);
        }
        assertNotNull(r.getTipologia());
    }
    @Test
    void testCheckInsertRivista() throws SQLException {
        info[0]=rBRivistaDAInserire.getString("info[0]");
        info[1]=rBRivistaDAInserire.getString("info[1]");
        info[2]=rBRivistaDAInserire.getString("info[2]");
        info[3]=rBRivistaDAInserire.getString("info[3]");
        info[4]=rBRivistaDAInserire.getString("info[4]");
        r.setDescrizione(rBRivistaDAInserire.getString("descrizione"));
        r.setDataPubb(LocalDate.of(2022, 11,12));
        r.setDisp(Integer.parseInt(rBRivistaDAInserire.getString("disponibilita")));
        r.setPrezzo(Float.parseFloat(rBRivistaDAInserire.getString("prezzo")));
        r.setCopieRim(Integer.parseInt(rBRivistaDAInserire.getString("copieRimanenti")));
        assertTrue(cAP.checkDataR(info, r.getDataPubb(), r.getDisp(),r.getPrezzo() , r.getCopieRim(), r.getDescrizione()));

    }
    @Test
    void testTotaleR() throws SQLException, IdException {
        r.setTitolo(rBSettaOggetto.getString("titoloR"));
        vis.setId(Integer.parseInt(rBSettaOggetto.getString("idR")));
        r.setCopieRim(Integer.parseInt(rBSettaOggetto.getString("copieRimanenti")));
        vis.setQuantita(Integer.parseInt(rBSettaOggetto.getString("quantitaR")));
        vis.setTypeAsMagazine();
        r.setDisp(Integer.parseInt(rBSettaOggetto.getString("disponibilita")));
        assertNotEquals(0,cA.totale1(vis.getType(),r.getTitolo(),r.getDisp(),vis.getQuantita()));
    }
    @Test
    void testGetRivistaById() throws SQLException {
        assertNotNull(cMP.getRivistaById(5));
    }
    @Test
    void testAggiungiCartaDBR() throws SQLException, IdException {
        vis.setSpesaT((float)14.9);
        vis.setTypeAsMagazine();
        java.sql.Date data= Date.valueOf("2028-02-22");
        cPCC.aggiungiCartaDB(rBCartaCredito.getString("nome3"),rBCartaCredito.getString("cognome3"),rBCartaCredito.getString("codice3"), data, rBCartaCredito.getString("civ3"), Float.parseFloat(rBCartaCredito.getString("prezzo3")));
        assertNotNull(data);
    }
    @Test
    void testSetDescrizione() throws SQLException {
        Rivista r=new Rivista();
        r.setTitolo("Focus");
        rD.getDesc(r);
        int id=rD.retId(r);
        r.setId(id);
        String nome=rD.getNome(r);
        int disp=rD.getDisp(r);
        String titolo=rD.getTitolo(r);
        assertNotEquals(0,r.getId());
    }

    @ParameterizedTest
    @ValueSource(ints= {1,2,3,4,5,6})
    void testScaricaRivista(int ints) throws DocumentException, IOException, URISyntaxException {
        vis.setTypeAsMagazine();
        vis.setId(ints);
        cD.scarica(vis.getType());
        assertEquals(ints,vis.getId());
    }

}
