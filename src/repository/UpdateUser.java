package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Empleado;
import utils.DBUtils;

public class UpdateUser {
public void updateUser(Empleado user, int id){
		
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
			String sql = "update empleado "
					+ "set empleado.nombre = ?, "
					+ "empleado.apellido = ?, "
					+ "empleado.telefono = ?, "
					+ "empleado.fechNac = ?, "
					+ "empleado.dir = ?, "
					+ "empleado.correo = ? "
					+ "where empleado.id = ?;";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			java.util.Date fechaNacimiento = user.getFechaNacimiento();
			java.sql.Date sqlFechaContratacion = new java.sql.Date(fechaNacimiento.getTime());
			
			preparedStatement.setString(1, user.getNombre());
			preparedStatement.setString(2, user.getApellido());
			preparedStatement.setInt(3, user.getNumeroTelefono());
			preparedStatement.setDate(4, sqlFechaContratacion);
			preparedStatement.setString(5, user.getDireccion());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setInt(7, id);
			
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
}
