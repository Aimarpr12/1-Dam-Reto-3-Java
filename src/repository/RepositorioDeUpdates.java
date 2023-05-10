package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import modelo.Empleado;
import modelo.Mecanico;
import modelo.Reparacion;
import modelo.Vendedor;
import modelo.Venta;
import utils.DBUtils;

public class RepositorioDeUpdates {
	public boolean insertarComision(Vendedor vendedor) {
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "insert into vendedor (vendedor.id, vendedor.comision) values (?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setInt(1, vendedor.getId());
			preparedStatement.setDouble(2, vendedor.getComision());
			
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
	public boolean updateDir(String dni, String dir){
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update empleado set empleado.dir = ? where empleado.DNI = ?";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setString(1, dir);
			preparedStatement.setString(2, dni);
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
	public boolean updateIdJefe(String dni, int idJefe){
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update empleado set empleado.idJefe = ? where empleado.DNI = ?";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setInt(1, idJefe);
			preparedStatement.setString(2, dni);
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
	public boolean insertarRango(Mecanico mecanico) {
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "insert into mecanico (mecanico.id, mecanico.rango) values (?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setInt(1, mecanico.getId());
			preparedStatement.setString(2, mecanico.getRango());
			
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}

	public boolean updateReparacion(int id, Date fechaFin){
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update reparacion set reparacion.fechaFin= ? where reparacion.idReparacion = ?";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			java.sql.Date sqlDate = new java.sql.Date(fechaFin.getTime());
			preparedStatement.setDate(1, sqlDate);
			preparedStatement.setInt(2, id);
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
	public boolean updateReparacion(Reparacion reparacion){
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update reparacion set reparacion.idVehiculo = ?, reparacion.descripcion = ?,"
					+ " reparacion.coste = ?, reparacion.precio = ?, reparacion.fechaIni = ?, "
					+ "reparacion.fechaFin = ?, reparacion.idMecanico = ? where reparacion.idReparacion = ?";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			java.sql.Date sqlFechaInicio = new java.sql.Date(reparacion.getFechaIni().getTime());
			java.sql.Date sqlFechaFin = new java.sql.Date(reparacion.getFechaFin().getTime());
			preparedStatement.setString(1, reparacion.getIdVehiculo());
			preparedStatement.setString(2, reparacion.getDescripcion());
			preparedStatement.setDouble(3, reparacion.getCoste());
			preparedStatement.setDouble(4, reparacion.getPrecio());
			preparedStatement.setDate(5, sqlFechaInicio);
			preparedStatement.setDate(6, sqlFechaFin);
			preparedStatement.setInt(7, reparacion.getIdMecanico());
			preparedStatement.setInt(8, reparacion.getIdReparacion());
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
	public boolean updateSallary(String dni, int salario){
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update empleado set empleado.salario = ? where empleado.DNI = ?";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setInt(1, salario);
			preparedStatement.setString(2, dni);
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
	public boolean updateTelefono(String dni, int telefono){
		boolean updateCorrect = false;
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update empleado set empleado.telefono = ? where empleado.DNI = ?";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setInt(1, telefono);
			preparedStatement.setString(2, dni);
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			updateCorrect = true;
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
		return updateCorrect;
	}
	
public void updateUser(Empleado user, int id){
		
		// La conexion con BBDD
		Connection connection = null;
		
		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;
		
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			
			// Montamos la SQL. Las ? se rellenan a continuacion
			// String sql = "update  emple set edad = ? where nombre = ?";
			String sql = "update empleado "
					+ "set empleado.nombre = ?, "
					+ "empleado.apellido = ?, "
					+ "empleado.telefono = ?, "
					+ "empleado.fechNac = ?, "
					+ "empleado.dir = ?, "
					+ "empleado.correo = ? "
					+ "where empleado.id = ?;";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			java.util.Date fechaNacimiento = user.getFechaNacimiento();
			java.sql.Date sqlFechaContratacion = new java.sql.Date(fechaNacimiento.getTime());
			
			preparedStatement.setString(1, user.getNombre());
			preparedStatement.setString(2, user.getApellido());
			preparedStatement.setInt(3, user.getNumeroTelefono());
			preparedStatement.setDate(4, sqlFechaContratacion);
			preparedStatement.setString(5, user.getDireccion());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setInt(7, id);
			
			// para ver la consulta antes de que se ejecute
			
			// La ejecutamos...
			preparedStatement.executeUpdate();
			
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error generico - " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};
			try {
				if (connection != null) { 
					connection.close(); 
				}
			} catch(Exception e){ 
				// No hace falta
			};					
		}
	}

public boolean updateVenta(Venta venta){
	boolean updateCorrect = false;
	// La conexion con BBDD
	Connection connection = null;
	
	// Vamos a lanzar una sentencia SQL contra la BBDD
	PreparedStatement  preparedStatement  = null;
	
	try {
		// El Driver que vamos a usar
		Class.forName(DBUtils.DRIVER);
		
		// Abrimos la conexion con BBDD
		connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		
		// Montamos la SQL. Las ? se rellenan a continuacion
		// String sql = "update  emple set edad = ? where nombre = ?";
		String sql = "update venta set venta.precio = ?, venta.fecha = ?, venta.idVendedor = ?, "
				+ "venta.idVehiculo = ?, venta.idCliente = ? where venta.idVenta = ?";
		
		
		preparedStatement = connection.prepareStatement(sql);
		
		// La fecha hay que formatearla ya que Date guarda en milisegundos
		// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
		java.sql.Date sqlFecha = new java.sql.Date(venta.getFecha().getTime());
		preparedStatement.setInt(1, venta.getPrecio());
		preparedStatement.setDate(2, sqlFecha);
		preparedStatement.setInt(3, venta.getIdVendedor());
		preparedStatement.setString(4, venta.getBastidor());
		preparedStatement.setString(5, venta.getIdCliente());
		preparedStatement.setInt(6, venta.getIdVenta());
		// para ver la consulta antes de que se ejecute
		
		// La ejecutamos...
		preparedStatement.executeUpdate();
		updateCorrect = true;
	} catch (SQLException sqle) {  
		System.out.println("Error con la BBDD - " + sqle.getMessage());
		sqle.printStackTrace();
	} catch(Exception e){ 
		System.out.println("Error generico - " + e.getMessage());
		e.printStackTrace();
	} finally {
		// Cerramos al reves de como las abrimos
		try {
			if (preparedStatement != null) {
				preparedStatement.close(); 
			}
		} catch(Exception e){ 
			// No hace falta
		};
		try {
			if (connection != null) { 
				connection.close(); 
			}
		} catch(Exception e){ 
			// No hace falta
		};					
	}
	return updateCorrect;
}

public void actualizarBarcoPorId(String dni){
	
	// La conexion con BBDD
	Connection connection = null;
	
	// Vamos a lanzar una sentencia SQL contra la BBDD
	PreparedStatement  preparedStatement  = null;
	
	try {
		// El Driver que vamos a usar
		Class.forName(DBUtils.DRIVER);
		
		// Abrimos la conexion con BBDD
		connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		
		// Montamos la SQL. Las ? se rellenan a continuacion
		// String sql = "update  emple set edad = ? where nombre = ?";
		String sql = "update login set login.estaVerificado = true where login.DNI = ?;";
		
		
		preparedStatement = connection.prepareStatement(sql);
		
		// La fecha hay que formatearla ya que Date guarda en milisegundos
		// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
		
		
		preparedStatement.setString(1, dni);
		
		// para ver la consulta antes de que se ejecute
		
		// La ejecutamos...
		preparedStatement.executeUpdate();
		
	} catch (SQLException sqle) {  
		System.out.println("Error con la BBDD - " + sqle.getMessage());
		sqle.printStackTrace();
	} catch(Exception e){ 
		System.out.println("Error generico - " + e.getMessage());
		e.printStackTrace();
	} finally {
		// Cerramos al reves de como las abrimos
		try {
			if (preparedStatement != null) {
				preparedStatement.close(); 
			}
		} catch(Exception e){ 
			// No hace falta
		};
		try {
			if (connection != null) { 
				connection.close(); 
			}
		} catch(Exception e){ 
			// No hace falta
		};					
	}
}
}
