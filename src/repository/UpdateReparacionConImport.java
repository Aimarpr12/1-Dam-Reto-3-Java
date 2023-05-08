package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Reparacion;
import modelo.Venta;
import utils.DBUtils;

public class UpdateReparacionConImport {
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
}
