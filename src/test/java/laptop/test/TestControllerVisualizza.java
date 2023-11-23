package laptop.test;

import laptop.controller.ControllerVisualizza;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestControllerVisualizza {
     private final ControllerVisualizza cV=new ControllerVisualizza();
     private final ResourceBundle RBSETTAOGGETTO=ResourceBundle.getBundle("configurations/settaOggetto");

     @ParameterizedTest
     @ValueSource(strings = {"1","2","3","4","5"})
     void testGetDataL(String strings) throws SQLException {
          cV.setID(strings);
          assertNotNull(cV.getDataL(cV.getID()));
     }
     @ParameterizedTest
     @ValueSource(strings = {"1","2","3","4","5"})
     void testGetDataR(String strings) throws SQLException {
          cV.setID(strings);
          assertNotNull(cV.getDataR(cV.getID()));
     }
     @ParameterizedTest
     @ValueSource(strings = {"1","2","3","4","5"})
     void testGetDataG(String strings) throws SQLException {
          cV.setID(strings);
          assertNotNull(cV.getDataG(cV.getID()));
     }

}
