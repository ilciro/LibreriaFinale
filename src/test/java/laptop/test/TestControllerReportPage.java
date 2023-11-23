package laptop.test;

import laptop.controller.ControllerReportPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestControllerReportPage {

     private final ControllerReportPage cRP=new ControllerReportPage();

     @Test
    void testReportTotale() throws IOException, SQLException {
         cRP.generaReportGiornali();
         cRP.generaReportLibri();
         cRP.generaReportRiviste();
        assertNotNull(cRP.reportRaccolta());
     }
}
