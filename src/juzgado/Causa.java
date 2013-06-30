package juzgado;

import java.util.ArrayList;

import juzgado.Juzgado.TipoFuero;

public class Causa {
	
	private Juzgado juzgado;
	private Integer nroExpediente;
	private ArrayList<Persona> imputados;
	private ArrayList<Persona> testigos;
	private String sentencia;
	
	//Constructor:
	public Causa(Juzgado juzgado, Integer nroExpediente, ArrayList<Persona> imputados, ArrayList<Persona> testigos, String sentencia) {
		this.juzgado = juzgado;
		this.nroExpediente = nroExpediente;
		this.imputados = imputados;
		this.testigos = testigos;
		this.sentencia = sentencia;
		//TODO: Es esto correcto?
		//Agregarse ella misma a la coleccion de Causas del juzgado. Existe una doble referncia. Motivo: Facilita la consulta nro2.
		//Aclaracion: juzgado puede ser null si se trata de un QBE.
		/*if (juzgado != null) {
			if(this.getJuzgado().getCausas().add(this)==true)
				System.out.println("Soy Juzgado:"+this.getJuzgado()+"agregue causa: "+ this.toString()+"tengo en total: "+this.getJuzgado().getCausas().size()+" causa/s!");
			
		}
		*/		
	}

	//Getters and Setters:
	public Juzgado getJuzgado() {
		return juzgado;
	}
	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}
	public Integer getNroExpediente() {
		return nroExpediente;
	}
	public void setNroExpediente(Integer nroExpediente) {
		this.nroExpediente = nroExpediente;
	}
	public ArrayList<Persona> getImputados() {
		return imputados;
	}
	public void setImputados(ArrayList<Persona> imputados) {
		this.imputados = imputados;
	}
	public ArrayList<Persona> getTestigos() {
		return testigos;
	}
	public void setTestigos(ArrayList<Persona> testigos) {
		this.testigos = testigos;
	}
	public String getSentencia() {
		return sentencia;
	}
	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	@Override
	public String toString() {
		return  "Causa { expediente: "+this.getNroExpediente()+" juzgado: ["+this.getJuzgado()+"] sentencia: "+this.getSentencia() + " }";
	}
	
	public void agregarASuJuzgado() {
		if (this.getJuzgado().getFuero() == TipoFuero.civil)
			this.getJuzgado().getCausas().add(this);
	}

}
