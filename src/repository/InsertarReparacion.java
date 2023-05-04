package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Reparacion;
import utils.DBUtils;

public class InsertarReparacion {
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
}
