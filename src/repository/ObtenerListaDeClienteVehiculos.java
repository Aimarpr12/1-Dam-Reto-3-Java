package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import clases.ClienteVehiculo;
import utils.DBUtils;
import utils.SqlQuerys;

public class ObtenerListaDeClienteVehiculos {
	public List <ClienteVehiculo> getListaDeClienteVehiculos() {
		List <ClienteVehiculo> response = new ArrayList<ClienteVehiculo>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LOS_CLIENTEVEHICULOS;
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
				String bastidor = resultSet.getString("bastidor");
				String idCliente = resultSet.getString("idCliente");
				
				ClienteVehiculo clienteVehiculoActual = new ClienteVehiculo(bastidor, idCliente);
				response.add(clienteVehiculoActual);
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
}
