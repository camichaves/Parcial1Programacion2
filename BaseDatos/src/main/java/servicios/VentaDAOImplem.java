/**
 * 
 */
package servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IVentaDAO;

import entidades.Producto;
import entidades.Venta;

/**
 * @author Camila
 *
 */
public class VentaDAOImplem implements IVentaDAO {

private java.sql.Connection conexion;
	
	public VentaDAOImplem () {
		this.conexion = AccesoBD.getConexion();
		// TODO Auto-generated constructor stub
	}
	
	public void insertar(Venta v) {
		// TODO Auto-generated method stub
		

		java.sql.PreparedStatement stmt=null;
		String sqlInsertar="insert into venta values (?,?,?,?,?);";
		try {
			
			stmt = this.conexion.prepareStatement(sqlInsertar);
			stmt.setLong(1,v.getId());
			stmt.setLong(2,v.getIdpersona());
			stmt.setString(3,v.getFechaventa());
			stmt.setFloat(4,v.getValorventa());
			stmt.setString(5,v.getDescripcion());
			stmt.execute();
			
		}catch (SQLException e) {e.printStackTrace();}
		
		
	}

	public void actualizar(Venta v) {
		// TODO Auto-generated method stub
		String sqlActualizar = "UPDATE venta set idpersona=?, fecha=?, monto=?, descripcion=? WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		
		try {
			stmt=conexion.prepareStatement(sqlActualizar);
			stmt.setLong(5,v.getId());
			stmt.setLong(1,v.getIdpersona());
			stmt.setString(2,v.getFechaventa());
			stmt.setFloat(3,v.getValorventa());
			stmt.setString(4,v.getDescripcion());
			stmt.execute();
			
		}catch(SQLException e) { e.printStackTrace();}
		
	
	}

	public void borrar(Venta v) {
		// TODO Auto-generated method stub
		

		String sqlBorrar = "DELETE FROM venta WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		
		try {
			stmt=this.conexion.prepareStatement(sqlBorrar);
			stmt.setLong(1, v.getId());
			stmt.execute();
			
		}catch(SQLException e) { e.printStackTrace();}
		
	}

	public Venta find(Long id) {
		// TODO Auto-generated method stub
		
		String sqlBuscar= "select * from venta WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		Venta v= null;
		
		try {
			stmt= this.conexion.prepareStatement(sqlBuscar);
			stmt.setLong(1, id);
			
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				v= new Venta();
				v.setId(rs.getLong("id"));
				v.setIdpersona(rs.getLong("idpersona"));
				v.setFechaventa(rs.getString("fecha"));
				v.setValorventa(rs.getFloat("monto"));
				v.setDescripcion(rs.getString("descripcion"));
				
			}
		}catch (SQLException e) {e.printStackTrace();  }
		return v;
		
	}

	public List<Venta> findAll() {
		// TODO Auto-generated method stub
		
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String sqlListar = "select id, idpersona, fecha, monto, descripcion from venta;";
		List<Venta> listav = new ArrayList<Venta>();
		try {
			stmt= this.conexion.createStatement();
			rs = stmt.executeQuery(sqlListar);
			//Paso 3 - leer datos
			while (rs.next()) {
				Long id = rs.getLong("id");
				Long idpersona = rs.getLong("idpersona");
				String fecha = rs.getString("fecha");
				Float monto = rs.getFloat("monto");
				String descripcion = rs.getString("descripcion");
				
				System.out.println("ID: "+String.valueOf(id)+" - ID Persona: "+String.valueOf(idpersona)+" - Fecha: "+fecha+" - Monto: "+String.valueOf(monto)+" - Descripcion: "+descripcion);
			Venta v = new Venta(id,fecha,monto,descripcion,idpersona);
			
			listav.add(v);
			}
			
		
	}catch(SQLException e) { e.printStackTrace();}
	
		return listav;
	}

	public void relproductos(Producto prod, Venta v) {
		// TODO Auto-generated method stub
		

		java.sql.PreparedStatement stmt=null;
		String sqlInsertar="insert into relventaproducto values (?,?, ?);";
		try {
			
			stmt = this.conexion.prepareStatement(sqlInsertar);
			stmt.setLong(1,this.nuevoidrel());
			stmt.setLong(2,v.getId());
			stmt.setLong(3,prod.getId());
			stmt.execute();
			
		}catch (SQLException e) {e.printStackTrace();}
	}

	private long nuevoidrel() {
		// TODO Auto-generated method stub
		String sqlid= "SELECT MAX(id) FROM relventaproducto";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		
		
		try {
			stmt= this.conexion.prepareStatement(sqlid);
			
			
			rs=stmt.executeQuery();
		
		
		rs.next();
		long nuevoid = rs.getLong("MAX(id)")+1;
		return nuevoid;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return 0;
		
	}

	public long nuevoid() {
		// TODO Auto-generated method stub
		String sqlid= "SELECT MAX(id) FROM venta";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		
		
		try {
			stmt= this.conexion.prepareStatement(sqlid);
			
			
			rs=stmt.executeQuery();
		
		
		rs.next();
		long nuevoid = rs.getLong("MAX(id)")+1;
		return nuevoid;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return 0;
	}

	
	
	

}
