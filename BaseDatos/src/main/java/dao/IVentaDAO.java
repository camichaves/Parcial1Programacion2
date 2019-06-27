/**
 * 
 */
package dao;

import java.util.List;

import entidades.Producto;
import entidades.Venta;

/**
 * @author Camila
 *
 */
public interface IVentaDAO {
	
	public void insertar(Venta v);
	public void actualizar (Venta v);
	public void borrar(Venta v);
	public Venta find(Long id);
	public List<Venta>findAll();
	public void relproductos(Producto prod, Venta v);
	public long nuevoid();
	

}
