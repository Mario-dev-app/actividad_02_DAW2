package edu.cibertec.com.service;

import java.util.List;
import java.util.Optional;

import edu.cibertec.com.entity.Medicamento;

public interface MedicamentoService {

	public abstract List<Medicamento> listarMedicamentos();
	
	public abstract Medicamento insertarMedicamento(Medicamento obj);
	
	public abstract List<Medicamento> listarMedicamentoPorNombre(String nombre);
	
	public abstract Optional<Medicamento> listarMedicamentoPorId(int idMedicamento);
	
	public abstract List<Medicamento> listarMedicamentosDiferentesAlActualizado(String nombre, int idMedicamento);
	
	public abstract void eliminarMedicamento(int idMedicamento);
}
