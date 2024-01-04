package laptop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerReportPageTest {
    private final ControllerReportPage cRP=new ControllerReportPage();

    ControllerReportPageTest() throws IOException {
    }

    @Test
    void reportTotale() throws IOException {
        assertThrows(IOException.class, cRP::reportTotale);

    }
}