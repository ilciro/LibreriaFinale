package laptop.test;

import laptop.controller.ControllerPagamentoCash;
import laptop.controller.ControllerSystemState;
import laptop.exception.IdException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestControllerPagamentoCash {
     private final ControllerPagamentoCash cPCash=new ControllerPagamentoCash();
     private final ResourceBundle RBFATTURA=ResourceBundle.getBundle("configurations/fattura");
     private final ControllerSystemState vis=ControllerSystemState.getInstance();

     @Test
    void testControllaFattura1() throws SQLException, IdException {
        vis.setSpesaT(Float.parseFloat(RBFATTURA.getString("ammontare1")));
        cPCash.controlla(RBFATTURA.getString("nome1"),RBFATTURA.getString("cognome1"),RBFATTURA.getString("via1"), RBFATTURA.getString("comunicazioni1"));
        assertNotEquals(0f,vis.getSpesaT());
     }
    @Test
    void testControllaFattura2() throws SQLException, IdException {
        vis.setSpesaT(Float.parseFloat(RBFATTURA.getString("ammontare2")));
        cPCash.controlla(RBFATTURA.getString("nome1"),RBFATTURA.getString("cognome1"),RBFATTURA.getString("via1"), RBFATTURA.getString("comunicazioni2"));
        assertNotEquals(0f,vis.getSpesaT());
    }

}


