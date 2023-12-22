package laptop.test;

import laptop.controller.ControllerAggiungiUtente;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerAggiungiUtente {

    private final ResourceBundle RBSERDAINSERIRE=ResourceBundle.getBundle("configurations/settaOggetto");

    private final ControllerAggiungiUtente cAU=new ControllerAggiungiUtente();

    String data="1990/11/21";

    @Test
    void testInsertUser() throws SQLException, ParseException {
        assertTrue(cAU.checkData(RBSERDAINSERIRE.getString("nomeU"),RBSERDAINSERIRE.getString("cognomeU"),RBSERDAINSERIRE.getString("emailU"), RBSERDAINSERIRE.getString("passU"),data));
    }
}


