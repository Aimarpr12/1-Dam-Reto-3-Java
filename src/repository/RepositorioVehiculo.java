package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Coche;
import modelo.Moto;
import modelo.Motor;
import modelo.TipoDeVehiculo;
import modelo.Vehiculo;
import utils.DBUtils;
import utils.SqlQuerys;

public class RepositorioVehiculo implements IRepositorioVehiculo{
	@Override
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

	@Override
	public boolean insertarVehiculo(Vehiculo vehiculo){
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
			String sql = "insert into vehiculo (vehiculo.bastidor, vehiculo.matricula, vehiculo.marca, "
					+ "vehiculo.modelo, vehiculo.anio) values (?, ?, ?, ?, ?)";


			preparedStatement = connection.prepareStatement(sql);

			// La fecha hay que formatearla ya que Date guarda en milisegundos
			// https://docs.oracle.com/javase/8/docs/api/java/util/Date.html

			preparedStatement.setString(1, vehiculo.getBastidor());
			preparedStatement.setString(2, vehiculo.getMatricula());
			preparedStatement.setString(3, vehiculo.getMarca());
			preparedStatement.setString(4, vehiculo.getModelo());
			preparedStatement.setInt(5, vehiculo.getAño());

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
