package laptop.test;

import com.itextpdf.text.DocumentException;
import laptop.controller.*;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Rivista;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
class TestRivista {
    private  final ResourceBundle RBBOOKCATEGORIES = ResourceBundle.getBundle("configurations/magazineCategories");
 //   private final ResourceBundle rBModificaRivista=ResourceBundle.getBundle("configurations/rivistaDaModificare");
    private final ResourceBundle RBRIVISTADAINSERIRE=ResourceBundle.getBundle("configurations/rivistaDaInserire");
    private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
    private final ResourceBundle REBARTADICREDITO=ResourceBundle.getBundle("configurations/cartaCredito");
    private  final ResourceBundle RBFATTURA=ResourceBundle.getBundle("configurations/fattura");



    private static final ControllerSystemState vis= ControllerSystemState.getInstance();
    private final String[] info=new String[7];

    private final ControllerAggiungiPage cAP=new ControllerAggiungiPage();
    private final ControllerAcquista cA=new ControllerAcquista();
    private final ControllerDownload cD=new ControllerDownload();
    private final ControllerModifPage cMP=new ControllerModifPage();
    private final ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    private final ControllerCompravendita cC=new ControllerCompravendita();
    private final ControllerPagamentoCash cPCash=new ControllerPagamentoCash();



    private final Rivista r=new Rivista();

    private final RivistaDao rD=new RivistaDao();

    @Test
    void testCategorieR()
    {


        Enumeration<String> keys = RBBOOKCATEGORIES.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement().replace("=","");
            r.setTipologia(key);
        }
        assertNotNull(r.getTipologia());
    }
    @Test
    void testCheckInsertRivista() throws SQLException {
        info[0]=RBRIVISTADAINSERIRE.getString("info[0]");
        info[1]=RBRIVISTADAINSERIRE.getString("info[1]");
        info[2]=RBRIVISTADAINSERIRE.getString("info[2]");
        info[3]=RBRIVISTADAINSERIRE.getString("info[3]");
        info[4]=RBRIVISTADAINSERIRE.getString("info[4]");
        r.setDescrizione(RBRIVISTADAINSERIRE.getString("descrizione"));
        r.setDataPubb(LocalDate.of(2022, 11,12));
        r.setDisp(Integer.parseInt(RBRIVISTADAINSERIRE.getString("disponibilita")));
        r.setPrezzo(Float.parseFloat(RBRIVISTADAINSERIRE.getString("prezzo")));
        r.setCopieRim(Integer.parseInt(RBRIVISTADAINSERIRE.getString("copieRimanenti")));
        assertTrue(cAP.checkDataR(info, r.getDataPubb(), r.getDisp(),r.getPrezzo() , r.getCopieRim(), r.getDescrizione()));

    }
    @Test
    void testTotaleR() throws SQLException, IdException {
        r.setTitolo(RBSETTAOGGETTO.getString("titoloR"));
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idR")));
        r.setCopieRim(Integer.parseInt(RBSETTAOGGETTO.getString("copieRimanenti")));
        vis.setQuantita(Integer.parseInt(RBSETTAOGGETTO.getString("quantitaR")));
        vis.setTypeAsMagazine();
        r.setDisp(Integer.parseInt(RBSETTAOGGETTO.getString("disponibilita")));
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
        Date data= Date.valueOf("2028-02-22");
        cPCC.aggiungiCartaDB(REBARTADICREDITO.getString("nome3"),REBARTADICREDITO.getString("cognome3"),REBARTADICREDITO.getString("codice3"), data, REBARTADICREDITO.getString("civ3"), Float.parseFloat(REBARTADICREDITO.getString("prezzo3")));
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
    @Test
    void testGetLista() throws SQLException {
        vis.setTypeAsMagazine();
        assertNotNull(cC.getLista(vis.getType()));
    }
    @Test
    void testDisponibilita() throws SQLException, IdException {
        vis.setTypeAsMagazine();
        assertTrue(cC.disponibilita(vis.getType(),"1"));
    }

    @ParameterizedTest
    @ValueSource(ints= {1,2,3,4,5,6})
    void testScaricaRivista(int ints) throws DocumentException, IOException, URISyntaxException {
        vis.setTypeAsMagazine();
        vis.setId(ints);
        cD.scarica(vis.getType());
        assertEquals(ints,vis.getId());
    }
    @Test
    void testControlla() {
        assertDoesNotThrow(()->cPCash.controlla(RBFATTURA.getString("nome1"), RBFATTURA.getString("cognome1"),RBFATTURA.getString("via"),RBFATTURA.getString("comunicazioni1")));

    }


}

