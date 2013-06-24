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

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getMatriculaProfesional() {
		return matriculaProfesional;
	}
	public void setMatriculaProfesional(Integer matriculaProfesional) {
		this.matriculaProfesional = matriculaProfesional;
	}
	public String getTrayectoria() {
		return trayectoria;
	}
	public void setTrayectoria(String trayectoria) {
		this.trayectoria = trayectoria;
	}
	
}
