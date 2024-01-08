package laptop.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerReportPageTest {
    private final ControllerReportPage cRP=new ControllerReportPage();

    ControllerReportPageTest() throws IOException {
    }

    @Test
    void leggiLibro() throws IOException {
        assertNotNull(cRP.leggiLibro());
    }

    @Test
    void leggiGiornale() throws IOException {
        assertNotNull(cRP.leggiGiornale());
    }

    @Test
    void leggiRivista() throws IOException {
        assertNotNull(cRP.leggiRivista());
    }

    @Test
    void leggiUtenti() throws IOException {
        assertThrows(NullPointerException.class,cRP::leggiUtenti);
    }
}