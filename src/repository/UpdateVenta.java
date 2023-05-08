package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Venta;
import utils.DBUtils;

public class UpdateVenta {
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
}
