package laptop.controller;

import java.sql.SQLException;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.database.RivistaDao;
import laptop.model.Pagamento;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;

public class ControllerCheckPagamentoData {
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private Libro l;
	private Giornale g;
	private Rivista r;
	
	private LibroDao  lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private PagamentoDao pagD;
	public void checkPagamentoData(String nome) throws SQLException
	{
		String tipo=vis.getType();
		
		Pagamento p;
		
		p=new Pagamento(0,"", 0, "utGene",0, null);
			
		//inserire qui
		p.setMetodo("cash");
		p.setNomeUtente(nome);
		
		p.setId(vis.getId());
		
		
		
		pagD.inserisciPagamento(p);
		if(tipo.equals("libro"))
		{
			//prenod spesa da vis
			l.setId(vis.getId());
			p.setAmmontare(vis.getSpesaT());
			p.setId(l.getId());
			p.setTipo(lD.retTip(l));
		}
		if(tipo.equals("giornale"))
		{
			//prenod spesa da vis
			g.setId(vis.getId());
			p.setAmmontare(vis.getSpesaT());
			p.setId(g.getId());
			p.setTipo(gD.retTip(g));
			
		}
		if(tipo.equals("rivista"))
		{
			//prenod spesa da vis
			r.setId(vis.getId());
			p.setAmmontare(vis.getSpesaT());
			p.setId(r.getId());
			p.setTipo(rD.retTip(r));
			
		}
		
		
	}
	public ControllerCheckPagamentoData()
	{
		l=new Libro();
		g=new Giornale();
		r=new Rivista();
		lD=new LibroDao();
		rD=new RivistaDao();
		gD=new GiornaleDao();
		pagD=new PagamentoDao();
		
	}

}
