package ar.edu.unq.desapp.grupob;

import java.util.List;

public class Sistema {
	
	private List<Persona> usuarios;
	private List<Diagnostico> diagnosticos;
	private List<HistoriaClinica> historiasClinicas;
	
	////////////////////////
	// GETTERS AND SETTERS//
	////////////////////////
	
	public List<Persona> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Persona> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Diagnostico> getDiagnosticos() {
		return diagnosticos;
	}
	public void setDiagnosticos(List<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}
	public List<HistoriaClinica> getHistoriasClinicas() {
		return historiasClinicas;
	}
	public void setHistoriasClinicas(List<HistoriaClinica> historiasClinicas) {
		this.historiasClinicas = historiasClinicas;
	}
	/////////////////////////////////////
}
