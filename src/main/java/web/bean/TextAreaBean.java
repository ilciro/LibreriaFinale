package web.bean;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import laptop.model.TempUser;
import laptop.utilities.ConnToDb;

public class TextAreaBean {
	private String scriviB;

	public String getScriviB() {
		return scriviB;
	}

	public void setScriviB(String scriviB) {
		this.scriviB = scriviB;
	}
	

}
