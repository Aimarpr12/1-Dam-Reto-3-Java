package Utils;

public class SqlQuerys {
	
	public static final String COMPROBAR_LOG_IN = "SELECT * FROM empleado where DNI = (select login.DNI from login where login.DNI = ? and login.pass = ?);";
	
	public static final String COMPROBAR_QUE_ES_JEFE = "select count(*) from empleado where id = 1 and idJefe = null";
	
	public static final String COMPROBAR_QUE_ES_MECANICO = "select count(*) from mecanico where id = ?";

	public static final String CONSEGUIR_DATOS_DE_MECANICO = "select rango from mecanico where id = ?";

	public static final String USUARIO_ESTA_REGISTRADO = "select count(*) from empleado where empleado.dni = ?";
	
	
}
