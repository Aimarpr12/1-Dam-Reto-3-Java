package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Moto;
import utils.DBUtils;

public class InsertarMoto {
	public boolean insertarMoto(Moto moto){
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
			String sql = "insert into moto (moto.bastidor, moto.cilindrada) values (?, ?);";
			
			
			preparedStatement = connection.prepareStatement(sql);
			
			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			preparedStatement.setString(1, moto.getBastidor());
			preparedStatement.setInt(2, moto.getCilindrada());
			
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
