package juzgado;

import java.util.List;

public class Causa {
	
	private Juzgado juzgado;
	private Integer nroExpediente;
	private List<Persona> imputados;
	private List<Persona> testigos;
	private String sentencia;
	
	public Causa(Juzgado juzgado, Integer nroExpediente, List<Persona> imputados, List<Persona> testigos, String sentencia) {
		// TODO Auto-generated constructor stub
		this.juzgado = juzgado;
		this.nroExpediente = nroExpediente;
		this.imputados = imputados;
		this.testigos = testigos;
		this.sentencia = sentencia;
	}

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
	public List<Persona> getImputados() {
		return imputados;
	}
	public void setImputados(List<Persona> imputados) {
		this.imputados = imputados;
	}
	public List<Persona> getTestigos() {
		return testigos;
	}
	public void setTestigos(List<Persona> testigos) {
		this.testigos = testigos;
	}
	public String getSentencia() {
		return sentencia;
	}
	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}


}
