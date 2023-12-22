package laptop.test;

import laptop.controller.ControllerPassword;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertFalse;

class TestControllerPassword {
     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
     private final ControllerPassword cP=new ControllerPassword();

     @Test
    void testAggiornaPass() throws SQLException {
         assertFalse(cP.aggiornaPass(RBSETTAOGGETTO.getString("emailUp"),RBSETTAOGGETTO.getString("vecchiaP"),RBSETTAOGGETTO.getString("nuovaP")));

     }
}


