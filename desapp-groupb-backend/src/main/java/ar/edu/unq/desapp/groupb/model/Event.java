package ar.edu.unq.desapp.groupb.model;


public class Event extends Entity {
	private String date;
	private Diagnostic diagnostic;
	
	public Event(){}
	
	public Event(String fecha, Diagnostic diagnostico) {
		this.date = fecha;
		this.diagnostic = diagnostico;
	}
	
	public String getFecha() {
		return date;
	}
	public void setDate(String fecha) {
		this.date = fecha;
	}
	public Diagnostic getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(Diagnostic diagnostico) {
		this.diagnostic = diagnostico;
	}
	

}
