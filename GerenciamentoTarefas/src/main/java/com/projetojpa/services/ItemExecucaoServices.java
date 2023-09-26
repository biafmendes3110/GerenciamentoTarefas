package com.projetojpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.ItemExecucao;
import com.projetojpa.repository.ItemExecucaoRepository;

@Service
public class ItemExecucaoServices {
	private final ItemExecucaoRepository itemExecucaoRepository;

	@Autowired
	public ItemExecucaoServices(ItemExecucaoRepository itemExecucaoRepository) {
		this.itemExecucaoRepository =itemExecucaoRepository;
	}
	public List<ItemExecucao> buscarTodasItemExecucao(){
		return itemExecucaoRepository.findAll();
	}
	//método para buscar produto por código 
	public ItemExecucao buscaItemExecucaoId(long id) {
		Optional<ItemExecucao> ItemExecucao = itemExecucaoRepository.findById(id); // classe usada para consulta de banco
		return ItemExecucao.orElse(null);
	} 
	//método para salvar os produtos
	public ItemExecucao salvaItemExecucao(ItemExecucao ItemExecucao) {
		return itemExecucaoRepository.save(ItemExecucao);
	}
	public ItemExecucao  alterarItemExecucao (long id, ItemExecucao alterarItemExecucao) {
		Optional<ItemExecucao > existeItemExecucao = itemExecucaoRepository.findById(id);
		if (existeItemExecucao.isPresent()) {
			alterarItemExecucao .setId(id);
			return itemExecucaoRepository.save(alterarItemExecucao );
		}
		return null;
	}
	public boolean apagarItemExecucao(long id) {
		Optional<ItemExecucao> existeItemExecucao =itemExecucaoRepository.findById(id);
		if (existeItemExecucao.isPresent()) {
			itemExecucaoRepository.deleteById(id);
			return true;
		}
		return false;
	}
}


