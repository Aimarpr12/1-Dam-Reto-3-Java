package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Reparacion;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioReparacion implements IRepositorioReparacion{
	@Override
	public boolean updateReparacion(int id, Date fechaFin){
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
			String sql = "update reparacion set reparacion.fechaFin= ? where reparacion.idReparacion = ?";


			preparedStatement = connection.prepareStatement(sql);

			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html
			java.sql.Date sqlDate = new java.sql.Date(fechaFin.getTime());
			preparedStatement.setDate(1, sqlDate);
			preparedStatement.setInt(2, id);

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
	@Override
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
	@Override
	public List <Reparacion> getListaDeReparaciones() {
		List <Reparacion> response = new ArrayList<Reparacion>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODAS_LAS_REPARACIONES;
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
				int  idReparacion = resultSet.getInt("idReparacion");
				String idVehiculo = resultSet.getString("idVehiculo");
				String descripcion = resultSet.getString("descripcion");
				double coste = resultSet.getDouble("coste");
				double precio = resultSet.getDouble("precio");
				Date fechaIni= resultSet.getDate("fechaIni");
				Date fechaFin = resultSet.getDate("fechaFin");
				int idMecanico = resultSet.getInt("idMecanico");

				Reparacion reparacionActual = new Reparacion(
						idReparacion, idVehiculo, descripcion, coste, precio, fechaIni, fechaFin, 
						idMecanico);
				response.add(reparacionActual);
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
	@Override
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
