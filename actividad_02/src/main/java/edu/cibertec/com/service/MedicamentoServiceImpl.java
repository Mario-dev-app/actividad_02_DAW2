package edu.cibertec.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cibertec.com.entity.Medicamento;
import edu.cibertec.com.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{
	
	@Autowired
	MedicamentoRepository repository;

	@Override
	public List<Medicamento> listarMedicamentos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Medicamento insertarMedicamento(Medicamento obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Medicamento> listarMedicamentoPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return repository.findByNombre(nombre);
	}

	@Override
	public Optional<Medicamento> listarMedicamentoPorId(int idMedicamento) {
		// TODO Auto-generated method stub
		return repository.findById(idMedicamento);
	}

	@Override
	public List<Medicamento> listarMedicamentosDiferentesAlActualizado(String nombre, int idMedicamento) {
		// TODO Auto-generated method stub
		return repository.listarMedicamentosDiferentesAlActualizado(nombre, idMedicamento);
	}

	@Override
	public void eliminarMedicamento(int idMedicamento) {
		// TODO Auto-generated method stub
		repository.deleteById(idMedicamento);
	}

}
