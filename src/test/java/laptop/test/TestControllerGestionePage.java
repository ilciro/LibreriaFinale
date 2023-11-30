/*package laptop.test;

import laptop.controller.ControllerGestionePage;
import laptop.controller.ControllerSystemState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestControllerGestionePage {
     private final ControllerGestionePage cGP=new ControllerGestionePage();
     private final ResourceBundle RBSETTAOGGETTO= ResourceBundle.getBundle("configurations/settaOggetto");
     private final ControllerSystemState vis=ControllerSystemState.getInstance();

    @Test
    void testCancellaLibro()
    {
        vis.setTypeAsBook();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idLibroC")));
        assertDoesNotThrow(()->cGP.cancella(vis.getId()));
    }
    @Test
    void testCancellaRivista()
    {
        vis.setTypeAsMagazine();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idRivistaC")));
        assertDoesNotThrow(()->cGP.cancella(vis.getId()));
    }
    @Test
    void testCancellaGiornale()
    {
        vis.setTypeAsBook();
        vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idGiornaleC")));
        assertDoesNotThrow(()->cGP.cancella(vis.getId()));
    }
    @ParameterizedTest
    @ValueSource(strings={"libro","giornale","rivista"})
    void testGetLista(String strings) throws SQLException {
        assertNotNull(cGP.getLista(strings));
    }
    @ParameterizedTest
    @ValueSource(strings={"libro","giornale","rivista"})
    void testGetMessaggio(String strings) throws SQLException {
        assertNotNull(cGP.settaHeader(strings));
    }

}
*/
