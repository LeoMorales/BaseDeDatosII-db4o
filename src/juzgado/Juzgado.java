package juzgado;

public class Juzgado {

	enum TipoFuero{civil, comercial, laboral, penal};
	
	private TipoFuero fuero;
	private Juez juez;
	private String domicilio;
	private String localidad;
	
	//Constructor:
	public Juzgado(TipoFuero fuero, Juez juez, String domicilio, String localidad) {
		// Crea un juzgado.
		this.fuero = fuero;
		this.juez = juez;
		this.domicilio = domicilio;
		this.localidad = localidad;
	}

	//Getters and Setters:
	public TipoFuero getFuero() {
		return fuero;
	}
	public Juez getJuez() {
		return juez;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setFuero(TipoFuero fuero) {
		this.fuero = fuero;
	}
	public void setJuez(Juez juez) {
		this.juez = juez;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "fuero: "+this.getFuero()+"| juez: "+this.getJuez();
	}
}
