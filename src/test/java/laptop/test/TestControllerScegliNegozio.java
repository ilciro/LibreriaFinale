package laptop.test;

import laptop.controller.ControllerScegliNegozio;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestControllerScegliNegozio {

     private final ControllerScegliNegozio cSN=new ControllerScegliNegozio();

     @Test
    void testGetNegozi() throws SQLException {
         assertNotNull(cSN.getNegozi());
     }
}
