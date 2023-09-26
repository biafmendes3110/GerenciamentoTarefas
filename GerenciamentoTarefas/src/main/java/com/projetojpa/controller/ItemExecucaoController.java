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

import com.projetojpa.entities.ItemExecucao;
import com.projetojpa.services.ItemExecucaoServices;

@RestController
@RequestMapping("/itemexecucao")
public class ItemExecucaoController {
	private ItemExecucaoServices itemExecucaoServices;

	@Autowired
	public ItemExecucaoController (ItemExecucaoServices itemExecucaoServices) {
		this.itemExecucaoServices = itemExecucaoServices;
	}
	@GetMapping("/{id}")
	public ResponseEntity <ItemExecucao> buscaItemExecucaoIdControlId(@PathVariable Long id){
		ItemExecucao itemExecucao = itemExecucaoServices.buscaItemExecucaoId(id);
		if(itemExecucao != null) {
			return ResponseEntity.ok(itemExecucao);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	public ResponseEntity<List<ItemExecucao>> buscaTodasItemExecucaoControl() {
		List<ItemExecucao> itemExecucao = itemExecucaoServices.buscarTodasItemExecucao();

		return ResponseEntity.ok(itemExecucao);
	}
	@PostMapping("/")
	public ResponseEntity<ItemExecucao> salvaItemExecucaoControl(@RequestBody ItemExecucao itemExecucao){
		ItemExecucao salvaItemExecucao = itemExecucaoServices.salvaItemExecucao(itemExecucao);

		return ResponseEntity.status(HttpStatus.CREATED).body(salvaItemExecucao);

	}
	@PutMapping ("/{id}")
	public ResponseEntity<ItemExecucao> updateItemExecucao(@PathVariable Long id, @RequestBody ItemExecucao itemExecucao) {
		ItemExecucao  updateItemExecucao = itemExecucaoServices.alterarItemExecucao(id,itemExecucao);
		if (updateItemExecucao  != null) {
			return ResponseEntity.ok(updateItemExecucao);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagaitemExecucaoControl(@PathVariable Long id) {
		boolean apagar = itemExecucaoServices.apagarItemExecucao(id);
		if(apagar) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}


