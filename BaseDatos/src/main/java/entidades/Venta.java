/**
 * 
 */
package entidades;


import java.util.List;

/**
 * @author Camila
 *
 */
public class Venta {
	
	public Venta(long id,String fecha, float valorventa, String descripcion, long idpe) {
		super();
		this.fechaventa = fecha;
		this.valorventa = valorventa;
		this.descripcion = descripcion;
		this.id=id;
		this.setIdpersona(idpe);
		
	}
	
	public Venta(long id,String fecha, float valorventa, String descripcion, long idpe, List<Producto> prod) {
		super();
		this.fechaventa = fecha;
		this.valorventa = valorventa;
		this.descripcion = descripcion;
		this.id=id;
		this.setIdpersona(idpe);
		this.productos=prod;
		
	}
	public Venta() {
		// TODO Auto-generated constructor stub
	}
	public Venta(String fecha, float valorventa, String descripcion, Long idpe, List<Producto> prod) {
		// TODO Auto-generated constructor stub
		
		this.fechaventa = fecha;
		this.valorventa = valorventa;
		this.descripcion = descripcion;
		this.setIdpersona(idpe);
		this.productos=prod;
	}
	private long id;
	private long idpersona;
	private String fechaventa;
	private float valorventa;
	private String descripcion;
	private List<Producto> productos;
	
	/**
	 * @return the fechaventa
	 */
	public String getFechaventa() {
		return fechaventa;
	}
	/**
	 * @param fechaventa the fechaventa to set
	 */
	public void setFechaventa(String fechaventa) {
		this.fechaventa = fechaventa;
	}
	/**
	 * @return the valorventa
	 */
	public float getValorventa() {
		return valorventa;
	}
	/**
	 * @param valorventa the valorventa to set
	 */
	public void setValorventa(float valorventa) {
		this.valorventa = valorventa;
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
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}
	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	/**
	 * @return the persona
	 */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdpersona() {
		return idpersona;
	}
	public void setIdpersona(long idpersona) {
		this.idpersona = idpersona;
	}
	
	
	
	
	

}
