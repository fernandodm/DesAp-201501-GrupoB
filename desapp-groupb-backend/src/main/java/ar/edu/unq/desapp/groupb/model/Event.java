package ar.edu.unq.desapp.groupb.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;


public class Event extends Entity {
	private DateTime date;
	private Diagnostic diagnostic;
	
	public Event(){}
	
	public Event(Diagnostic diagnostico) {
		this.diagnostic = diagnostico;
	}
	
	public DateTime stringToCalendar(String date){
		List<String> list = Arrays.asList(StringUtils.split(date, "/"));
		DateTime dateTime = new DateTime(Integer.parseInt(list.get(2)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(0)), 0, 0);
    	return dateTime;
	}
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime fecha) {
		this.date = fecha;
	}
	public Diagnostic getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(Diagnostic diagnostico) {
		this.diagnostic = diagnostico;
	}
	////////////////////////////////

}
