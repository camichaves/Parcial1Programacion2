/**
 * 
 */
package entidades;

/**
 * @author Camila
 *
 */
public class Producto {
	
	protected Long id;
	protected String nombre;
	protected String descripcion;
	protected float precio;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Producto(String nombre, String descripcion, float precio) {
		super();
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	public Producto() {
		// TODO Auto-generated constructor stub
	}
	public Producto(Long id2, String nombre2, String descripcion2, Float precio2) {
		// TODO Auto-generated constructor stub
		
		this.nombre = nombre2;
		this.descripcion = descripcion2;
		this.precio = precio2;
		this.id=id2;
	}
	
	

}
