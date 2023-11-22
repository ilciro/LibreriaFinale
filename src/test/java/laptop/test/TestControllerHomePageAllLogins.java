package laptop.test;

import laptop.controller.ControllerHomePageAfterLogin;
import laptop.controller.ControllerHomePageAfterLoginSE;

import laptop.exception.LogoutException;
import laptop.model.User;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerHomePageAllLogins {

     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");
     private final User u= User.getInstance();
     @Test
        void testLogoutScrittore()
     {
         u.setNome(RBSETTAOGGETTO.getString("nomeS"));
         assertTrue(ControllerHomePageAfterLogin.logout());

     }
    @Test
    void testLogoutEditore() throws LogoutException {
        u.setNome(RBSETTAOGGETTO.getString("nomeE"));
        assertTrue(ControllerHomePageAfterLoginSE.logout());

    }



}
