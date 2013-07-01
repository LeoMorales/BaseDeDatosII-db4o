package juzgado;

import java.util.ArrayList;
import java.util.Iterator;

import juzgado.Juzgado.TipoFuero;

public class Juzgado {

	enum TipoFuero{civil, comercial, laboral, penal};
	
	private TipoFuero fuero;
	private Juez juez;
	private String domicilio;
	private String localidad;
	private ArrayList<Causa> causas;
	
	//Constructor:
	public Juzgado(TipoFuero fuero, Juez juez, String domicilio, String localidad) {
		// Crea un juzgado.
		this.fuero = fuero;
		this.juez = juez;
		this.domicilio = domicilio;
		this.localidad = localidad;
		//Conjunto de causas vacias al principio:
		this.setCausas(new ArrayList<Causa>());
	}

	
	public Juzgado(TipoFuero fuero, Juez juez, String domicilio, String localidad, ArrayList<Causa> causas) {
		// TODO Auto-generated constructor stub
		this(fuero, juez, domicilio, localidad);
		this.setCausas(causas);
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
	public ArrayList<Causa> getCausas() {
		return causas;
	}

	public void setCausas(ArrayList<Causa> causas) {
		this.causas = causas;
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
		return "Juzgado{ fuero: "+this.getFuero()+"\t| juez: "+this.getJuez() + " }";
	}

	public boolean poseeCausaConSentencia() {
		boolean encontreCausa = false;
		Iterator<Causa> iterador = this.getCausas().iterator();
		
		while ((iterador.hasNext()) && (! encontreCausa)) {
            if (iterador.next().getSentencia() != null)
            	encontreCausa = true; //xq la encontre.
		}
		return encontreCausa;
	}

	public boolean poseeCausaSinSentencia() {
		boolean encontreCausa = false;
		Iterator<Causa> iterador = this.getCausas().iterator();
		
		while ((iterador.hasNext()) && (! encontreCausa)) {
            if (iterador.next().getSentencia() == null)
            	encontreCausa = true; //xq la encontre.
		}
		return encontreCausa;
	}


	
}
