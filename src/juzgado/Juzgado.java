package juzgado;

public class Juzgado {

	enum TipoFuero{civil, comercial, laboral, penal};
	
	private TipoFuero fuero;
	private Juez juez;
	private String domicilio;
	private String localidad;
	
	public Juzgado(TipoFuero fuero, Juez juez, String domicilio, String localidad) {
		// Crea un juzgado.
		this.fuero = fuero;
		this.juez = juez;
		this.domicilio = domicilio;
		this.localidad = localidad;
	}
	
}
