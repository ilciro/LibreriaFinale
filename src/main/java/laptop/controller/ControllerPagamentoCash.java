package laptop.controller;

import java.sql.SQLException;

import laptop.database.ContrassegnoDao;
import laptop.model.Fattura;


public class ControllerPagamentoCash {
	private ContrassegnoDao cD;
	private Fattura f;
	private ControllerSystemState vis= ControllerSystemState.getIstance();
	private ControllerCheckPagamentoData cCPD;
	
	

	public void controlla(String nome, String cognome, String via, String com) throws SQLException {
		
			
			float spesa=vis.getSpesaT();

			//fino a qui va
			
			f.setNome(nome);
			f.setCognome(cognome);
			f.setVia(via);
			f.setCom(com);
			f.setAmmontare(spesa);
			
	 		

					
			
			cD.inserisciFattura(f);
			
			
			cCPD.checkPagamentoData(nome);
			
			
			
			
			
	}

	public ControllerPagamentoCash()  {
		cD = new ContrassegnoDao();
		f = new Fattura();
		cCPD=new ControllerCheckPagamentoData();
		
		
	}

}
