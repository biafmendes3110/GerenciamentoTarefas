package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entities.Tarefa;
import com.projetojpa.services.TarefaServices;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	private final TarefaServices tarefaServices;

	@Autowired
	public TarefaController (TarefaServices tarefaServices) {
		this.tarefaServices = tarefaServices;
	}
	@GetMapping("/{id}")
	public ResponseEntity <Tarefa> buscaTarefaIdControlId(@PathVariable Long id){
		Tarefa tarefa = tarefaServices.buscaTarefaId(id);
		if(tarefa != null) {
			return ResponseEntity.ok(tarefa);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	public ResponseEntity<List<Tarefa>> buscaTodasTarefaControl() {
		List<Tarefa> Tarefa = tarefaServices.buscarTodasTarefa();

		return ResponseEntity.ok(Tarefa);
	}
	@PostMapping("/")
	public ResponseEntity<Tarefa> salvaDrogariaControl(@RequestBody Tarefa tarefa){
		Tarefa salvaTarefa = tarefaServices.salvaTarefa(tarefa);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaTarefa);

	}
	@PutMapping ("/{id}")
	public ResponseEntity<Tarefa> updateDrogaria(@PathVariable Long id, @RequestBody Tarefa tarefa) {
		Tarefa  updateTarefa = tarefaServices.alterarTarefa(id,tarefa);
		if (updateTarefa  != null) {
			return ResponseEntity.ok(updateTarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaTarefaControl(@PathVariable Long id) {
		boolean apagar = tarefaServices.apagarTarefa(id);
		if(apagar) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}

