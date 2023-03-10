package laptop.model.raccolta;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class Rivista implements Raccolta  {
	
	private String titolo;
	private String tipologia;
	private String autore;
	private String lingua;
	private String editore;
	private String descrizione;
	private LocalDate dataPubb;
	private int disp;
	private float prezzo;
	private int copieRim;
	private int id;
	private String url="C:\\libriScaricati";
	private String [] infoGenerali=new String[5];

	public Rivista(String [] info,String descrizione, LocalDate dataPubb2, int disp, float prezzo, int copieRim,int id) {
		this.setInfoGenerali(info);
		this.descrizione = descrizione;
		this.dataPubb = dataPubb2;
		this.disp = disp;
		this.prezzo = prezzo;
		this.copieRim = copieRim;
		this.id = id;
		this.titolo=info[0];
		this.tipologia=info[1];
		this.editore=info[4];
		this.lingua=info[3];
		this.autore=info[2];
	}
	public Rivista() {
		super();
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	public String getTipologia() {
		return this.tipologia;
	}
	public String getAutore() {
		return this.autore;
	}
	public String getLingua() {
		return this.lingua;
	}
	public String getEditore() {
		return this.editore;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public LocalDate getDataPubb() {
		return this.dataPubb;
	}
	public int getDisp() {
		return this.disp;
	}
	public float getPrezzo() {
		return this.prezzo;
	}
	public int getCopieRim() {
		return this.copieRim;
	}
	public int getId() {
		return this.id;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setTipologia(String tipologia) {
		switch (tipologia){
		
		case "SETTIMANALE": 
			this.tipologia = CategorieRivista.SETTIMANALE.toString();  
			break;

		case "BISETTIMANALE": 
			this.tipologia = CategorieRivista.BISETTIMANALE.toString();  
			break;

		case "MENSILE" : 
			this.tipologia = CategorieRivista.MENSILE.toString();  
			break;

		case "BIMESTRALE" : 
			this.tipologia = CategorieRivista.BIMESTRALE.toString();  
			break;

		case "TRIMESTRALE" : 
			this.tipologia = CategorieRivista.TRIMESTRALE.toString();  
			break;

		case "ANNUALE" : 
			this.tipologia = CategorieRivista.ANNUALE.toString();  
			break;

		case "ESTIVO" : 
			this.tipologia = CategorieRivista.ESTIVO.toString();  
			break;

		case "INVERNALE" : 
			this.tipologia = CategorieRivista.INVERNALE.toString();  
			break;

		case "SPORTIVO" : 
			this.tipologia = CategorieRivista.SPORTIVO.toString();  
			break;

		case "CINEMATOGRAFICA" : 
			this.tipologia = CategorieRivista.CINEMATOGRAFICA.toString();  
			break;

		case "GOSSIP" : 
			this.tipologia = CategorieRivista.GOSSIP.toString();  
			break;

		case "TELEVISIVO" : 
			this.tipologia = CategorieRivista.TELEVISIVO.toString();  
			break;

		case "MILITARE" : 
			this.tipologia = CategorieRivista.MILITARE.toString();  
			break;

		case "INFORMATICA" : 
			this.tipologia = CategorieRivista.INFORMATICA.toString();  
			break;

		default :
			this.tipologia = null;
			break;
		}
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public void setCopieRim(int copieRim) {
		this.copieRim = copieRim;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void scarica() throws IOException {
		File file=null;
		File dirToOpen=null;
		 file = new File(url);
		 Desktop desktop=null;
	      file.mkdir();
	      
	      
		  desktop = Desktop.getDesktop();
	        
	        
	            dirToOpen = new File(url);
	            
					desktop.open(dirToOpen);
				
	        
		
	}
	@Override
	public void leggi(int i) throws FileNotFoundException, DocumentException {
		Document document=null;
		
		
		
		
		 ResourceBundle rBR=ResourceBundle.getBundle("configurations/downloadConfigurationRivista");
		
		    		 
		  			document = new Document();
		     			PdfWriter.getInstance(document, new FileOutputStream(rBR.getString("path")));
		     			document.open();	

		     			document.add(new Paragraph("Rivista/Magazine not avalaible"
		     			+"\n"+"Nam ultricies efficitur magna, sit amet luctus magna luctus volutpat."
		     			+"\n"+" Pellentesque facilisis lacinia mi, nec posuere justo pharetra non."
		     			+"\n"+ " Nulla vel risus sit amet risus aliquam auctor."
		     			+"\n"+ " Nunc viverra felis sit amet nulla faucibus, sed euismod neque lacinia."
		     			+"\n"+ " Integer pharetra sapien sed odio mattis, sed efficitur justo blandit. "
		     			+"\n"+ "Praesent in quam non neque hendrerit pulvinar ut quis tortor. "
		     			+"\n"+ "Maecenas nec convallis nunc. "
		     			+"\n"+ "Donec ultricies malesuada mauris ac accumsan. "
		     			+"\n"+ "Vestibulum auctor est ac laoreet egestas. "
		     			+"\n"+ "Nam malesuada in massa eu venenatis."));
		     			document.close();
		
		
	}
	public String [] getInfoGenerali() {
		return infoGenerali;
	}
	public void setInfoGenerali(String [] infoGenerali) {
		this.infoGenerali = infoGenerali;
	}
 
	
}
