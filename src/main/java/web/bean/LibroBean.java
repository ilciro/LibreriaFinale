package web.bean;

import java.sql.Date;

import java.time.LocalDate;
import java.util.logging.Level;

import javafx.collections.ObservableList;
import laptop. model.raccolta.CategorieLibro;
import laptop.model.raccolta.Raccolta;



public class LibroBean {
	/**
	 * 
	 */
	private Exception mexB;
	
	private String titoloB;
	private String codIsbnB;
	private String editoreB;
	private String autoreB;
	private String linguaB;
	private LocalDate dataPubbB;
	private String recensioneB;
	private int nrCopieB; // numero copie vendute
	private String descB;
	private int disponibilitaB;
	
	private Date dateB;
	
	private int numeroPagineB;
	private String tipologiaB;

	private int idB;
	private float prezzoB;
	private String categoriaB;
	private ObservableList<Raccolta> listaLibriB;



	public Exception getMexB() {
		return mexB;
	}


	public void setMexB(Exception mexB) {
		this.mexB = mexB;
	}

	public String getTitoloB() {
		return titoloB;
	}


	public void setTitoloB(String titoloB) {
		this.titoloB = titoloB;
	}


	public String getCodIsbnB() {
		return codIsbnB;
	}


	public void setCodIsbnB(String codIsbnB) {
		this.codIsbnB = codIsbnB;
	}


	public String getEditoreB() {
		return editoreB;
	}


	public void setEditoreB(String editoreB) {
		this.editoreB = editoreB;
	}


	public String getAutoreB() {
		return autoreB;
	}


	public void setAutoreB(String autoreB) {
		this.autoreB = autoreB;
	}

	public String getLinguaB() {
		return linguaB;
	}


	public void setLinguaB(String linguaB) {
		this.linguaB = linguaB;
	}

	public LocalDate getDataPubbB() {
		return dataPubbB;
	}

	public void setDataPubbB(LocalDate dataPubbB) {
		this.dataPubbB = dataPubbB;
	}


	public String getRecensioneB() {
		return recensioneB;
	}

	public void setRecensioneB(String recensioneB) {
		this.recensioneB = recensioneB;
	}


	public int getNrCopieB() {
		return nrCopieB;
	}


	public void setNrCopieB(int nrCopieB) {
		this.nrCopieB = nrCopieB;
	}


	public String getDescB() {
		return descB;
	}

	public void setDescB(String descB) {
		this.descB = descB;
	}

	public int getDisponibilitaB() {
		return disponibilitaB;
	}


	public void setDisponibilitaB(int disponibilitaB) {
		this.disponibilitaB = disponibilitaB;
	}

	public Date getDateB() {
		return dateB;
	}


	public void setDateB(Date dateB) {
		this.dateB = dateB;
	}

	public int getNumeroPagineB() {
		return numeroPagineB;
	}


	public void setNumeroPagineB(int numeroPagineB) {
		this.numeroPagineB = numeroPagineB;
	}

	public String getTipologiaB() {
		return tipologiaB;
	}

	public void setTipologiaB(String tipologiaB) {
		this.tipologiaB = tipologiaB;
	}


	public int getIdB() {
		return idB;
	}

	public void setIdB(int idB) {
		this.idB = idB;
	}


	public float getPrezzoB() {
		return prezzoB;
	}


	public void setPrezzoB(float prezzoB) {
		this.prezzoB = prezzoB;
	}

	public String setCategorie()
	{
		
		String s="ADOLESCENTI_RAGAZZI"+"\n";
		s+="ARTE"+"\n";
		s+="CINEMA_FOTOGRAFIA"+"\n";
		s+="BIOGRAFIE"+"\n";
		s+="DIARI_MEMORIE"+"\n";
		s+="CALENDARI_AGENDE"+"\n";
		s+="DIRITTO"+"\n";
		s+="DIZINARI_OPERE"+"\n";
		s+="ECONOMIA"+"\n";
		s+="FAMIGLIA"+"\n";
		s+="SALUTE_BENESSERE"+"\n";
		s+="FANTASCIENZA_FANTASY"+"\n";
		s+="FUMETTI_MANGA"+"\n";
		s+="GIALLI_THRILLER"+"\n";
		s+="COMPUTER_GIOCHI"+"\n";
		s+="HUMOR"+"\n";
		s+="INFORMATICA"+"\n";
		s+="WEB_DIGITAL_MEDIA"+"\n";
		s+="LETTERATURA_NARRATIVA"+"\n";
		s+="LIBRI_BAMBINI"+"\n";
		s+="LIBRI_SCOLASTICI"+"\n";
		s+="LIBRI_UNIVERSITARI"+"\n";
		s+="RICETTARI_GENERALI"+"\n";
		s+="LINGUISTICA_SCRITTURA"+"\n";
		s+="POLITICA"+"\n";
		s+="RELIGIONE"+"\n";
		s+="ROMANZI_ROSA"+"\n";
		s+="SCIENZE"+"\n";
		s+="TECNOLOGIA_MEDICINA"+"\n";
		return s;
		}







