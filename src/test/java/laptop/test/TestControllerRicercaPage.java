package laptop.test;

import laptop.controller.ControllerRicercaPage;
import laptop.controller.ControllerRicercaPerTipo;
import laptop.controller.ControllerSystemState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerRicercaPage {

     private final ControllerRicercaPage cRP=new ControllerRicercaPage();
     private final ControllerSystemState vis=ControllerSystemState.getInstance();

     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");

     private final ControllerRicercaPerTipo cRPT=new ControllerRicercaPerTipo();

     @Test
    void testCercaPerLibro() throws SQLException {
         vis.setTypeAsBook();
         assertNotNull(cRP.cercaPerTipo(RBSETTAOGGETTO.getString("nomeS")));
     }
     @Test
    void testCercaPerRivista() throws SQLException {
         vis.setTypeAsMagazine();
         assertNotNull(cRP.cercaPerTipo(RBSETTAOGGETTO.getString("rivistaS")));
     }
     @Test
    void testCercaPerGiornale() throws SQLException {
         vis.setTypeAsDaily();
         assertNotNull(cRP.cercaPerTipo(RBSETTAOGGETTO.getString("giornaleS")));
     }
     @ParameterizedTest
     @ValueSource (strings={"libro","giornale","rivista"})
    void testSetRicerca(String strings)
     {
         assertTrue(cRPT.setRicerca(strings));
     }

}


