package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.Empleado;
import utils.DBUtils;
import utils.SqlQuerys;

public class ListDeEmpleadosPorVerificar {
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
}
