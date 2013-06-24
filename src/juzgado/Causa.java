package juzgado;

import java.util.ArrayList;

public class Causa {
	
	private Juzgado juzgado;
	private Integer nroExpediente;
	private ArrayList<Persona> imputados;
	private ArrayList<Persona> testigos;
	private String sentencia;
	
	//Constructor:
	public Causa(Juzgado juzgado, Integer nroExpediente, ArrayList<Persona> imputados, ArrayList<Persona> testigos, String sentencia) {
		// TODO Auto-generated constructor stub
		this.juzgado = juzgado;
		this.nroExpediente = nroExpediente;
		this.imputados = imputados;
		this.testigos = testigos;
		this.sentencia = sentencia;
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
		return  "expediente: "+this.getNroExpediente()+" juzgado: ["+this.getJuzgado()+"] sentencia: "+this.getSentencia();
	}

}
