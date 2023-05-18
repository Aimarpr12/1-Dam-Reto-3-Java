package modelo;

public class ComboBoxNuevoMecanico {
	protected String dni;
	protected Mecanico mecanico;
	
	public ComboBoxNuevoMecanico(Mecanico mecanico)
	{
		this.mecanico = mecanico;
		if (mecanico != null) {
			this.dni = mecanico.getDni();
		}
	}

	public ComboBoxNuevoMecanico(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString()
	{
		return dni;
	}


	public Mecanico getValue(){
		return mecanico;
	}
}
