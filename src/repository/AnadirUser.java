package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Empleado;
import utils.DBUtils;

public class AnadirUser {
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
}
