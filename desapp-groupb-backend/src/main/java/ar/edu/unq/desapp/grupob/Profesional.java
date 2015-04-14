package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.desapp.grupob.excepciones.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.grupob.excepciones.NombreDeUsuarioYaTomado;
import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;

public class Profesional extends Persona {

	public Profesional(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		
	}

	public void registrarseEnElSistema(Sistema sistema, String nombre, String apellido, String dni,
			String usuario, String contrasena) throws NombreDeUsuarioYaTomado{
		sistema.darDeAltaNuevoUsuarioProfesional(nombre, apellido, dni, usuario, contrasena);
	}
	
	public void darDeAltaNuevoUsuarioPacienteEnElSistema(String nombre, String apellido, String dni,
			String usuario, String contrasena, int peso, int altura, Sistema sistema) throws NombreDeUsuarioYaTomado{
		
		sistema.darDeAltaNuevoUsuarioPaciente(nombre, apellido, dni, usuario, contrasena, peso, altura);
	}
	
	
	public HistoriaClinica obtenerHistoriaClinicaDe(Persona persona, Sistema sistema) throws PacienteNoEncontradoException{
		
		for(HistoriaClinica h : sistema.getHistorias()){
			if(h.getPersona().equals(persona)){
				return h;
			}
	}
				
		throw new PacienteNoEncontradoException();
	}
	
	public List<Tratamiento> tratamientosParaElDiagnostico(String nombre, Sistema sistema) throws DiagnosticoNoEncontradoException{
		
		Set<Tratamiento> tratamientos = new HashSet<Tratamiento>();
		
		if(this.tieneDiagnostico(nombre, sistema)){
			for(HistoriaClinica h: sistema.getHistorias()){
				for(Diagnostico d: h.getEventos().values()){
					if(d.getNombre().toUpperCase().equals(nombre.toUpperCase())){
						tratamientos.add(d.getTratamiento());
					}
				}
			}
			ArrayList<Tratamiento> tratamientoList = new ArrayList<Tratamiento>(tratamientos);
			return tratamientoList;
		} else {
			throw new DiagnosticoNoEncontradoException();
		}
	}
	
	public boolean tieneDiagnostico(String nombre, Sistema sistema){
		for(Diagnostico d: sistema.getDiagnosticos()){
			if(d.getNombre().toUpperCase().equals(nombre.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}
	
	//TESTEAR
	public List<Tratamiento> sugerirTratamientosParaElPaciente(Paciente paciente,String nombreDiagnostico, Sistema sistema) throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
		List<Tratamiento> tratamientos = this.tratamientosParaElDiagnostico(nombreDiagnostico, sistema);
		HistoriaClinica historia = this.obtenerHistoriaClinicaDe(paciente, sistema);
		List<Tratamiento> tratamientosReturn = new ArrayList<Tratamiento>();
		for(Tratamiento t: tratamientos){
			if(historia.elPacienteEsCompatibleCon(t)){
				tratamientosReturn.add(t);
			}
		}
		return tratamientosReturn;
	}
	
	
	//TESTEAR
	public List<Diagnostico> diagnosticosPosiblesParaSintomatologia(List<Sintoma> sintomatologia, Sistema sistema){
		
		ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		
		for(Diagnostico each : sistema.getDiagnosticos()){
			if(each.getSintomas().containsAll(sintomatologia)){
				diagnosticos.add(each);
			}
		}
		
		return diagnosticos;
		
	}
	//TESTEAR
	public List<Diagnostico> quienesTuvieronUnSintomaTambienTuvieron(String sintoma, Sistema sistema){
		
		ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		
		for(Diagnostico each : sistema.getDiagnosticos()){
			if(each.getSintomas().contains(sintoma)){
				diagnosticos.add(each);
			}
		}
		
		return diagnosticos;
	}
	//TESTEAR
	public List<Diagnostico> quienesTuvieronUnaEnfermedadTambienTuvieron(Diagnostico diagnostico, Sistema sistema){
		
		Set<Diagnostico> diagnosticos = new HashSet<Diagnostico>();
		
		for(HistoriaClinica each : sistema.getHistorias()){
			if(each.getEventos().values().contains(diagnostico)){
				for(Diagnostico d : each.getEventos().values()){
					diagnosticos.add(d);
				}
			}
		}
		
		ArrayList<Diagnostico> diagnosticosPosta = new ArrayList<Diagnostico>(diagnosticos);
		
		return diagnosticosPosta;
		
	}
	//TESTEAR
	public void agregarSintomaADiagnostico(Sintoma sintoma, Diagnostico diagnostico, Sistema sistema){
		
		for(Diagnostico each :  sistema.getDiagnosticos()){
			if(diagnostico.equals(each)){
				each.getSintomas().add(sintoma);
			}
		}
		
	}
	//TESTEAR
	public void confirmarDiagnosticoParaPaciente(Paciente paciente, Diagnostico diagnostico, GregorianCalendar fecha, Sistema sistema) throws PacienteNoEncontradoException{
		
		if(sistema.existePaciente(paciente)){
			this.obtenerHistoriaClinicaDe(paciente, sistema).agregarEvento(fecha, diagnostico);
	
		}else{
			throw new PacienteNoEncontradoException();
		}
	}
	
	
	
	 public static void main (String [ ] args) {

		

//       GregorianCalendar c = new GregorianCalendar(2014,9,22);
//       GregorianCalendar f = new GregorianCalendar(2014,c.get(2),14);
//
		 
		 Calendar c = Calendar.getInstance();
		 int i = Integer.parseInt(Integer.toString(c.get(Calendar.YEAR)));
		 
		 
		 
        System.out.println(i);
//        List<String> ls = new ArrayList<String>();
//        ls.add("a");
//        ls.add("a");
//        System.out.println(Collections.frequency(ls, "a"));
        
	 }
}
