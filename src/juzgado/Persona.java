package juzgado;

import java.util.Date;

public class Persona {

	private String nombre;
	private String apellido;
	private Integer dni;
	private Date fechaNacimiento;
	private String sexo;
	
	//Constructor:
	public Persona(String nombre, String apellido, Integer dni, Date fechaNacimiento, String sexo) {
	// Crea una nueva persona
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
	}

	//Getters and Setters:
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
