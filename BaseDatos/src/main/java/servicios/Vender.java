/**
 * 
 */
package servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IPersonaDAO;
import dao.IProductoDAO;
import dao.IVentaDAO;
import entidades.Persona;
import entidades.Producto;
import entidades.Venta;
import excepciones.VentaAPersonaDesactivadaException;

/**
 * @author Camila
 *
 */
public class Vender {
	
	public static boolean newVenta (Persona p, String des, List<Producto> prod) {
		
		// ver si la persona esta en la base de datos, sino agregarla
		// poner la fecha
		//pedir agregar una descripcion
		//
		
		IPersonaDAO pDAO =  new PersonaDAOImplem();
		IVentaDAO vDAO = new VentaDAOImplem();
		IProductoDAO prDAO = new ProductoDAOImplem();
		
		long idpers=pDAO.existe(p);
		if(idpers==-1) {
			
			//darle id a persona
			p.setId(pDAO.nuevoid());
			pDAO.insertar(p);}else {
				
				p.setId(idpers);
			}
		
		try {
		verificar(p);
		}catch (VentaAPersonaDesactivadaException e) {
			System.out.println(e.getMessage());
			return false;
		}
		float total=0;
		
		Date hoy = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//foreach producto in prod: prDAO.existe(pr) y total+precio prod
		
		int i;
		
		Venta v = new Venta(formatter.format(hoy) , total, des,p.getId(),prod);
		v.setId(vDAO.nuevoid());
		
		for( i =0;i<prod.size();i++) {
			
			if(prDAO.existe(prod.get(i))==-1){
				
				prod.get(i).setId(pDAO.nuevoid());
				prDAO.insertar(prod.get(i));
				}else {
					prod.get(i).setId(prDAO.existe(prod.get(i)));
				}
			total=total+prod.get(i).getPrecio();
			vDAO.relproductos(prod.get(i),v);
		}
		
		v.setValorventa(total);
		
		
		
		vDAO.insertar(v);
		
		return true;
		
		
	}

	private static void verificar(Persona p) throws VentaAPersonaDesactivadaException {
		// TODO Auto-generated method stub
		 IPersonaDAO pDAO =  new PersonaDAOImplem();
		int band=0;
		 List<Persona> activos =pDAO.findAllNoActivos();
		 
		 for(Persona activo : activos){
				if(activo.getId()==p.getId()) {
					band=1; break;}
			}
		 
		 
		if(band==1) {
			throw new VentaAPersonaDesactivadaException("La persona esta desactivada");
		}
		
	}

}
