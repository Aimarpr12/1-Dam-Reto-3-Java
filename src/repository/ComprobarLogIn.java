package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.Empleado;
import utils.DBUtils;
import utils.SqlQuerys;

public class ComprobarLogIn {
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
}
