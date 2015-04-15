package ar.edu.unq.desapp.grupob;

import java.util.GregorianCalendar;

public class Evento {
	private GregorianCalendar fecha;
	private Diagnostico diagnostico;
	
	public Evento(GregorianCalendar fecha, Diagnostico diagnostico) {
		this.fecha = fecha;
		this.diagnostico = diagnostico;
	}
	
	public GregorianCalendar getFecha() {
		return fecha;
	}
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	public Diagnostico getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}
	

}
