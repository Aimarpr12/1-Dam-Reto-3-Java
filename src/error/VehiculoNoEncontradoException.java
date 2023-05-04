package error;

public class VehiculoNoEncontradoException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehiculoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
