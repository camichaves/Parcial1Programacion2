/**
 * 
 */
package servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IProductoDAO;
import entidades.Producto;

/**
 * @author Camila
 *
 */
public class ProductoDAOImplem implements IProductoDAO {

private java.sql.Connection conexion;
	
	public ProductoDAOImplem () {
		this.conexion = AccesoBD.getConexion();
		// TODO Auto-generated constructor stub
	}
	
	public boolean insertar(Producto p) {
		// TODO Auto-generated method stub
		
		if(this.existe(p)!=-1) {
			p.setId(this.existe(p));
			this.actualizar(p); return true;}
		
		
		java.sql.PreparedStatement stmt=null;
		String sqlInsertar="insert into producto values (?,?,?,?);";
		try {
			
			stmt = this.conexion.prepareStatement(sqlInsertar);
			long id=this.nuevoid();
			stmt.setLong(1,id);
			stmt.setString(2,p.getNombre());
			stmt.setString(3,p.getDescripcion());
			stmt.setFloat(4,p.getPrecio());
			stmt.execute();
			p.setId(id);
			
		}catch (SQLException e) {e.printStackTrace();}
		
		return true;
	}

	public void actualizar(Producto p) {
		// TODO Auto-generated method stub
		String sqlActualizar = "UPDATE producto set nombre=?, descripcion=?, precio=?  WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		
		try {
			stmt=conexion.prepareStatement(sqlActualizar);
			stmt.setString(1,p.getNombre());
			stmt.setString(2,p.getDescripcion());
			stmt.setFloat(3,p.getPrecio());
			stmt.setLong(4, p.getId());
			stmt.execute();
			
		}catch(SQLException e) { e.printStackTrace();}
		
		
		
	}

	public void borrar(Producto p) {
		// TODO Auto-generated method stub
		String sqlBorrar = "DELETE FROM producto WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		
		try {
			stmt=this.conexion.prepareStatement(sqlBorrar);
			stmt.setLong(1, p.getId());
			stmt.execute();
			
		}catch(SQLException e) { e.printStackTrace();}
		
	}

	public Producto find(Long id) {
		// TODO Auto-generated method stub
		String sqlBuscar= "select * from producto WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		Producto p = null;
		
		try {
			stmt= this.conexion.prepareStatement(sqlBuscar);
			stmt.setLong(1, id);
			
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				p= new Producto();
				p.setId(rs.getLong("id"));
				p.setNombre(rs.getString("nombre"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setPrecio(rs.getFloat("precio"));
			}
		}catch (SQLException e) {e.printStackTrace();  }
		return p;
	}

	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String sqlListar = "select id, nombre, descripcion, precio from producto;";
		List<Producto> listap = new ArrayList<Producto>();
		try {
			stmt= this.conexion.createStatement();
			rs = stmt.executeQuery(sqlListar);
			//Paso 3 - leer datos
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				Float precio = rs.getFloat("precio");
				System.out.println("ID: "+String.valueOf(id)+" - Nombre: "+nombre+" - Descripcion: "+descripcion+" - Precio: "+precio);
			Producto p = new Producto(id,nombre,descripcion,precio);
			
			listap.add(p);
			}
			
		
	}catch(SQLException e) { e.printStackTrace();}
	
		return listap;
	}

	public Long existe(Producto p) {
		// TODO Auto-generated method stub
		

		//String sqlExiste = "SELECT * FROM producto WHERE id =?";
		String sqlExiste = "SELECT id FROM producto WHERE Nombre=? and Descripcion=?; ";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		
		
		try {
			stmt= this.conexion.prepareStatement(sqlExiste);
			
			stmt.setString(1,p.getNombre());
			stmt.setString(2,p.getDescripcion());
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if( rs.next()) { return rs.getLong("id");}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (long)-1;
		
	}

	public Long nuevoid() {
		// TODO Auto-generated method stub
		

		String sqlid= "SELECT MAX(id) FROM producto";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		
		
		try {
			stmt= this.conexion.prepareStatement(sqlid);
			
			
			rs=stmt.executeQuery();
		
		
		rs.next();
		long idnuevo= rs.getLong("MAX(id)")+1;
		return idnuevo;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}

}
