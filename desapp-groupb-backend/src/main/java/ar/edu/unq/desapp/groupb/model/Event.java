package ar.edu.unq.desapp.groupb.model;

import java.util.GregorianCalendar;

public class Event extends Entity {
	private GregorianCalendar date;
	private Diagnostic diagnostic;
	
	public Event(){}
	
	public Event(GregorianCalendar fecha, Diagnostic diagnostico) {
		this.date = fecha;
		this.diagnostic = diagnostico;
	}
	
	public GregorianCalendar getFecha() {
		return date;
	}
	public void setDate(GregorianCalendar fecha) {
		this.date = fecha;
	}
	public Diagnostic getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(Diagnostic diagnostico) {
		this.diagnostic = diagnostico;
	}
	

}
