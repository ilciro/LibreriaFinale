package laptop.model;


import laptop.exception.IdException;
import laptop.exception.SetterException;


public class Pagamento {

	private int id;
	private String metodo;
	private int esito; //0 ok 1 fallito
	private String nomeUtente;
	private float ammontare;
	private String tipo;
	private int idOggetto;
	private static final String ISNUMBER="-?\\d+(\\.\\d+)?";




	public Pagamento(int id, String metodo, int esito, String nomeUtente, float ammontare, String tipo) {
		super();
		this.id = id;
		this.metodo = metodo;
		this.esito = esito;
		this.nomeUtente = nomeUtente;
		this.ammontare = ammontare;
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) throws IdException {

		if (id<0)
		{
			throw new IdException("id incorrect");
		}
		this.id = id;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) throws SetterException {
		if(metodo.equals(ISNUMBER))
		{
			throw new SetterException("method of payment is incorrect");
		}

		this.metodo = metodo;
	}
	public int getEsito() {
		return esito;
	}
	public void setEsito(int esito) throws SetterException {
		if(esito<0 || esito>1)
		{
			throw new SetterException("payment sattus wrong");
		}
		this.esito = esito;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) throws SetterException {
		if (nomeUtente.equals(ISNUMBER))
		{
			throw new SetterException("name user wrong");
		}
		this.nomeUtente = nomeUtente;
	}
	public float getAmmontare() {
		return ammontare;
	}
	public void setAmmontare(float ammontare) throws SetterException {
		if(ammontare<0 || ammontare>3000f)
		{
			throw new SetterException("amount wrong");
		}
		this.ammontare = ammontare;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) throws SetterException {
		if (tipo.equals(ISNUMBER))
		{
			throw new SetterException("wrong type");
		}
		this.tipo = tipo;
	}
	
	
	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", metodo=" + metodo + ", esito=" + esito + ", nomeUtente=" + nomeUtente
				+ ", ammontare=" + ammontare + ", tipo=" + tipo + ", idOggetto="+idOggetto+"]";
	}
	
	public int getIdOggetto() {
		return idOggetto;
	}
	public void setIdOggetto(int idOggetto) {
		this.idOggetto = idOggetto;
	}
	//usato per stampare roba in cronologia ordini
	public Pagamento(int id, String metodo, int esito, String nomeUtente, float ammontare, String tipo,int idOggetto) {
	super();
	this.id = id;
	this.metodo = metodo;
	this.esito = esito;
	this.nomeUtente = nomeUtente;
	this.ammontare = ammontare;
	this.tipo = tipo;
	this.idOggetto=idOggetto;
}
	public Pagamento() {
		
	}

	
	
	
}
