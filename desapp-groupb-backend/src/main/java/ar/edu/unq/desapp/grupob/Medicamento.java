package ar.edu.unq.desapp.grupob;

public class Medicamento {
	
	private String droga;
	private int concentracion;
	private int tiempo;
	
	public Medicamento(String droga, int concentracion, int tiempo){
		
		this.droga = droga;
		this.concentracion = concentracion;
		this.tiempo = tiempo;
		
	}
	
	////////////////////////
	//GETTERS AND SETTERS//
	////////////////////////
	
	public String getDroga() {
		return droga;
	}
	public void setDroga(String droga) {
		this.droga = droga;
	}
	public int getConcentracion() {
		return concentracion;
	}
	public void setConcentracion(int concentracion) {
		this.concentracion = concentracion;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	///////////////////////////////////
}
