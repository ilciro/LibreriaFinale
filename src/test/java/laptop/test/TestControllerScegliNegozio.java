package laptop.test;

import laptop.controller.ControllerScegliNegozio;
import laptop.model.Negozio;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestControllerScegliNegozio {

     private final ControllerScegliNegozio cSN=new ControllerScegliNegozio();
     private final Negozio n=new Negozio();

     @Test
    void testGetNegozi() throws SQLException {
         assertNotNull(cSN.getNegozi());
     }
     @Test
    void tesToString() throws SQLException {
         n.setNome(cSN.getNegozi().get(1).getNome());
         n.setIsOpen(cSN.getNegozi().get(1).getIsOpen());
         n.setIsValid(cSN.getNegozi().get(1).getIsValid());
         n.setVia(cSN.getNegozi().get(1).getVia());
         assertNotNull(n.toString());
     }

}


