package laptop.test;

import laptop.controller.ControllerModificaUtente;
import laptop.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerModificaUtente {
     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
     private final ControllerModificaUtente cMU=new ControllerModificaUtente();
     private final User u= User.getInstance();

     @Test
    void testAggiornaTotale() throws SQLException {
         u.setEmail(RBSETTAOGGETTO.getString("emailUpdate"));
         assertTrue(cMU.aggiornaTot(RBSETTAOGGETTO.getString("nameUpdate"),RBSETTAOGGETTO.getString("nameUpdate"),RBSETTAOGGETTO.getString("newEmail"),RBSETTAOGGETTO.getString("newPwd"),RBSETTAOGGETTO.getString("nameUpdate"), LocalDate.of(1963,4,1),"U"));
     }
}


