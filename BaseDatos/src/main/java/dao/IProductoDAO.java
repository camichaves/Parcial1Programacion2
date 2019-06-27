/**
 * 
 */
package dao;

import java.util.List;

import entidades.Producto;

/**
 * @author Camila
 *
 */
public interface IProductoDAO {
	
	public boolean insertar(Producto p);
	public void actualizar (Producto p);
	public void borrar(Producto p);
	public Producto find(Long id);
	public List<Producto>findAll();
	public Long existe(Producto producto);
	public Long nuevoid();

}
