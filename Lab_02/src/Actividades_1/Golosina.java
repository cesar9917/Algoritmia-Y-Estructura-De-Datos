package Actividades_1;

//clase Golosina
public class Golosina {
	
	//atributos
	private String nombre;
	private double peso;
	
	//constructor
	public Golosina(String nombre, double peso) {
		this.nombre = nombre;
		this.peso = peso;
	}
	
	//getters
	public String getNombre() {
		return nombre;
	}
	
	public double getPeso() {
		return peso;
	}
	
	//setters
	public void setPeso(double peso) {
		this.peso = peso;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
