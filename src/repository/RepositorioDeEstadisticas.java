package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.EstadisticasDeVentas;
import utils.DBUtils;

public class RepositorioDeEstadisticas {
	public List <EstadisticasDeVentas> obtenerTopDosVendedores() {
	    // La conexión con la base de datos
		List <EstadisticasDeVentas> response = new ArrayList <EstadisticasDeVentas>();
	    Connection connection = null;

	    // Vamos a lanzar una sentencia SQL contra la base de datos
	    PreparedStatement preparedStatement = null;

	    try {
	        // El driver que vamos a usar
	        Class.forName(DBUtils.DRIVER);

	        // Abrimos la conexión con la base de datos
	        connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

	        // Montamos la sentencia SQL para llamar al procedimiento almacenado
	        String sql = "{CALL top_dos_vendedores()}";

	        // Preparamos la llamada al procedimiento almacenado
	        preparedStatement = connection.prepareStatement(sql);

	        // Ejecutamos el procedimiento almacenado
	        preparedStatement.execute();

	        // Recuperamos los resultados
	        
	        ResultSet resultSet = preparedStatement.getResultSet();
	        
	        // Iteramos sobre los resultados
	        while (resultSet.next()) {
	            String nombreCompleto = resultSet.getString(1);
	            String totalComision = resultSet.getString(2);
	            EstadisticasDeVentas estadisticasDeVentas = new EstadisticasDeVentas(nombreCompleto, totalComision);
	            response.add(estadisticasDeVentas);
	            // System.out.println(nombreCompleto + " ha ganado " + String.format("%.2f", totalComision) + " € de comisión el mes pasado.");
	        }
	        
	        resultSet.close();
	        
	        preparedStatement.getMoreResults();
	        
	        resultSet = preparedStatement.getResultSet();
	        
	        while (resultSet.next()) {
	            String nombreCompleto = resultSet.getString(1);
	            String totalComision = resultSet.getString(2);
	            EstadisticasDeVentas estadisticasDeVentas = new EstadisticasDeVentas(nombreCompleto, totalComision);
	            response.add(estadisticasDeVentas);
	            // System.out.println(nombreCompleto + " ha ganado " + String.format("%.2f", totalComision) + " € de comisión el mes pasado.");
	        }
	        
	    } catch (SQLException sqle) {
	        System.out.println("Error con la base de datos - " + sqle.getMessage());
	        sqle.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Error genérico - " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        // Cerramos los recursos en orden inverso a como los abrimos
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (Exception e) {
	            // No hace falta hacer nada
	        }
	        try {
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (Exception e) {
	            // No hace falta hacer nada
	        }
	    }
	    return response;
	}

}
