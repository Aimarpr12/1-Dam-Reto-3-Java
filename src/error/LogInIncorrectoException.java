package error;

public class LogInIncorrectoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public LogInIncorrectoException(String mensaje) {
        super(mensaje);
    }
	
}
