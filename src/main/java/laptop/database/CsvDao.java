package laptop.database;

import laptop.model.raccolta.Libro;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class CsvDao implements DaoInterface {
    private static final String CSV_FILE_NAME="localDBFile.csv";

    private File fd;

    private HashMap<String, Libro> localCache;

    public CsvDao() throws IOException {
        this.fd = new File(CSV_FILE_NAME);

        if (!fd.exists()) {
            fd.createNewFile();
        }

        this.localCache = new HashMap<String, Libro>();
    }

    @Override
    public void generaReport() throws IOException {


    }
}
