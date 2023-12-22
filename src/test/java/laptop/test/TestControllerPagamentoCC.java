package laptop.test;

import laptop.controller.ControllerPagamentoCC;
import laptop.controller.ControllerSystemState;
import laptop.exception.IdException;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestControllerPagamentoCC {
     private final ResourceBundle RBCARTACREDITO=ResourceBundle.getBundle("configurations/pagamento");
     private final ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
     private final ControllerSystemState vis=ControllerSystemState.getInstance();


     @Test
    void testControllaPag()
     {
         assertTrue(cPCC.controllaPag(RBCARTACREDITO.getString("dataScad"),RBCARTACREDITO.getString("numero"),RBCARTACREDITO.getString("civ")));
     }
     @Test
    void testAggiungiCartaDB() throws SQLException, IdException {
        java.sql.Date data=new Date(20280909);
        vis.setSpesaT(Float.parseFloat(RBCARTACREDITO.getString("prezzoI")));
        cPCC.aggiungiCartaDB(RBCARTACREDITO.getString("nomeI"),RBCARTACREDITO.getString("cognomeI"),RBCARTACREDITO.getString("codiceI"),data,RBCARTACREDITO.getString("civI"),vis.getSpesaT());

     }

}


