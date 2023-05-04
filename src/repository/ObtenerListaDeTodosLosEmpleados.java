package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import clases.Empleado;
import clases.Mecanico;
import clases.TipoDeEmpleado;
import clases.Vendedor;
import utils.DBUtils;
import utils.SqlQuerys;

public class ObtenerListaDeTodosLosEmpleados {
	public List <Empleado> getListaDeEmpleados() {
		List <Empleado> response = new ArrayList<Empleado>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LOS_EMPLEADOS;
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
				int  id = resultSet.getInt("id");
				String dni = resultSet.getString("DNI");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				int telefono = resultSet.getInt("telefono");
				Date fechaNacimiento= resultSet.getDate("fechNac");
				Date fechaAlt = resultSet.getDate("fechAlt");
				String dir = resultSet.getString("dir");
				String correo = resultSet.getString("correo");
				int salario = resultSet.getInt("salario");
				int idjefe =  resultSet.getInt("idJefe");
				String tipoDeEmpleado = resultSet.getString("tipo_empleado");
				if("jefe".equals(tipoDeEmpleado)) {
					Empleado empleadoActual = new Empleado(
							id, dni, nombre, apellido, fechaNacimiento, fechaAlt, telefono, 
							dir, correo, salario, idjefe, TipoDeEmpleado.jefe);
					response.add(empleadoActual);					
				}else if("mecanico".equals(tipoDeEmpleado)) {
					String rango = resultSet.getString("rango");
					Mecanico mecanicoActual = new Mecanico(
							id, dni, nombre, apellido, fechaNacimiento, fechaAlt, telefono, 
							dir, correo, salario, idjefe, TipoDeEmpleado.mecanico, rango
							);
					response.add(mecanicoActual);	
				}else if("vendedor".equals(tipoDeEmpleado)) {
					Double comision = resultSet.getDouble("comision");
					Vendedor vendedorActual = new Vendedor(
							id, dni, nombre, apellido, fechaNacimiento, fechaAlt, telefono, 
							dir, correo, salario, idjefe, TipoDeEmpleado.vendedor, comision
							);
					
					response.add(vendedorActual);	
				}
				
				//EmpleadoAbstracto empleado = new E
	        }
			System.out.println(response.size());
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
