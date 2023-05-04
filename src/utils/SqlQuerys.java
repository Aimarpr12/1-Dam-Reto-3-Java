package utils;

public class SqlQuerys {
	
	public static final String COMPROBAR_LOG_IN = "SELECT * FROM empleado where DNI = (select login.DNI from login where login.DNI = ? and login.pass = ?);";
	
	public static final String COMPROBAR_QUE_ES_JEFE = "select count(*) from empleado where id = ? and idJefe is null";
	
	public static final String COMPROBAR_QUE_ES_MECANICO = "select count(*) from mecanico where id = ?";

	public static final String CONSEGUIR_DATOS_DE_MECANICO = "select rango from mecanico where id = ?";
	
	public static final String CONSEGUIR_DATOS_DE_VENDEDOR= "select comision from vendedor where id = ?";

	public static final String USUARIO_ESTA_REGISTRADO = "select count(*) from empleado where empleado.dni = ?";
	
	public static final String COMPROBAR_PASS = "select count(*) from login where login.DNI = ? and login.pass = ?";

	public static final String NOMBRE_USER_BY_ID = "select empleado.nombre from empleado where empleado.id = ?";
	
	public static final String TODOS_LOS_EMPLEADOS = "select * from empleado_mecanico_vendedor";
	
	public static final String TODOS_LOS_EMPLEADOS_MECANICOS = "select * from empleado where id in (select id from mecanico)";
	
	public static final String TODOS_LOS_EMPLEADOS_VENDEDORES = "select * from empleado where id in (select id from vendedor)";

	public static final String TODOS_LAS_VENTAS = "select * from venta";
	
	public static final String TODAS_LAS_REPARACIONES = "select * from reparacion";

	public static final String TODOS_LOS_VEHICULOS = "select * from vehiculos_motos_coches";
	
	public static final String TODOS_LOS_CLIENTES = "SELECT * FROM reto4.cliente;";
	
	public static final String TODOS_LOS_CLIENTEVEHICULOS = "SELECT * FROM reto4.cochecliente;";

	public static final String LIST_EMPLEADOS_SIN_VERIFICAR = "select empleado.DNI, empleado.nombre, empleado.apellido from empleado where empleado.DNI in (select login.DNI from login where login.estaVerificado = false)";
}

