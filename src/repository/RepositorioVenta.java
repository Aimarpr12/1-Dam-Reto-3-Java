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

import modelo.Venta;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioVenta implements IRepositorioVenta{
	@Override
	public List <Venta> getListaDeVentas() {
		List <Venta> response = new ArrayList<Venta>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LAS_VENTAS;
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
				int idVenta = resultSet.getInt("idVenta");
				int precio= resultSet.getInt("precio");
				Date fecha= resultSet.getDate("fecha");
				int idVendedor= resultSet.getInt("idVendedor");
				String matricula = resultSet.getString("idVehiculo");
				String idCliente= resultSet.getString("idCliente");

				Venta ventaActual = new Venta(
						idVenta, precio, fecha, matricula, idCliente, idVendedor);
				response.add(ventaActual);
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

	@Override
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
