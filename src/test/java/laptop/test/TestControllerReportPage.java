package laptop.test;

import laptop.controller.ControllerReportPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestControllerReportPage {

     private final ControllerReportPage cRP=new ControllerReportPage();

     @Test
    void testGeneraReportTotale() throws IOException, SQLException {
         cRP.generaReportGiornali();
         cRP.generaReportLibri();
         cRP.generaReportRiviste();
         cRP.getUtenti();
        assertNotNull(cRP.reportRaccolta());
     }
     @Test
    void testReportTotale()
     {
         assertNotNull(cRP.reportTotale());
     }
}
