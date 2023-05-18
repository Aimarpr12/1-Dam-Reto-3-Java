 package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Empleado;
import modelo.Mecanico;
import modelo.TipoDeEmpleado;
import modelo.Vendedor;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioEmpleado implements IRepositorioEmpleado{
	@Override
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
	@Override
	public Empleado getLogInCorrect(String DNI, String password) {
		Empleado response = null;
		// SQL que queremos lanzar
		String sql = SqlQuerys.COMPROBAR_LOG_IN;
		// La conexion con BBDD
		Connection connection = null;
		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			// Vamos a lanzar la sentencia...
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, DNI);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				// Obtenemos el valor de la primera columna (0 ó 1) y lo convertimos a booleano
				int id = resultSet.getInt("id");
				String dni = resultSet.getString("DNI");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				int telefono = resultSet.getInt("telefono");
				Date fechNac = resultSet.getDate("fechNac");
				Date fechAlt = resultSet.getDate("fechAlt");
				String dir = resultSet.getString("dir");
				String correo = resultSet.getString("correo");
				int salario = resultSet.getInt("salario");
				int idJefe = resultSet.getInt("idJefe");

				Empleado empelado = new Empleado(id, dni, nombre, apellido, fechNac, fechAlt, telefono, dir, correo, salario, idJefe);
				response = empelado;
			}
			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente

		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			e.printStackTrace();
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(Exception e){ 
				// No hace falta 
			};
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
		return response;
	}
	@Override
	public List <Empleado> getListaDeEmpleadosPorVerificar() {
		List <Empleado> response = new ArrayList<Empleado>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.LIST_EMPLEADOS_SIN_VERIFICAR;
		// La conexion con BBDD
		Connection connection = null;
		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		java.sql.Statement statement = null;
		ResultSet resultSet = null;
		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			// Vamos a lanzar la sentencia...
			// Vamos a lanzar la sentencia...
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				// Obtenemos el valor de la primera columna (0 ó 1) y lo convertimos a booleano
				String dni = resultSet.getString("DNI");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");

				Empleado empelado = new Empleado(dni, nombre, apellido);
				response.add(empelado);
				//EmpleadoAbstracto empleado = new E
			}
			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente

		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			e.printStackTrace();
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(Exception e){ 
				// No hace falta 
			};
			try {
				if (statement != null) { 
					statement.close();
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
		return response;
	}
	
	@Override
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
	@Override
	public List <Empleado> getListaDeEmpleados() {
		List <Empleado> response = new ArrayList<Empleado>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LOS_EMPLEADOS;
		// La conexion con BBDD
		Connection connection = null;
		// Vamos a lanzar una sentencia SQL contra la BBDD
		// Result set va a contener todo lo que devuelve la BBDD
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// El Driver que vamos a usar
			Class.forName(DBUtils.DRIVER);
			// Abrimos la conexion con BBDD
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			// Vamos a lanzar la sentencia...
			// Vamos a lanzar la sentencia...
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// Obtenemos el valor de la primera columna (0 ó 1) y lo convertimos a booleano
				int  id = resultSet.getInt("id");
				String dni = resultSet.getString("DNI");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				int telefono = resultSet.getInt("telefono");
				Date fechaNacimiento= resultSet.getDate("fechNac");
				Date fechaAlt = resultSet.getDate("fechAlt");
				String dir = resultSet.getString("dir");
				String correo = resultSet.getString("correo");
				int salario = resultSet.getInt("salario");
				int idjefe =  resultSet.getInt("idJefe");
				String tipoDeEmpleado = resultSet.getString("tipo_empleado");
				if("jefe".equals(tipoDeEmpleado)) {
					Empleado empleadoActual = new Empleado(
							id, dni, nombre, apellido, fechaNacimiento, fechaAlt, telefono, 
							dir, correo, salario, idjefe, TipoDeEmpleado.jefe);
					response.add(empleadoActual);					
				}else if("mecanico".equals(tipoDeEmpleado)) {
					String rango = resultSet.getString("rango");
					Mecanico mecanicoActual = new Mecanico(
							id, dni, nombre, apellido, fechaNacimiento, fechaAlt, telefono, 
							dir, correo, salario, idjefe, TipoDeEmpleado.mecanico, rango
							);
					response.add(mecanicoActual);	
				}else if("vendedor".equals(tipoDeEmpleado)) {
					Double comision = resultSet.getDouble("comision");
					Vendedor vendedorActual = new Vendedor(
							id, dni, nombre, apellido, fechaNacimiento, fechaAlt, telefono, 
							dir, correo, salario, idjefe, TipoDeEmpleado.vendedor, comision
							);

					response.add(vendedorActual);	
				}

				//EmpleadoAbstracto empleado = new E
			}
			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente

		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch(Exception e){ 
			e.printStackTrace();
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch(Exception e){ 
				// No hace falta 
			};
			try {
				if (resultSet != null) { 
					resultSet.close();
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
		return response;
	}
	
	@Override
	public boolean cambiarAMecanico(int id) {
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
			 String sql1 = "UPDATE venta SET idVendedor = NULL WHERE idVendedor = ?";
		        preparedStatement = connection.prepareStatement(sql1);
		        preparedStatement.setInt(1, id);
		        preparedStatement.executeUpdate();

		        // Sentencia 2: Eliminar el registro correspondiente al vendedor con el ID proporcionado de la tabla "vendedor"
		        String sql2 = "DELETE FROM vendedor WHERE id = ?";
		        preparedStatement = connection.prepareStatement(sql2);
		        preparedStatement.setInt(1, id);
		        preparedStatement.executeUpdate();

		        // Sentencia 3: Llamar a la función almacenada en la base de datos "cambiarPuesto"
		        String sql3 = "CALL cambiarPuesto(?, ?)";
		        preparedStatement = connection.prepareStatement(sql3);
		        preparedStatement.setInt(1, id);
		        preparedStatement.setString(2, "mecanico");
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
	
	@Override
	public boolean cambiarAVendedor(int id) {
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
			
			 String sql1 = "UPDATE reparacion SET idMecanico = NULL WHERE idMecanico = ?";
		        preparedStatement = connection.prepareStatement(sql1);
		        preparedStatement.setInt(1, id);
		        preparedStatement.executeUpdate();

		        // Sentencia 2: Eliminar el registro correspondiente al vendedor con el ID proporcionado de la tabla "vendedor"
		        String sql2 = "DELETE FROM mecanico WHERE id = ?";
		        preparedStatement = connection.prepareStatement(sql2);
		        preparedStatement.setInt(1, id);
		        preparedStatement.executeUpdate();

		        // Sentencia 3: Llamar a la función almacenada en la base de datos "cambiarPuesto"
		        String sql3 = "CALL cambiarPuesto(?, ?)";
		        preparedStatement = connection.prepareStatement(sql3);
		        preparedStatement.setInt(1, id);
		        preparedStatement.setString(2, "vendedor");
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
}
