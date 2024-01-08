package laptop.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerUserPageTest {
    private final ControllerUserPage cUP=new ControllerUserPage();

    @Test
    void getUtenti() {
        assertThrows(NullPointerException.class,cUP::getUtenti);
    }
}