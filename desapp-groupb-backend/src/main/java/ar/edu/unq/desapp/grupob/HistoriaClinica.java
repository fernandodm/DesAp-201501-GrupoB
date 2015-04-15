package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class HistoriaClinica {
	
	private int peso;
	private int altura;
	private List<String> alergias = new ArrayList<String>();
	private List<Evento> eventos = new ArrayList<Evento>();
	private Persona persona;
		
	public HistoriaClinica(int peso, int altura, Persona persona){
		this.peso = peso;
		this.altura = altura;
		this.setPersona(persona);
	}	
	
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
	
	public List<Diagnostico> eventosDesdeFecha(GregorianCalendar fecha){
		
		List<Diagnostico> events = new ArrayList<Diagnostico>();
		
		for(Evento evento : this.getEventos()){
			if(evento.getFecha().after(fecha)){
				events.add(evento.getDiagnostico());
			}
		}
		return events;
	}
	
	public void agregarEvento(Evento evento){
		this.getEventos().add(evento);
	}
	
	public List<Diagnostico> obtenerDiagnosticos() {
		
		List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		for(Evento evento: this.getEventos()){
			diagnosticos.add(evento.getDiagnostico());
		}
	
		return diagnosticos;
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
		
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public void agregarAlergia(String alergia){
		this.alergias.add(alergia);		
	}		
}
