package laptop.test;

import laptop.controller.ControllerVisualizzaOrdine;
import laptop.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestControllerVisualizzaOrdine {

     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
     private final ControllerVisualizzaOrdine cVO=new ControllerVisualizzaOrdine();

     @Test
    void testGetDati() throws SQLException {
         User.getInstance().setEmail(RBSETTAOGGETTO.getString("emailPag"));
         assertNotNull(cVO.getDati());
     }
}
