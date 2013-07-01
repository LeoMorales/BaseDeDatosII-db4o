package tuto_db4o;

import java.util.ArrayList;

public class MyMarca {

	private String marca;
	private ArrayList<Pilot> pilotos;
	
	public MyMarca(String marca) {
		// TODO Auto-generated constructor stub
		this();
		this.setMarca(marca);
	}
	
	public MyMarca() {
		// TODO Auto-generated constructor stub
		this.pilotos = new ArrayList<Pilot>();
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public ArrayList<Pilot> getPilotos() {
		return pilotos;
	}

	public void setPilotos(ArrayList<Pilot> pilotos) {
		this.pilotos = pilotos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Marca: " + this.getMarca();
	}
}
