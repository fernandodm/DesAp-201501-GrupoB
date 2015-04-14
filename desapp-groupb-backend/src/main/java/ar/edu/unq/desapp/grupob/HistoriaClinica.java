package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class HistoriaClinica {
	
	private int peso;
	private int altura;
	private List<String> alergias = new ArrayList<String>();
	private HashMap<GregorianCalendar,Diagnostico> eventos = new HashMap<GregorianCalendar,Diagnostico>();
	private Persona persona;
		
	public HistoriaClinica(int peso, int altura, Persona persona){
		this.peso = peso;
		this.altura = altura;
		this.setPersona(persona);
	}	
	
	public HashMap<GregorianCalendar, Diagnostico> getEventos() {
		return eventos;
	}

	public void setEventos(HashMap<GregorianCalendar, Diagnostico> eventos) {
		this.eventos = eventos;
	}

	public void agregarAlergia(String alergia){
		this.alergias.add(alergia);		
	}
	

	//agregar Diagnostico
	
	/* ACLARACION: se podria poner como precondicion que la primer letra sea 
	 * mayuscula y el resto minuscula o pasar todo a mayuscula y comparar
	 * ahora voy hacer la primera opcion 
	 * */
	public boolean esAlergicoA(String alergia){
		return this.getAlergias().contains(alergia);
	}
	
	public void eliminarAlergia(String alergia){
		this.getAlergias().remove(alergia);
	}
	
	public boolean elPacienteEsCompatibleCon(Tratamiento t) {
		List<Medicamento> medicamentos = t.getMedicamentos();
		for(Medicamento m: medicamentos){
			if(this.alergias.contains(m.getDroga())){
				return true;
			}
		}
		return false;
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public List<String> getAlergias() {
		return alergias;
	}
	public void setAlergias(List<String> alergias) {
		this.alergias = alergias;
	}
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public void agregarEvento(GregorianCalendar fecha, Diagnostico diagnostico){
		this.getEventos().put(fecha, diagnostico);
	}
	
	public HashMap<GregorianCalendar,Diagnostico> eventosDesdeFecha(Calendar fecha){
		
		HashMap<GregorianCalendar,Diagnostico> events = new HashMap<GregorianCalendar,Diagnostico>();
		
		for(GregorianCalendar each : this.getEventos().keySet()){
			if(each.after(fecha)){
				events.put(each, this.getEventos().get(each));
			}
		}
		return events;
	}
	

	
}
