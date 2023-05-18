package modelo;

public class ComboBoxNuevoVendedor {
	protected String dni;
	protected Vendedor vendedor;
	
	public ComboBoxNuevoVendedor(Vendedor vendedor)
	{
		this.vendedor = vendedor;
		if (vendedor != null) {
			this.dni = vendedor.getDni();
		}
	}

	public ComboBoxNuevoVendedor(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString()
	{
		return dni;
	}


	public Vendedor getValue(){
		return vendedor;
	}
}
