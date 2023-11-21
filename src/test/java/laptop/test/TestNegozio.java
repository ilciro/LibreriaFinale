package laptop.test;

import laptop.controller.ControllerScegliNegozio;
import laptop.model.Negozio;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestNegozio {
    private final ResourceBundle RBNEGOZIO=ResourceBundle.getBundle("configurations/negozio");
    private final Negozio n=new Negozio();
    private final ControllerScegliNegozio cSN=new ControllerScegliNegozio();

    @Test
    void testSetGetNegozio()
    {
        n.setNome(RBNEGOZIO.getString("nome2"));
        n.setVia(RBNEGOZIO.getString("via2"));
        n.setIsOpen(Boolean.parseBoolean(RBNEGOZIO.getString("isOpen2")));
        n.setIsValid(Boolean.parseBoolean(RBNEGOZIO.getString("isValid2")));
        assertNotNull(n.getNome());
    }
    @Test
    void testCheckNegozi() throws SQLException {
        assertNotNull(cSN.getNegozi());
    }
}




