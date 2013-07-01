package problema_solucion;

import java.util.ArrayList;

import juzgado.Juzgado;

public class Causa {

	/* Se marcha xq no funka la consulta....
	private Juzgado juzgado;
	*/
	private Integer nroExpediente;
	private ArrayList<Persona> imputados;
	private ArrayList<Persona> testigos;
	private String sentencia;
	
	//Constructor:
	public Causa(Integer nroExpediente, ArrayList<Persona> imputados, ArrayList<Persona> testigos, String sentencia) {
		this.nroExpediente = nroExpediente;
		this.imputados = imputados;
		this.testigos = testigos;
		this.sentencia = sentencia;
	}

	//Getters and Setters:
	/*
	 	public Juzgado getJuzgado() {
		return juzgado;
	}
	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}
	*/
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
		return  "Causa { expediente: "+this.getNroExpediente()+ " sentencia: "+this.getSentencia() + " }";
	}
	
	/*
	@Override
	public String toString() {
		return  "Causa { expediente: "+this.getNroExpediente()+" juzgado: ["+this.getJuzgado()+"] sentencia: "+this.getSentencia() + " }";
	}
	
	public void agregarASuJuzgado() {
		//if (this.getJuzgado().getFuero() == TipoFuero.civil)
		this.getJuzgado().getCausas().add(this);
	}
	 * */
}
