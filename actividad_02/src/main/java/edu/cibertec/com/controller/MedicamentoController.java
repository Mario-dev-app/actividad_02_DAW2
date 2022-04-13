package edu.cibertec.com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.cibertec.com.entity.Medicamento;
import edu.cibertec.com.service.MedicamentoService;

@RestController
@RequestMapping("/rest/medicamentos")
public class MedicamentoController {
	
	@Autowired
	MedicamentoService medicamentoService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listarMedicamentos(){
		return ResponseEntity.ok(medicamentoService.listarMedicamentos());
	}
	
	@GetMapping("/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listarMedicamentoPorNombre(@PathVariable String nombre){
		List<Medicamento> lstMedicamentos = medicamentoService.listarMedicamentoPorNombre(nombre);
		return ResponseEntity.ok(lstMedicamentos);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertarMedicamento(@RequestBody Medicamento obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Medicamento> lstMedicamentos = medicamentoService.listarMedicamentoPorNombre(obj.getNombre());
			if(CollectionUtils.isEmpty(lstMedicamentos)) {
				Medicamento medicamentoNuevo =  medicamentoService.insertarMedicamento(obj);
				if(medicamentoNuevo == null) {
					salida.put("mensaje", "Error al registrar medicamento");
				}else {
					salida.put("mensaje", "Medicamento registrado correctamente");
				}
			}else {
				salida.put("mensaje", "Medicamento ya existe: " + obj.getNombre());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "Error al registrar medicamento " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> modificarMedicamento(@RequestBody Medicamento obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Optional<Medicamento> optional = medicamentoService.listarMedicamentoPorId(obj.getIdMedicamento());
			if(optional.isPresent()) {
				List<Medicamento> lstMedicamentos = medicamentoService.listarMedicamentosDiferentesAlActualizado(obj.getNombre(), obj.getIdMedicamento());
				if(CollectionUtils.isEmpty(lstMedicamentos)) {
					Medicamento medicamentoActualizado =  medicamentoService.insertarMedicamento(obj);
					if(medicamentoActualizado == null) {
						salida.put("mensaje", "Error al registrar medicamento");
					}else {
						salida.put("mensaje", "Medicamento se actualizó correctamente");
					}
				}else {
					salida.put("mensaje", "Medicamento ya existe con ese nombre: " + obj.getNombre());
				}
				
			}else {
				salida.put("mensaje", "No existe medicamento con ID: " + obj.getIdMedicamento());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "Error al registrar medicamento " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminarMedicamento(@PathVariable int id){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			Optional<Medicamento> optional = medicamentoService.listarMedicamentoPorId(id);
			if(optional.isPresent()) {
				medicamentoService.eliminarMedicamento(id);
				salida.put("mensaje", "Medicamento eliminado correctamente");
			}else {
				salida.put("mensaje", "No existe medicamento con ID: " + id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "Error al registrar medicamento " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
}
