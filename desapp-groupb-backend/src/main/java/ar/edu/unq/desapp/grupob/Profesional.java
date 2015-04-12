package ar.edu.unq.desapp.grupob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unq.desapp.grupob.excepciones.DiagnosticoNoEncontradoException;
import ar.edu.unq.desapp.grupob.excepciones.PacienteNoEncontradoException;

public class Profesional extends Persona {

	public Profesional(String nombre, String apellido, String dni,
			String usuario, String contrasena){
		super(nombre,apellido,dni,usuario, contrasena);
		
	}
	
	
	//TESTEAR
	public HistoriaClinica obtenerHistoriaClinicaDe(Persona persona, Sistema sistema) throws PacienteNoEncontradoException{
		
		for(HistoriaClinica h : sistema.getHistorias()){
			if(h.getPersona().equals(persona)){
				return h;
			}
		}
				
		throw new PacienteNoEncontradoException();
	}
	//TESTEAR
	public List<Tratamiento> tratamientosParaElDiagnostico(Diagnostico diagnostico, Sistema sistema) throws DiagnosticoNoEncontradoException{
		
		Set<Tratamiento> tratamientos = new HashSet<Tratamiento>();
		
		if(sistema.getDiagnosticos().contains(diagnostico)){
			for(HistoriaClinica h: sistema.getHistorias()){
				for(Diagnostico d: h.getEventos().values()){
					if(d.equals(diagnostico)){
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
	//TESTEAR
	public List<Tratamiento> sugerirTratamientosParaElPaciente(Paciente paciente,Diagnostico diagnostico, Sistema sistema) throws DiagnosticoNoEncontradoException, PacienteNoEncontradoException{
		List<Tratamiento> tratamientos = this.tratamientosParaElDiagnostico(diagnostico, sistema);
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
	public void confirmarDiagnosticoParaPaciente(Paciente paciente, Diagnostico diagnostico, Calendar fecha, Sistema sistema){
		
		if(sistema.existePaciente(paciente)){
			
		}else{
			
		}
		
	}
	
	
}
