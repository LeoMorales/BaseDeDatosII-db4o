package juzgado;

import java.util.Date;

public class Persona {

	private String nombre;
	private String apellido;
	private Integer dni;
	private Date fechaNacimiento;
	private String sexo;
	
	public Persona(String nombre, String apellido, Integer dni, Date fechaNacimiento, String sexo) {
	// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
	}
}
