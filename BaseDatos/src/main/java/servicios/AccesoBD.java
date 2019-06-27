/**
 * 
 */
package servicios;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import entidades.Persona;

/**
 * @author Camila
 *
 */
public class AccesoBD {

	private String usuario;
	private String password;
	private static java.sql.Connection con;
	
	
	public AccesoBD(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.con=null;
	}


	public boolean Conectar() {
	
	// Paso 1 - conexión a la base de datos
		
	
		
		// URL de conexión
		
		String url = "jdbc:mysql://localhost:3306/programacion2_2019?user=" + usuario+ "&password=" + password;
		
		// Registro de MySQL en el driver manager
		
		try {Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {e.printStackTrace();}
		
		try {
			// Abrir conexión al motor de BD
			con = DriverManager.getConnection(url);
			if (con != null) {System.out.println("Conectado");}
			
			
			} catch (SQLException e) {System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();}


	return true;
	}
	
	public static java.sql.Connection getConexion(){
		if ( con != null ) {return con; } 
		return null;
	}
	
	
	
	
	public boolean Ejecucion (Long i, String nom, String ape, ArrayList<Persona> almacen) {
		
		
		 // Paso 2 - ejecucion de SQL
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String sqlBuscar = "select id, nombre, apellido from persona;";
		try {
			stmt= con.createStatement();
			rs = stmt.executeQuery(sqlBuscar);
			//Paso 3 - leer datos
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				System.out.println("ID: "+String.valueOf(id)+" - Nombre: "+nombre+" - Apellido: "+apellido);
			Persona p = new Persona(id,nombre,apellido);
			almacen.add(p);
			}
			
			//Paso 4 - Insertar
			
			
			String sqlInsertar="insert into persona values (?,?, ?);";
			try {
				
				PreparedStatement stmt2 = con.prepareStatement(sqlInsertar);
				stmt2.setLong(1,i);
				stmt2.setString(2,nom);
				stmt2.setString(3,ape);
				stmt2.execute();
				Persona p = new Persona(i,nom,ape);
				almacen.add(p);
			}catch (SQLException e) {e.printStackTrace();}
			
		} catch (SQLException e) {e.printStackTrace();}
		return true;
		}
		public boolean CerrarConexion () {
			//Paso 5 - Cerrar conexión
			try {
			con.close();
			} catch (SQLException e) {e.printStackTrace();}
		
		
		return true;
	}


	

}
