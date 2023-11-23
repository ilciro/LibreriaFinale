package laptop.test;

import laptop.controller.ControllerAcquista;
import laptop.controller.ControllerSystemState;
import laptop.exception.IdException;
import laptop.utilities.ConnToDb;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class TestControllerAcquista {

    private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
    private static final ControllerSystemState vis=ControllerSystemState.getInstance();

    private final  ControllerAcquista cA=new ControllerAcquista();

    @BeforeAll
    static void init() throws FileNotFoundException {
        ConnToDb.creaPopolaDb();
    }



    @Test
    void testInserisciAmmontareLibro()
    {
        vis.setTypeAsBook();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibro")));
        assertDoesNotThrow(()->cA.inserisciAmmontare(vis.getType(),vis.getId()));
    }
    @Test
    void testInserisciAmmontareRivista()
    {
        vis.setTypeAsMagazine();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivista")));
        assertDoesNotThrow(()->cA.inserisciAmmontare(vis.getType(),vis.getId()));
    }
    @Test
    void testInserisciAmmontareGiornale()
    {
        vis.setTypeAsDaily();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornale")));
        assertDoesNotThrow(()->cA.inserisciAmmontare(vis.getType(),vis.getId()));
    }
    @Test
    void testGetCostoLibro() throws SQLException, IdException {
        vis.setTypeAsBook();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibro")));
        assertNotEquals(0f,cA.getCosto(vis.getType()));
    }
    @Test
    void testGetCostoRivista() throws SQLException, IdException {
        vis.setTypeAsMagazine();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivista")));
        assertNotEquals(0f,cA.getCosto(vis.getType()));
    }
    @Test
    void testGetCostoGiornale() throws SQLException, IdException {
        vis.setTypeAsDaily();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornale")));
        assertNotEquals(0f,cA.getCosto(vis.getType()));
    }
    @Test
    void testGetNomeByIdLibro() throws SQLException {
        vis.setTypeAsBook();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibro")));
        assertNotNull(cA.getNomeById());
    }
    @Test
    void testGetNomeByIdRivista() throws SQLException {
        vis.setTypeAsMagazine();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivista")));
        assertNotNull(cA.getNomeById());
    }
    @Test
    void testGetNomeByIdGiornale() throws SQLException {
        vis.setTypeAsDaily();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornale")));
        assertNotNull(cA.getNomeById());
    }
    @Test
    void testGetDisppLibro() throws SQLException, IdException {
        vis.setTypeAsBook();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibro")));
        assertNotEquals(0,cA.getDisp(vis.getType()));
    }
    @Test
    void testGetDispRivista() throws SQLException, IdException {
        vis.setTypeAsMagazine();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivista")));
        assertNotEquals(0,cA.getDisp(vis.getType()));
    }
    @Test
    void testGetDispGiornale() throws SQLException, IdException {
        vis.setTypeAsDaily();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornale")));
        assertNotEquals(0,cA.getDisp(vis.getType()));
    }

}