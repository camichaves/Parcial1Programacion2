/**
 * 
 */
package servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDAO;
import entidades.Persona;

/**
 * @author Camila
 *
 */
public class PersonaDAOImplem implements IPersonaDAO {
	
	private java.sql.Connection conexion;
	
	public PersonaDAOImplem () {
		this.conexion = AccesoBD.getConexion();
		// TODO Auto-generated constructor stub
	}

	public boolean insertar(Persona p) {
		// TODO Auto-generated method stub
		
		if(this.existe(p)!=-1) {
			p.setId(this.existe(p));
			this.actualizar(p); return true;}
		
		java.sql.PreparedStatement stmt=null;
		String sqlInsertar=null;
		boolean band=false;
		
		if(p.getActivo()==1 || p.getActivo()==0) {
			sqlInsertar="insert into persona values (?,?,?,?,?);";
			band=true;
			}else {
			sqlInsertar="insert into persona values (?,?,?,?,NULL);";}
		
		try {
			
			stmt = this.conexion.prepareStatement(sqlInsertar);
			long id=this.nuevoid();
			stmt.setLong(1,id);
			stmt.setString(2,p.getNombre());
			stmt.setString(3,p.getApellido());
			stmt.setInt(4,p.getDocumento());
			if(band) { stmt.setInt(5,p.getActivo());}
			stmt.execute();
			p.setId(id);
			
		}catch (SQLException e) {e.printStackTrace();}
		
		return true;
	
		
	}

	public void actualizar(Persona p) {
		// TODO Auto-generated method stub
		
		String sqlActualizar=null;
		boolean band=false;
		
		if(p.getActivo()==1 || p.getActivo()==0) {
			sqlActualizar = "UPDATE persona set nombre=?, apellido=?, activo=? WHERE id=?";
			band=true;
			}else {
				sqlActualizar = "UPDATE persona set nombre=?, apellido=? WHERE id=?";}
		
		java.sql.PreparedStatement stmt=null;
		
		try {
			stmt=conexion.prepareStatement(sqlActualizar);
			stmt.setString(1,p.getNombre());
			stmt.setString(2, p.getApellido());
			stmt.setLong(3, p.getId());
			if(band) { stmt.setInt(4,p.getActivo());}
			stmt.execute();
			
		}catch(SQLException e) { e.printStackTrace();}
		
	}

	public void borrar(Persona p) {
		
		// TODO Auto-generated method stub
		
				String sqlBorrar = "DELETE FROM persona WHERE id=?";
				java.sql.PreparedStatement stmt=null;
				
				try {
					stmt=this.conexion.prepareStatement(sqlBorrar);
					stmt.setLong(1, p.getId());
					stmt.execute();
					
				}catch(SQLException e) { e.printStackTrace();}
		
	}

	public Persona find(Long id) {
		// TODO Auto-generated method stub
		
		String sqlBuscar= "select * from persona WHERE id=?";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		Persona p = null;
		
		try {
			stmt= this.conexion.prepareStatement(sqlBuscar);
			stmt.setLong(1, id);
			
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				p= new Persona();
				p.setId(rs.getLong("id"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
			}
		}catch (SQLException e) {e.printStackTrace();  }
		return p;
	}

	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		
		
		
				java.sql.Statement stmt = null;
				ResultSet rs = null;
				String sqlListar = "select id, nombre, apellido,documento from persona;";
				List<Persona> listap = new ArrayList<Persona>();
				try {
					stmt= this.conexion.createStatement();
					rs = stmt.executeQuery(sqlListar);
					//Paso 3 - leer datos
					while (rs.next()) {
						Long id = rs.getLong("id");
						String nombre = rs.getString("nombre");
						String apellido = rs.getString("apellido");
						int documento=rs.getInt("documento");
						System.out.println("ID: "+String.valueOf(id)+" - Nombre: "+nombre+" - Apellido: "+apellido);
					Persona p = new Persona(id,nombre,apellido,documento);
					
					
					//pper el id
					listap.add(p);
					}
					
				
			}catch(SQLException e) { e.printStackTrace();}
			
				return listap;
	}

	public  Long existe(Persona p) {
		// TODO Auto-generated method stub
		
		
		
		String sqlExiste = "SELECT id FROM persona WHERE Nombre =? and Apellido=?; ";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		
		
		try {
			stmt= this.conexion.prepareStatement(sqlExiste);
			
			stmt.setString(1,p.getNombre());
			stmt.setString(2,p.getApellido());
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
	
		String sqlid= "SELECT MAX(id) FROM persona";
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		
		
		try {
			stmt= this.conexion.prepareStatement(sqlid);
			
			
			rs=stmt.executeQuery();
		
		
		rs.next();
		long idnuevo=rs.getLong("MAX(id)")+1;
		return (idnuevo);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}

	public List<Persona> findAllActivos() {
		// TODO Auto-generated method stub
		
		
	
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String sqlListar = "select id, nombre, apellido, documento from persona WHERE activo=1 ;";
		List<Persona> listap = new ArrayList<Persona>();
		try {
			stmt= this.conexion.createStatement();
			rs = stmt.executeQuery(sqlListar);
			//Paso 3 - leer datos
			System.out.println("ACTIVOS:");
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int documento=rs.getInt("documento");
				
				System.out.println("ID: "+String.valueOf(id)+" - Nombre: "+nombre+" - Apellido: "+apellido+" -Documento: "+documento);
			Persona p = new Persona(id,nombre,apellido,documento);
			
			
			//pper el id
			listap.add(p);
			}
			
		
	}catch(SQLException e) { e.printStackTrace();}
	
		return listap;
	}

	public List<Persona> findByNombreOrApellidoLike(String nombre) {
		// TODO Auto-generated method stub
		
		java.sql.PreparedStatement stmt=null;
		ResultSet rs = null;
		String sqlListar = "SELECT * from persona where (Nombre LIKE ?) or (Apellido LIKE ?)";
		List<Persona> listap = new ArrayList<Persona>();
		try {
			
			stmt= this.conexion.prepareStatement(sqlListar);
			String nom="%"+nombre+"%";
			stmt.setString(1, nom);
			stmt.setString(2, nom);
			
			
			rs=stmt.executeQuery();
			
			
			
			System.out.println("CONTIENEN "+nombre+" :");
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nombree = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int documento=rs.getInt("documento");
				
				System.out.println("ID: "+String.valueOf(id)+" - Nombre: "+nombree+" - Apellido: "+apellido+" - Documento: "+documento);
				
			Persona p = new Persona(id,nombree,apellido,documento);
			
			
			
			listap.add(p);
			}
			
		
	}catch(SQLException e) { e.printStackTrace();}
	
		return listap;
	}

	public List<Persona> findAllNoActivos() {
		// TODO Auto-generated method stub
	
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		String sqlListar = "select id, nombre, apellido, documento from persona WHERE activo=0 ;";
		List<Persona> listap = new ArrayList<Persona>();
		try {
			stmt= this.conexion.createStatement();
			rs = stmt.executeQuery(sqlListar);
			//Paso 3 - leer datos
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int documento=rs.getInt("documento");
				//System.out.println("ID: "+String.valueOf(id)+" - Nombre: "+nombre+" - Apellido: "+apellido);
			Persona p = new Persona(id,nombre,apellido,documento);
			
			
			//pper el id
			listap.add(p);
			}
			
		
	}catch(SQLException e) { e.printStackTrace();}
	
		return listap;
		
		
	}
}
	
	


