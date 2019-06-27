package entidades;



public class Persona {
	
	public static java.sql.Connection conexion;
	
	protected Long id;
	protected String nombre;
	protected String apellido;
	protected int documento;
	protected int activo;
	
	public Persona(String nombre2, String apellido2, int doc) {
		// TODO Auto-generated constructor stub
		
		
		
		nombre=nombre2;
		apellido=apellido2;
		documento=doc;
		activo=-1;
	}
	
	
	
	
	
public Persona() {
		// TODO Auto-generated constructor stub
	}

public Persona(Long id2, String nombre2, String apellido2, int doc) {
	// TODO Auto-generated constructor stub
	nombre=nombre2;
	apellido=apellido2;
	id=id2;
	documento=doc;
	activo=-1;
}







public Persona(Long id2, String nombre2, String apellido2, int doc,int ac) {
	// TODO Auto-generated constructor stub
	
	nombre=nombre2;
	apellido=apellido2;
	id=id2;
	activo=ac;
	
}

public Persona(Long id2, String nombre2, String apellido2, int doc,boolean ac) {
	// TODO Auto-generated constructor stub
	
	nombre=nombre2;
	apellido=apellido2;
	id=id2;
	if(ac) { activo=1;}else {activo=0;}
	
}

public Persona(String string, String string2, int i, boolean act) {
	// TODO Auto-generated constructor stub
	
	nombre=string;
	apellido=string2;
	documento=i;
	if(act) { activo=1;}else {activo=0;}
}





public Persona(Long id2, String nombre2, String apellido2) {
	// TODO Auto-generated constructor stub
}





public int getActivo() {
	
	return activo;
}


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
 * @return the apellido
 */
public String getApellido() {
	return apellido;
}
/**
 * @param apellido the apellido to set
 */



public void setApellido(String apellido) {
	this.apellido = apellido;
}


public void activar() {
	
	this.activo=1;
}

public void desactivar() {
	
	this.activo=0;
}

public String mostrardocumento() {
	
	String doc= String.valueOf(documento);
	String doc2="";
	int sum=0;
	for(int i=doc.length()-1; i>-1;i--) {
		
		doc2=doc.charAt(i)+doc2;
		sum++;
		if(sum ==3 && i!=0) {
			doc2="."+doc2;
			sum=0;
		}

		
	}
	return doc2;
}





public int getDocumento() {
	// TODO Auto-generated method stub
	return documento;
}


}


