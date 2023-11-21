package laptop.test;

import com.itextpdf.text.DocumentException;
import laptop.controller.*;
import laptop.database.GiornaleDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Giornale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class TestGiornale {
    private final ResourceBundle RBGIORNALEDAINSERIRE=ResourceBundle.getBundle("configurations/giornaleDaInserire");

    private final ResourceBundle RBMODIFICAGIORNALE=ResourceBundle.getBundle("configurations/giornaleDaModificare");
    private  final ResourceBundle RBFATTURA=ResourceBundle.getBundle("configurations/fattura");

    private final Giornale g=new Giornale();
    private final GiornaleDao gD=new GiornaleDao();

    private final String[] cambio=new String[4];
    private static final ControllerSystemState vis=ControllerSystemState.getInstance();


    private final ControllerAggiungiPage cAP=new ControllerAggiungiPage();
    private final ControllerModifPage cMP=new ControllerModifPage();
    private final ControllerDownload cD=new ControllerDownload();
    private final ControllerAcquista cA=new ControllerAcquista();
    private final ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    private final ControllerReportPage cRP =new ControllerReportPage();
    private final ControllerVisualizza cV=new ControllerVisualizza();
    private final ControllerGestionePage cGP=new ControllerGestionePage();
    private final ControllerCompravendita cC=new ControllerCompravendita();
    private final ControllerPagamentoCash cPCash=new ControllerPagamentoCash();




    @Test
    void testCheckInsertGiornale() throws SQLException {
        g.setTitolo(RBGIORNALEDAINSERIRE.getString("titolo"));
        g.setTipologia(RBGIORNALEDAINSERIRE.getString("tipologia"));
        g.setLingua(RBGIORNALEDAINSERIRE.getString("lingua"));
        g.setEditore(RBGIORNALEDAINSERIRE.getString("editore"));
        g.setDataPubb(LocalDate.of(2022, 12,12));
        g.setCopieRimanenti(Integer.parseInt(RBGIORNALEDAINSERIRE.getString("copieRimanenti")));
        g.setDisponibilita(Integer.parseInt(RBGIORNALEDAINSERIRE.getString("disponibilita")));
        g.setPrezzo(Float.parseFloat(RBGIORNALEDAINSERIRE.getString("prezzo")));
        assertTrue(cAP.checkDataG(g));
    }
    @Test
    void testModificaGiornale() throws SQLException {
        vis.setId(1);
        cambio[0]=RBMODIFICAGIORNALE.getString("cambio[0]");
        cambio[1]=RBMODIFICAGIORNALE.getString("cambio[1]");
        cambio[2]=RBMODIFICAGIORNALE.getString("cambio[2]");
        cambio[3]=RBMODIFICAGIORNALE.getString("cambio[3]");
        cMP.checkDataG(cambio,LocalDate.of(2022, 11,20), 0, (float)1.25, 10);

    }
    @Test
    void testRetId() throws SQLException {
        Giornale g=new Giornale();
        g.setTitolo("La Republica1");
        int id=gD.retId(g);
        String titolo=gD.getTitolo(g);
        String nome=gD.getNome(g);
        int disp=gD.getDisp(g);
        assertNotEquals(0,id);
    }
    @Test
    void testGetLista() throws SQLException {
        vis.setTypeAsDaily();
        assertNotNull(cC.getLista(vis.getType()));
    }
    @Test
    void testDisponibilita() throws SQLException, IdException {
        vis.setTypeAsDaily();
        assertTrue(cC.disponibilita(vis.getType(),"3"));
    }
    @Test
    void testControlla() {

        assertDoesNotThrow(()->cPCash.controlla(RBFATTURA.getString("nome1"), RBFATTURA.getString("cognome1"),RBFATTURA.getString("via"),RBFATTURA.getString("comunicazioni1")));

    }





    @ParameterizedTest
    @ValueSource(ints= {1,2,3,4,5,6})
    void testScaricaGiornale(int ints) throws IOException, DocumentException, URISyntaxException {
        vis.setTypeAsDaily();
        vis.setId(ints);
        cD.scarica(vis.getType());
        assertEquals(ints,vis.getId());
    }
}




