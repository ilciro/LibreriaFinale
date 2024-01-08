package laptop.controller;

import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URISyntaxException;


class ControllerDownloadTest {
    private final  ControllerDownload cD=new ControllerDownload();
    private final ControllerSystemState vis=ControllerSystemState.getInstance();

    ControllerDownloadTest() throws IOException {
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    void scaricaL(int ints) throws DocumentException, IOException, URISyntaxException {
        vis.setTypeAsBook();
        vis.setId(ints);
        cD.scarica(vis.getType());
    }
    @Test
    void scaricaG() throws DocumentException, IOException, URISyntaxException {
        vis.setTypeAsDaily();
        vis.setId(1);
        cD.scarica(vis.getType());
    }
    @Test
    void scaricaR() throws DocumentException, IOException, URISyntaxException {
        vis.setTypeAsMagazine();
        vis.setId(1);
        cD.scarica(vis.getType());
    }
}