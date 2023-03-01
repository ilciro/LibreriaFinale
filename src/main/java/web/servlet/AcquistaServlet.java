package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import web.bean.AcquistaBean;
import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AcquistaServlet")
public class AcquistaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static AcquistaBean aB=new AcquistaBean();
	private static LibroDao lD=new LibroDao();
	private static Libro l=new Libro();
	private static LibroBean lB=new LibroBean();
	private static GiornaleBean gB=new GiornaleBean();
	private static RivistaBean rB=new RivistaBean();
	private static Rivista r=new Rivista();
	private static RivistaDao rD=new RivistaDao();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();
	private static String bean1="bean1";

	public AcquistaServlet()
	{
		super();
		if(SystemBean.getIstance().getTypeB().equals("libro"))
		{
			lB.setIdB(SystemBean.getIstance().getIdB());
			l.setId(lB.getIdB());
			aB.setTitoloB(lD.getTitolo(l));
			
		}
		if(SystemBean.getIstance().getTypeB().equals("giornale"))
		{
			gB.setIdB(SystemBean.getIstance().getIdB());
			g.setId(gB.getIdB());
			aB.setTitoloB(gD.getTitolo(g));

		}
		if(SystemBean.getIstance().getTypeB().equals("rivista"))
		{
			rB.setIdB(SystemBean.getIstance().getIdB());
			r.setId(rB.getIdB());
			aB.setTitoloB(rD.getTitolo(r));

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String q=req.getParameter("quantita");
		String calcola=req.getParameter("totaleB");
		String metodo=req.getParameter("metodoP");
		String negozio=req.getParameter("negozioB");
		SystemBean.getIstance().setMetodoPB(metodo);
		String download=req.getParameter("pdfB");
		float costo=(float)0.0;
		String type=SystemBean.getIstance().getTypeB();
		String pagamento=SystemBean.getIstance().getMetodoPB();
		try {
		
			
		
		if(calcola!=null && calcola.equals("calcola"))
		{
			switch(type)
			{
				case"libro":
				{
					
							costo=Integer.parseInt(q)*lD.getCosto(l);							
							aB.setPrezzoB(costo);						
							SystemBean.getIstance().setQuantitaB(Integer.parseInt(q));
							SystemBean.getIstance().setSpesaTB(costo);
							SystemBean.getIstance().setTitoloB(aB.getTitoloB());
							
						
					break;
				}
				case "giornale":
				{
					
						costo=Integer.parseInt(q)*gD.getCosto(g);
						aB.setPrezzoB(costo);
						SystemBean.getIstance().setQuantitaB(Integer.parseInt(q));
						SystemBean.getIstance().setSpesaTB(costo);
						SystemBean.getIstance().setTitoloB(aB.getTitoloB());
					
					break;
				}
				case "rivista":
				{
				
						costo=Integer.parseInt(q)*rD.getCosto(r);
						aB.setPrezzoB(costo);
						SystemBean.getIstance().setQuantitaB(Integer.parseInt(q));
						SystemBean.getIstance().setSpesaTB(costo);
						SystemBean.getIstance().setTitoloB(aB.getTitoloB());
					
					break;
				}
				default:break;
			}
			
				
				req.setAttribute("beanA",aB);
				req.setAttribute(bean1, SystemBean.getIstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
				view.forward(req,resp);
				
				
		}
			
			
		
		if(negozio!=null && negozio.equals("ritiro in negozio"))
		{
			SystemBean.getIstance().setNegozioSelezionatoB(true);
			switch(pagamento)
			{
				case "cash":
				{
					req.setAttribute(bean1, SystemBean.getIstance());

					RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp"); 
					view.forward(req,resp);
					break;
				}
				case "cCredito":
				{
					req.setAttribute(bean1, SystemBean.getIstance());

					RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp"); 
					view.forward(req,resp);
					break;
				}
				default:break;
			}
			
		}
		if(download!=null && download.equals("scarica il pdf"))
		{
			SystemBean.getIstance().setNegozioSelezionatoB(false);
			switch(pagamento)
			{
				case "cash":
				{
					req.setAttribute(bean1, SystemBean.getIstance());

					RequestDispatcher view = getServletContext().getRequestDispatcher("/fattura.jsp"); 
					view.forward(req,resp);
					break;
				}
				case "cCredito":
				{
					req.setAttribute(bean1, SystemBean.getIstance());

					RequestDispatcher view = getServletContext().getRequestDispatcher("/cartaCredito.jsp"); 
					view.forward(req,resp);
					break;
				}
				default:break;
			}
		}
		
		
	} catch (NumberFormatException | SQLException e) {
		aB.setMexB(new IdException("quantita eccede la scorta nel magazzino"));
		req.setAttribute("beanA",aB);
		RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
		view.forward(req,resp);
	}
	}

	
}	
		
	
	
