package laptop.test;

import laptop.controller.ControllerPagamentoCC;
import laptop.utilities.CreateDefaultDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCartaCredito {
    private final ResourceBundle rBPagamentoInfo=ResourceBundle.getBundle("configurations/pagamentoInfo");
    private final ResourceBundle rBPagamento=ResourceBundle.getBundle("configurations/pagamento");

    private ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    @BeforeAll
    static void creaDB() throws FileNotFoundException, SQLException
    {
        CreateDefaultDB.createDefaultDB();


    }

    @Test
    void testControllaPag()
    {
        assertTrue(cPCC.controllaPag("2028-08-11",rBPagamentoInfo.getString("codice"),rBPagamentoInfo.getString("civ")));
    }
    @Test
    void testRitornaElencoCC()
    {
        assertNotNull(cPCC.ritornaElencoCC(rBPagamento.getString("nomeUser")));
    }
    @Test
    void testRitornaCarta()
    {
        assertNotNull(cPCC.tornaDalDb(rBPagamento.getString("numero")));
    }
}
