package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioCliente implements IRepositorioCliente{

	@Override
	public boolean insertarCliente(Cliente cliente) {
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
			String sql = "insert into cliente (cliente.DNI, cliente.nombre, cliente.apellido, cliente.telefono, "
					+ "cliente.direccion, cliente.correo) values (?, ?, ?, ?, ?, ?);";


			preparedStatement = connection.prepareStatement(sql);

			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html

			preparedStatement.setString(1, cliente.getDni());
			preparedStatement.setString(2, cliente.getNombre());
			preparedStatement.setString(3, cliente.getApellido());
			preparedStatement.setInt(4, cliente.getTelefono());
			preparedStatement.setString(5, cliente.getDir());
			preparedStatement.setString(6, cliente.getCorreo());


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
	public List <Cliente> getListaDeClientes() {
		List <Cliente> response = new ArrayList<Cliente>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LOS_CLIENTES;
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
				String  dni = resultSet.getString("dni");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				int telefono = resultSet.getInt("telefono");
				String direccion= resultSet.getString("direccion");
				String correo = resultSet.getString("correo");

				Cliente clienteActual = new Cliente(
						dni, nombre, apellido, telefono, direccion, correo);
				response.add(clienteActual);
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

}