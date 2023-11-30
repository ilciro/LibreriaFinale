/*package laptop.test;

import laptop.controller.ControllerCompravendita;
import laptop.controller.ControllerSystemState;
import laptop.exception.IdException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerCompravendita {
     private final ControllerCompravendita cC=new ControllerCompravendita();
     private final ControllerSystemState vis=ControllerSystemState.getInstance();
     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");

     @ParameterizedTest
     @ValueSource(strings={"libro","giornale","rivista"})
    void testGetLista(String strings) throws SQLException {
         assertNotNull(cC.getLista(strings));
     }
     @Test
    void testDisponibilitaLibro() throws SQLException, IdException {
         vis.setTypeAsBook();
         assertTrue(cC.disponibilita(vis.getType(),RBSETTAOGGETTO.getString("idLibro")));
     }
    @Test
    void testDisponibilitaRivista() throws SQLException, IdException {
        vis.setTypeAsMagazine();
        assertTrue(cC.disponibilita(vis.getType(),RBSETTAOGGETTO.getString("idRivista")));
    }
    @Test
    void testDisponibilitaGiornale() throws SQLException, IdException {
        vis.setTypeAsDaily();
        assertTrue(cC.disponibilita(vis.getType(),RBSETTAOGGETTO.getString("idGiornale")));
    }

}


 */