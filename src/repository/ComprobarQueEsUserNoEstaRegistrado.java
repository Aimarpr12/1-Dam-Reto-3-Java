
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBUtils;
import utils.SqlQuerys;

public class ComprobarQueEsUserNoEstaRegistrado {

		public boolean comprobarSiExisteUser(String dni) {
			boolean esRH = false;
			// SQL que queremos lanzar
			String sql = SqlQuerys.USUARIO_ESTA_REGISTRADO;
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
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, dni);
				
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					if(resultSet.getInt(1) == 0) {
						esRH = true;
					}
		        }
			
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
			return esRH;
		}
	

}