	public void setcategoriaB(String categoriaB) {
		switch (categoriaB){
		case "ADOLESCENTI_RAGAZZI": 
			this.setcategoriaB(CategorieLibro.ADOLESCENTI_RAGAZZI.toString());  
			break;
		case "ARTE": 
			this.setcategoriaB(CategorieLibro.ARTE.toString());  
			break;
		case "CINEMA_FOTOGRAFIA" : 
			this.setcategoriaB(CategorieLibro.CINEMA_FOTOGRAFIA.toString());  
			break;
		case "BIOGRAFIE" : 
			this.setcategoriaB(CategorieLibro.BIOGRAFIE.toString());  
			break;
		case "DIARI_MEMORIE" : 
			this.setcategoriaB(CategorieLibro.DIARI_MEMORIE.toString());  
			break;
		case "CALENDARI_AGENDE" : 
			this.setcategoriaB(CategorieLibro.CALENDARI_AGENDE.toString());  
			break;
		case "DIRITTO" : 
			this.setcategoriaB(CategorieLibro.DIRITTO.toString());  
			break;
		case "DIZINARI_OPERE" : 
			this.setcategoriaB(CategorieLibro.DIZINARI_OPERE.toString());  
			break;
		case "ECONOMIA" : 
			this.setcategoriaB(CategorieLibro.ECONOMIA.toString());  
			break;
		case "FAMIGLIA" : 
			this.setcategoriaB(CategorieLibro.FAMIGLIA.toString());  
			break;
		case "SALUTE_BENESSERE" : 
			this.setcategoriaB(CategorieLibro.SALUTE_BENESSERE.toString());  
			break;
		case "FANTASCIENZA_FANTASY" : 
			this.setcategoriaB(CategorieLibro.FANTASCIENZA_FANTASY.toString());  
			break;
		case "FUMETTI_MANGA" : 
			this.setcategoriaB(CategorieLibro.FUMETTI_MANGA.toString());  
			break;
		case "GIALLI_THRILLER" : 
			this.setcategoriaB(CategorieLibro.GIALLI_THRILLER.toString());  
			break;		
		case "COMPUTER_GIOCHI" : 
			this.setcategoriaB(CategorieLibro.COMPUTER_GIOCHI.toString());  
			break;
		case "HUMOR" : 
			this.setcategoriaB(CategorieLibro.HUMOR.toString());  
			break;
		case "INFORMATICA" : 
			this.setcategoriaB(CategorieLibro.INFORMATICA.toString());  
			break;		
		case "WEB_DIGITAL_MEDIA" : 
			this.setcategoriaB(CategorieLibro.WEB_DIGITAL_MEDIA.toString());  
			break;		
		case "LETTERATURA_NARRATIVA" : 
			this.setcategoriaB(CategorieLibro.LETTERATURA_NARRATIVA.toString());  
			break;		
		case "LIBRI_BAMBINI" : 
			this.setcategoriaB(CategorieLibro.LIBRI_BAMBINI.toString());  
			break;		
		case "LIBRI_SCOLASTICI" : 
			this.setcategoriaB(CategorieLibro.LIBRI_SCOLASTICI.toString());  
			break;
		case "LIBRI_UNIVERSITARI" : 
			this.setcategoriaB(CategorieLibro.LIBRI_UNIVERSITARI.toString());  
			break;			
		case "RICETTARI_GENERALI" : 
			this.setcategoriaB(CategorieLibro.RICETTARI_GENERALI.toString());  
			break;	
		case "LINGUISTICA_SCRITTURA" : 
			this.setcategoriaB(CategorieLibro.LINGUISTICA_SCRITTURA.toString());  
			break;
		case "POLITICA" : 
			this.setcategoriaB(CategorieLibro.POLITICA.toString());  
			break;
		case "RELIGIONE" : 
			this.setcategoriaB(CategorieLibro.RELIGIONE.toString());  
			break;
		case "ROMANZI_ROSA" : 
			this.setcategoriaB(CategorieLibro.ROMANZI_ROSA.toString());  
			break;		
		case "SCIENZE" : 
			this.setcategoriaB(CategorieLibro.SCIENZE.toString());  
			break;		
		case "TECNOLOGIA_MEDICINA" : 
			this.setcategoriaB(CategorieLibro.TECNOLOGIA_MEDICINA.toString());  
			break;
		
		default :
			this.setcategoriaB(null);
			break;
		}
		
	}











	public ObservableList<Raccolta> getListaLibriB() {
		return listaLibriB;
	}







	public void setListaLibriB(ObservableList<Raccolta> listaLibriB) {
		this.listaLibriB = listaLibriB;
	}







	public LibroBean()
	{
		java.util.logging.Logger.getLogger("libro bean").log(Level.INFO,"costruttore");

	}


	public String getCategoriaB() {
		return categoriaB;
	}


	

	















}
