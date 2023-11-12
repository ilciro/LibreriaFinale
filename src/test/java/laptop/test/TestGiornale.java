package laptop.test;

import com.itextpdf.text.DocumentException;
import laptop.controller.*;
import laptop.database.GiornaleDao;
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
    private final ResourceBundle rBGiornaleDAInserire=ResourceBundle.getBundle("configurations/giornaleDaInserire");

    private final ResourceBundle rBModificaGiornale=ResourceBundle.getBundle("configurations/giornaleDaModificare");
    private Giornale g=new Giornale();
    private GiornaleDao gD=new GiornaleDao();

    private String[] cambio=new String[4];
    private static ControllerSystemState vis=ControllerSystemState.getInstance();


    private ControllerAggiungiPage cAP=new ControllerAggiungiPage();
    private ControllerModifPage cMP=new ControllerModifPage();
    private ControllerDownload cD=new ControllerDownload();
    private ControllerAcquista cA=new ControllerAcquista();
    private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    private ControllerReportPage cRP =new ControllerReportPage();
    private ControllerVisualizza cV=new ControllerVisualizza();
    private ControllerGestionePage cGP=new ControllerGestionePage();

    @Test
    void testCheckInsertGiornale() throws SQLException {
        g.setTitolo(rBGiornaleDAInserire.getString("titolo"));
        g.setTipologia(rBGiornaleDAInserire.getString("tipologia"));
        g.setLingua(rBGiornaleDAInserire.getString("lingua"));
        g.setEditore(rBGiornaleDAInserire.getString("editore"));
        g.setDataPubb(LocalDate.of(2022, 12,12));
        g.setCopieRimanenti(Integer.parseInt(rBGiornaleDAInserire.getString("copieRimanenti")));
        g.setDisponibilita(Integer.parseInt(rBGiornaleDAInserire.getString("disponibilita")));
        g.setPrezzo(Float.parseFloat(rBGiornaleDAInserire.getString("prezzo")));
        assertTrue(cAP.checkDataG(g));
    }
    @Test
    void testModificaGiornale() throws SQLException {
        vis.setId(1);
        cambio[0]=rBModificaGiornale.getString("cambio[0]");
        cambio[1]=rBModificaGiornale.getString("cambio[1]");
        cambio[2]=rBModificaGiornale.getString("cambio[2]");
        cambio[3]=rBModificaGiornale.getString("cambio[3]");
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




    @ParameterizedTest
    @ValueSource(ints= {1,2,3,4,5,6})
    void testScaricaGiornale(int ints) throws IOException, DocumentException, URISyntaxException {
        vis.setTypeAsDaily();
        vis.setId(ints);
        cD.scarica(vis.getType());
        assertEquals(ints,vis.getId());
    }
}
