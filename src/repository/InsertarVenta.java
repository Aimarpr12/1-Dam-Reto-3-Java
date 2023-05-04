package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Reparacion;
import clases.Venta;
import utils.DBUtils;

public class InsertarVenta {
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
}
