package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import error.LogInIncorrectoException;
import modelo.Empleado;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioLogIn implements IRepositorioLogIn{
	@Override
	public void anadirLogIn(String dni, String password){
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
			String sql = "INSERT INTO login "
					+ "(DNI, pass) VALUES (?, ?);";

			preparedStatement = connection.prepareStatement(sql);
			// la fecha hay que transformarla;

			// iniciamos el seteo de variables

			preparedStatement.setString(1, dni);
			preparedStatement.setString(2, password);

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
	public boolean comprobarContrasena(String dni, String passWord) {
		boolean contranaIgual = false;
		// SQL que queremos lanzar
		String sql = SqlQuerys.COMPROBAR_PASS;
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
			preparedStatement.setString(1, dni);
			preparedStatement.setString(2, passWord);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if(resultSet.getInt(1) == 1) {
					contranaIgual = true;
				}
			}

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
		return contranaIgual;
	}
	@Override
	public void updatePass(String dni, String pass){

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
			String sql = "update login set login.pass = ? where login.DNI = ? ;";


			preparedStatement = connection.prepareStatement(sql);

			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html


			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, dni);

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
	public void actualizarLoginPorId(String dni){

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
	@Override
	public Empleado getLogInCorrect(String DNI, String password) throws LogInIncorrectoException{
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
			if(response == null) {
				throw new LogInIncorrectoException("User no encontrado");
			}
			
			// No es posible saber cuantas cosas nos ha devuelto el resultSet.
			// Hay que ir 1 por 1 y guardandolo todo en su objeto Ejemplo correspondiente
		
		} catch (SQLException sqle) {  
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (LogInIncorrectoException e) {
			System.err.println(e.getMessage());
			throw e;
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
	public boolean comprobarSiExisteUser(String dni) {
		System.out.println("aa");
		boolean esRH = false;
		// SQL que queremos lanzar
		String sql = SqlQuerys.USUARIO_ESTA_REGISTRADO;
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
			preparedStatement.setString(1, dni);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if(resultSet.getInt(1) == 0) {
					esRH = true;
				}
			}

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
		return esRH;
	}
}
