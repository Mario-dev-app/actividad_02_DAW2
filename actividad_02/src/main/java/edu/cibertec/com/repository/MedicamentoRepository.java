package edu.cibertec.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.cibertec.com.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{

	@Query("select m from Medicamento m where m.nombre like %:nombre%")
	public List<Medicamento> findByNombre(String nombre);
	
	@Query("select m from Medicamento m where m.nombre like %:nombre and m.idMedicamento <> :idmedicamento")
	public List<Medicamento> listarMedicamentosDiferentesAlActualizado(String nombre, int idmedicamento);
}
