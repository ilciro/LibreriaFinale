package laptop.test;

import laptop.controller.ControllerLogin;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerLogin {

     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
     private final ControllerLogin cL=new ControllerLogin();


    @Test
    void testLogin() throws SQLException {
         assertTrue(cL.controlla(RBSETTAOGGETTO.getString("emailLogin"),RBSETTAOGGETTO.getString("pwdLogin")));
    }

    @Test
     void testGetRuoloTempUser() throws SQLException {
         assertEquals("W",cL.getRuoloTempUSer(RBSETTAOGGETTO.getString("emailLogin")));
    }

}


