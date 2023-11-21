package laptop.test;

import laptop.controller.ControllerPagamentoCC;
import laptop.utilities.ConnToDb;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCartaCredito {
    private final ResourceBundle RBPAGAMENTOINFO=ResourceBundle.getBundle("configurations/pagamentoInfo");
    private final ResourceBundle RBPAGAMENTO=ResourceBundle.getBundle("configurations/pagamento");

    private final ControllerPagamentoCC cPCC=new ControllerPagamentoCC();
    @BeforeAll
    static void creaDB() throws FileNotFoundException
    {
        ConnToDb.creaPopolaDb();

    }

    @Test
    void testControllaPag()
    {
        assertTrue(cPCC.controllaPag("2028-08-11",RBPAGAMENTOINFO.getString("codice"),RBPAGAMENTOINFO.getString("civ")));
    }

    @Test
    void testRitornaElencoCC()
    {
        assertNotNull(cPCC.ritornaElencoCC(RBPAGAMENTO.getString("nomeUser")));
    }
    @Test
    void testRitornaCarta()
    {
        assertNotNull(cPCC.tornaDalDb(RBPAGAMENTO.getString("numero")));
    }



 
}
