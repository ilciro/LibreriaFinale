package laptop.test;

import laptop.controller.ControllerAggiungiUtente;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerAggiungiUtente {

    private final ResourceBundle RBSERDAINSERIRE=ResourceBundle.getBundle("configurations/userDaInserire");

    private final ControllerAggiungiUtente cAU=new ControllerAggiungiUtente();

    String data="1984/05/05";

    @Test
    void testInsertUser() throws SQLException, ParseException {
        assertTrue(cAU.checkData(RBSERDAINSERIRE.getString("nome"),RBSERDAINSERIRE.getString("cognome"),RBSERDAINSERIRE.getString("email"), RBSERDAINSERIRE.getString("pass"),data));
    }
}
