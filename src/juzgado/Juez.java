package juzgado;

public class Juez {

	private String nombre;
	private Integer matriculaProfesional;
	private String trayectoria;
	
	public Juez(String nombre, Integer matriculaProfesional, String trayectoria) {
		// Crea un nuevo juez.
		this.nombre = nombre;
		this.matriculaProfesional = matriculaProfesional;
		this.trayectoria = trayectoria;
	}
}
