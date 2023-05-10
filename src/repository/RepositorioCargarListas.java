package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Cliente;
import modelo.ClienteVehiculo;
import modelo.Coche;
import modelo.Empleado;
import modelo.Mecanico;
import modelo.Moto;
import modelo.Motor;
import modelo.Reparacion;
import modelo.TipoDeEmpleado;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import modelo.Vendedor;
import modelo.Venta;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioCargarListas {
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
	
	public List <ClienteVehiculo> getListaDeClienteVehiculos() {
		List <ClienteVehiculo> response = new ArrayList<ClienteVehiculo>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LOS_CLIENTEVEHICULOS;
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
				String bastidor = resultSet.getString("bastidor");
				String idCliente = resultSet.getString("idCliente");
				
				ClienteVehiculo clienteVehiculoActual = new ClienteVehiculo(bastidor, idCliente);
				response.add(clienteVehiculoActual);
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
	
	public List <Vehiculo> getListaVehiculos() {
		List <Vehiculo> response = new ArrayList<Vehiculo>();
		// SQL que queremos lanzar
		String sql = SqlQuerys.TODOS_LOS_VEHICULOS;
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
				String bastidor = resultSet.getString("bastidor");
				String matricula = resultSet.getString("matricula");
				String marca = resultSet.getString("marca");
				String modelo= resultSet.getString("modelo");
				int anio = resultSet.getInt("anio");
				String tipoVehiculo= resultSet.getString("tipo_Vehiculo");
				
				if("moto".equals(tipoVehiculo) ) {
					TipoDeVehiculo tipoDeVehiculoEnEnum = TipoDeVehiculo.moto;
					int cilindrada = resultSet.getInt("cilindrada");
					Moto vehiculoActual = new Moto(
							bastidor, matricula, marca, modelo, anio, tipoDeVehiculoEnEnum, cilindrada);
					response.add(vehiculoActual);
				}else if ("coche".equals(tipoVehiculo)) {
					TipoDeVehiculo tipoDeVehiculoEnEnum = TipoDeVehiculo.coche;
					String motor = resultSet.getString("motor");
					Motor motorEnum = null;
					if("diesel".equals(motor)) {
						motorEnum = Motor.Diesel;
					}else if("electrico".equals(motor)) {
						motorEnum = Motor.Electrico;
					}else if("gasolina".equals(motor)) {
						motorEnum = Motor.Gasolina;
					}
					Coche vehiculoActual = new Coche(
							bastidor, matricula, marca, modelo, anio, tipoDeVehiculoEnEnum, motorEnum);
					response.add(vehiculoActual);
				}
				
				
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
