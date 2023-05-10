package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.Coche;
import modelo.Empleado;
import modelo.Moto;
import modelo.Reparacion;
import modelo.Vehiculo;
import modelo.Venta;
import utils.DBUtils;

public class RepositorioDeInserts {
	public boolean insertarCliente(Cliente cliente) {
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
			String sql = "insert into cliente (cliente.DNI, cliente.nombre, cliente.apellido, cliente.telefono, "
					+ "cliente.direccion, cliente.correo) values (?, ?, ?, ?, ?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setString(1, cliente.getDni());
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3, cliente.getApellido());
			preparedStatement.setInt(4, cliente.getTelefono());
			preparedStatement.setString(5, cliente.getDir());
			preparedStatement.setString(6, cliente.getCorreo());
			
			
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
	
	public boolean insertarClienteVehiculo(ClienteVehiculo clienteVehiculo) {
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
			String sql = "insert into cochecliente (cochecliente.bastidor, cochecliente.idCliente) values (?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setString(1, clienteVehiculo.getBastidor());
			preparedStatement.setString(2, clienteVehiculo.getIdCliente());
			
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
	
	public boolean insertarCoche(Coche coche) {
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
			String sql = "insert into coche (coche.bastidor, coche.motor) values (?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setString(1, coche.getBastidor());
			preparedStatement.setString(2, coche.getMotor().name());
			
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
	
	public boolean insertarMoto(Moto moto){
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
			String sql = "insert into moto (moto.bastidor, moto.cilindrada) values (?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			preparedStatement.setString(1, moto.getBastidor());
			preparedStatement.setInt(2, moto.getCilindrada());
			
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
	
	public Reparacion createReparacion(Reparacion reparacion){
		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Montamos la SQL 
			String sql = "insert into reparacion (reparacion.idVehiculo, reparacion.descripcion,"
					+ " reparacion.coste, reparacion.precio, reparacion.fechaIni, reparacion.idMecanico) values (?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// la fecha hay que transformarla;

			// iniciamos el seteo de variables
			java.sql.Date sqlFechaContratacion = new java.sql.Date(reparacion.getFechaIni().getTime());
			preparedStatement.setString(1, reparacion.getIdVehiculo());
			preparedStatement.setString(2, reparacion.getDescripcion());
			preparedStatement.setDouble(3, reparacion.getCoste());
			preparedStatement.setDouble(4, reparacion.getPrecio());
			preparedStatement.setDate(5, sqlFechaContratacion);
			preparedStatement.setInt(6, reparacion.getIdMecanico());
			
			// para ver la consulta antes de que se ejecute
			//System.out.println(preparedStatement);

			// La ejecutamos...
			preparedStatement.executeUpdate();
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	reparacion.setIdReparacion(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error genérico - " + e.getMessage());
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
		return reparacion;
	}
	
	public boolean insertarVehiculo(Vehiculo vehiculo){
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
			String sql = "insert into vehiculo (vehiculo.bastidor, vehiculo.matricula, vehiculo.marca, "
					+ "vehiculo.modelo, vehiculo.anio) values (?, ?, ?, ?, ?)";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			
			preparedStatement.setString(1, vehiculo.getBastidor());
			preparedStatement.setString(2, vehiculo.getMatricula());
			preparedStatement.setString(3, vehiculo.getMarca());
			preparedStatement.setString(4, vehiculo.getModelo());
			preparedStatement.setInt(5, vehiculo.getAño());
			
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
	
	public Venta createVenta(Venta venta){
		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Montamos la SQL 
			String sql = "insert into venta (venta.idVenta, venta.precio, venta.fecha, venta.idVendedor, "
					+ "venta.idVehiculo, venta.idCliente) values (?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// la fecha hay que transformarla;

			// iniciamos el seteo de variables
			java.sql.Date sqlFechaVenta = new java.sql.Date(venta.getFecha().getTime());
			preparedStatement.setInt(1, venta.getIdVenta());
			preparedStatement.setInt(2, venta.getPrecio());
			preparedStatement.setDate(3, sqlFechaVenta);
			preparedStatement.setInt(4, venta.getIdVendedor());
			preparedStatement.setString(5, venta.getBastidor());
			preparedStatement.setString(6, venta.getIdCliente());
			
			// para ver la consulta antes de que se ejecute
			//System.out.println(preparedStatement);

			// La ejecutamos...
			preparedStatement.executeUpdate();
			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	venta.setIdVenta(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error genérico - " + e.getMessage());
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
		return venta;
	}
	
	public void anadirUser(Empleado empleado){
		// La conexion con BBDD
		Connection connection = null;

		// Vamos a lanzar una sentencia SQL contra la BBDD
		PreparedStatement  preparedStatement  = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);

			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			// Montamos la SQL 
			String sql = "INSERT INTO empleado "
					+ "(DNI, nombre, apellido, telefono, fechNac, fechAlt, dir, correo) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			preparedStatement = connection.prepareStatement(sql);
			// la fecha hay que transformarla;

			// iniciamos el seteo de variables
			java.sql.Date sqlFechaNac = new java.sql.Date(empleado.getFechaNacimiento().getTime());		
			java.sql.Date sqlFechaAlt = new java.sql.Date(empleado.getFechaContratacion().getTime());
			preparedStatement.setString(1, empleado.getDni());
			preparedStatement.setString(2, empleado.getNombre());
			preparedStatement.setString(3, empleado.getApellido());
			preparedStatement.setInt(4, empleado.getNumeroTelefono());
			preparedStatement.setDate(5, sqlFechaNac);
			preparedStatement.setDate(6, sqlFechaAlt);
			preparedStatement.setString(7, empleado.getDireccion());
			preparedStatement.setString(8, empleado.getEmail());
			
			// para ver la consulta antes de que se ejecute
			//System.out.println(preparedStatement);

			// La ejecutamos...
			preparedStatement.executeUpdate();

		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
			sqle.printStackTrace();
		} catch(Exception e){ 
			System.out.println("Error genérico - " + e.getMessage());
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
