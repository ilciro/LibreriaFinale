package laptop.database;

import com.opencsv.CSVWriter;
import laptop.model.raccolta.Libro;
import laptop.utilities.ConnToDb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.*;
import java.util.HashMap;

public class CsvDao implements DaoInterface {
    private static final String CSV_FILE_NAME="localDBFile.csv";

    private final File fd;

    //private HashMap<String, Libro> localCache;


    public CsvDao() throws IOException {
        this.fd = new File(CSV_FILE_NAME);



        //this.localCache = new HashMap<String, Libro>();
    }



    @Override
    public void generaReport() throws IOException {

        try {
            if (!fd.exists()) {
                throw new IOException();
            }
        }catch (IOException e)
        {
            System.out.println("EXCEPTION CsvDao");

            if (!fd.exists()) {
                if (fd.createNewFile())
                {


                    System.out.println("creo file CsvDao");

                    String query=   "SELECT * from USERS";


                    try (Connection conn= ConnToDb.connectionToDB();
                         PreparedStatement prepQ=conn.prepareStatement(query)){

                        ResultSet rs = prepQ.executeQuery(query);
                        CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE_NAME));
                        ResultSetMetaData Mdata = rs.getMetaData();
                        Mdata.getColumnName(1);
                        String[] line1 = {Mdata.getColumnName(1), Mdata.getColumnName(2), Mdata.getColumnName(3), Mdata.getColumnName(4), Mdata.getColumnName(5),Mdata.getColumnName(7),Mdata.getColumnName(8)};
                        writer.writeNext(line1);

                        String[] data =new String[7];
                        while (rs.next())
                        {
                            data[0]= String.valueOf((rs.getInt(1)));
                            data[1]=rs.getString(2);
                            data[2]=rs.getString(3);
                            data[3]=rs.getString(4);
                            data[4]=rs.getString(5);
                            data[5]=rs.getString(7);
                            data[6]= String.valueOf(rs.getDate(8));
                            writer.writeNext(data);
                        }
                        writer.flush();
                        writer.close();


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            }
        }
        /*
        todo
            copiare users dao in csv
         */
        /*
        @Override
	public void saveAlbum(Album instance) throws Exception {
		boolean duplicatedRecordId = false;

		synchronized (this.localCache) {
			duplicatedRecordId = (this.localCache.get(String.valueOf(instance.getAlbumId())) != null);
		}

		if (!duplicatedRecordId) {
			try {
				List<Album> albumList = retreiveById(this.fd, instance.getAlbumId());
				duplicatedRecordId = (albumList.size() != 0);
			} catch (Exception e) {
				duplicatedRecordId = false;
			}
		}

		if (duplicatedRecordId) {
			DuplicatedRecordException e = new DuplicatedRecordException(
					"Duplicated Instance ID. Id " + instance.getAlbumId() + " was already assigned");
			throw e;
		}

		saveAlbum(this.fd, instance);
	}

	private static synchronized void saveAlbum(File fd, Album instance) throws Exception {

		// create csvWriter object passing file reader as a parameter
		CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));

		String[] record = new String[4];

		record[AlbumAttributesOrder.getIndex_AlbumID()] = String.valueOf(instance.getAlbumId());
		record[AlbumAttributesOrder.getIndex_Titolo()] = instance.getTitolo();
		record[AlbumAttributesOrder.getIndex_Artista()] = instance.getArtista();
		record[AlbumAttributesOrder.getIndex_Anno()] = String.valueOf(instance.getAnno());

		csvWriter.writeNext(record);
		csvWriter.flush();
		csvWriter.close();
	}

         */





    }
}
