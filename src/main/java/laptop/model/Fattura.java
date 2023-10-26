package laptop.model;


import laptop.exception.SetterException;

public class Fattura {

	private String nome;
	private String cognome;
	private String via;
	private String com;
	private String numero;
	private float ammontare;
	private static final String ISNUMBER= "-?\\d+(\\.\\d+)?";
	
	public Fattura() {
		super();
 
	}
	
	public Fattura(String nome, String cognome, String via, String com, String numero, float ammontare) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.via = via;
		this.com = com;
		this.numero = numero;
		this.ammontare = ammontare;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws SetterException {
		if (nome.equals(ISNUMBER)) {
				throw new SetterException("name incorrect");
			}


		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) throws SetterException {
		if(cognome.equals(ISNUMBER))
		{

				throw new SetterException("surname incorrect");

		}
		this.cognome = cognome;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) throws SetterException {
		if(via.equals(ISNUMBER))
		{

				throw new SetterException("via incorrect");

		}
		this.via = via;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) throws SetterException {
		if(com.equals(ISNUMBER))
		{

				throw new SetterException("comunications incorrect");

		}
		this.com = com;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) throws SetterException {
		if(numero.equals(ISNUMBER))
		{
				throw new SetterException("number incorrect");

		}
		this.numero = numero;
	}
	public float getAmmontare() {
		return ammontare;
	}
	public void setAmmontare(float ammontare) throws SetterException {
		if((ammontare<=0f) || ammontare>3000f)
		{

				throw new SetterException("price  incorrect");

		}
		this.ammontare = ammontare;
	}

	
	
	@Override
	public String toString() {
		return "Fattura [nome=" + nome + ", cognome=" + cognome + ", via=" + via + ", com=" + com + ", numero=" + numero
				+ ", ammontare=" + ammontare + "]";
	}

	

	
	
}
