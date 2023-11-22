package laptop.test;

import laptop.controller.ControllerCancellaUser;
import laptop.controller.ControllerSystemState;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerCancellaUser {
     private final ControllerCancellaUser cCU=new ControllerCancellaUser();
     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
     private final ControllerSystemState vis= ControllerSystemState.getInstance();
     @Test
     void testDeleteUser() throws SQLException {
          vis.setId(Integer.parseInt(RBSETTAOGGETTO.getString("idUser")));
          assertTrue(cCU.cancellaUser());
     }

}
