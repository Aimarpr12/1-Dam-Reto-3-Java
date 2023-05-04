package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clases.Venta;
import utils.DBUtils;
import utils.SqlQuerys;

public class ObtenerListaDeTodasLasVentas {
	public List <Venta> getListaDeVentas() {
		List <Venta> response = new ArrayList<Venta>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LAS_VENTAS;
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
				int idVenta = resultSet.getInt("idVenta");
				int precio= resultSet.getInt("precio");
				Date fecha= resultSet.getDate("fecha");
				int idVendedor= resultSet.getInt("idVendedor");
				String matricula = resultSet.getString("idVehiculo");
				String idCliente= resultSet.getString("idCliente");
				
				Venta ventaActual = new Venta(
						idVenta, precio, fecha, matricula, idCliente, idVendedor);
				response.add(ventaActual);
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
