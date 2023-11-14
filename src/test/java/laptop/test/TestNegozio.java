package laptop.test;

import laptop.controller.ControllerScegliNegozio;
import laptop.model.Negozio;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestNegozio {
    private final ResourceBundle rBNegozio=ResourceBundle.getBundle("configurations/negozio");
    private Negozio n=new Negozio();
    private ControllerScegliNegozio cSN=new ControllerScegliNegozio();

    @Test
    void testSetGetNegozio()
    {
        n.setNome(rBNegozio.getString("nome2"));
        n.setVia(rBNegozio.getString("via2"));
        n.setIsOpen(Boolean.parseBoolean(rBNegozio.getString("isOpen2")));
        n.setIsValid(Boolean.parseBoolean(rBNegozio.getString("isValid2")));
        assertNotNull(n.getNome());
    }
    @Test
    void testCheckNegozi() throws SQLException {
        assertNotNull(cSN.getNegozi());
    }
}


