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
			this.setCategoriaB(CategorieLibro.ADOLESCENTI_RAGAZZI.toString());  
			break;
		case "ARTE": 
			this.setCategoriaB(CategorieLibro.ARTE.toString());  
			break;
		case "CINEMA_FOTOGRAFIA" : 
			this.setCategoriaB(CategorieLibro.CINEMA_FOTOGRAFIA.toString());  
			break;
		case "BIOGRAFIE" : 
			this.setCategoriaB(CategorieLibro.BIOGRAFIE.toString());  
			break;
		case "DIARI_MEMORIE" : 
			this.setCategoriaB(CategorieLibro.DIARI_MEMORIE.toString());  
			break;
		case "CALENDARI_AGENDE" : 
			this.setCategoriaB(CategorieLibro.CALENDARI_AGENDE.toString());  
			break;
		case "DIRITTO" : 
			this.setCategoriaB(CategorieLibro.DIRITTO.toString());  
			break;
		case "DIZINARI_OPERE" : 
			this.setCategoriaB(CategorieLibro.DIZINARI_OPERE.toString());  
			break;
		case "ECONOMIA" : 
			this.setCategoriaB(CategorieLibro.ECONOMIA.toString());  
			break;
		case "FAMIGLIA" : 
			this.setCategoriaB(CategorieLibro.FAMIGLIA.toString());  
			break;
		case "SALUTE_BENESSERE" : 
			this.setCategoriaB(CategorieLibro.SALUTE_BENESSERE.toString());  
			break;
		case "FANTASCIENZA_FANTASY" : 
			this.setCategoriaB(CategorieLibro.FANTASCIENZA_FANTASY.toString());  
			break;
		case "FUMETTI_MANGA" : 
			this.setCategoriaB(CategorieLibro.FUMETTI_MANGA.toString());  
			break;
		case "GIALLI_THRILLER" : 
			this.setCategoriaB(CategorieLibro.GIALLI_THRILLER.toString());  
			break;		
		case "COMPUTER_GIOCHI" : 
			this.setCategoriaB(CategorieLibro.COMPUTER_GIOCHI.toString());  
			break;
		case "HUMOR" : 
			this.setCategoriaB(CategorieLibro.HUMOR.toString());  
			break;
		case "INFORMATICA" : 
			this.setCategoriaB(CategorieLibro.INFORMATICA.toString());  
			break;		
		case "WEB_DIGITAL_MEDIA" : 
			this.setCategoriaB(CategorieLibro.WEB_DIGITAL_MEDIA.toString());  
			break;		
		case "LETTERATURA_NARRATIVA" : 
			this.setCategoriaB(CategorieLibro.LETTERATURA_NARRATIVA.toString());  
			break;		
		case "LIBRI_BAMBINI" : 
			this.setCategoriaB(CategorieLibro.LIBRI_BAMBINI.toString());  
			break;		
		case "LIBRI_SCOLASTICI" : 
			this.setCategoriaB(CategorieLibro.LIBRI_SCOLASTICI.toString());  
			break;
		case "LIBRI_UNIVERSITARI" : 
			this.setCategoriaB(CategorieLibro.LIBRI_UNIVERSITARI.toString());  
			break;			
		case "RICETTARI_GENERALI" : 
			this.setCategoriaB(CategorieLibro.RICETTARI_GENERALI.toString());  
			break;	
		case "LINGUISTICA_SCRITTURA" : 
			this.setCategoriaB(CategorieLibro.LINGUISTICA_SCRITTURA.toString());  
			break;
		case "POLITICA" : 
			this.setCategoriaB(CategorieLibro.POLITICA.toString());  
			break;
		case "RELIGIONE" : 
			this.setCategoriaB(CategorieLibro.RELIGIONE.toString());  
			break;
		case "ROMANZI_ROSA" : 
			this.setCategoriaB(CategorieLibro.ROMANZI_ROSA.toString());  
			break;		
		case "SCIENZE" : 
			this.setCategoriaB(CategorieLibro.SCIENZE.toString());  
			break;		
		case "TECNOLOGIA_MEDICINA" : 
			this.setCategoriaB(CategorieLibro.TECNOLOGIA_MEDICINA.toString());  
			break;
		
		default :
			this.setCategoriaB(null);
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


	public void setCategoriaB(String categoriaB) {
		this.categoriaB = categoriaB;
	}
	
	

	















}
