package clases;


public class ComboBoxMatriculaAnadirReparacion {
	protected String matricula;
	protected String bastidor;
	
	public ComboBoxMatriculaAnadirReparacion(String bastidor, String matricula)
	{
		this.bastidor = bastidor;
		if (bastidor != null) {
			this.matricula = matricula;
		}
	}

	public ComboBoxMatriculaAnadirReparacion(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString()
	{
		return matricula;
	}


	public String getValue(){
		return bastidor;
	}
}
