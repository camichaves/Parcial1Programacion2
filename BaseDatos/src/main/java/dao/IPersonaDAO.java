/**
 * 
 */
package dao;

import java.util.List;

import entidades.Persona;

/**
 * @author Camila
 *
 */
public interface IPersonaDAO {
	
	public boolean insertar(Persona p);
	public void actualizar (Persona p);
	public void borrar(Persona p);
	public Persona find(Long id);
	public List<Persona>findAll();
	public Long existe(Persona p);
	public Long nuevoid();
	public List<Persona> findAllActivos();
	public List<Persona> findAllNoActivos();
	public List<Persona> findByNombreOrApellidoLike(String nombre);

}
