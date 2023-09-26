package com.projetojpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Tarefa;
import com.projetojpa.repository.TarefaRepository;

@Service
public class TarefaServices {

	private TarefaRepository tarefaRepository;

	@Autowired
	public TarefaServices(TarefaRepository tarefaRepository) {
		this.tarefaRepository = tarefaRepository;
	}
	public List<Tarefa> buscarTodasTarefa(){
		return tarefaRepository.findAll();
	}
	//método para buscar produto por código 
	public Tarefa buscaTarefaId(long id) {
		Optional<Tarefa> Tarefa = tarefaRepository.findById(id); // classe usada para consulta de banco
		return Tarefa.orElse(null);
	} 
	//método para salvar os produtos
	public Tarefa salvaTarefa(Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}
	public Tarefa alterarTarefa(long id, Tarefa alterarTarefa) {
		Optional<Tarefa> existeTarefa = tarefaRepository.findById(id);
		if (existeTarefa.isPresent()) {
			alterarTarefa.setId(id);
			return tarefaRepository.save(alterarTarefa);
		}
		return null;
	}
	public boolean apagarTarefa(long id) {
		Optional<Tarefa> existeTarefa = tarefaRepository.findById(id);
		if (existeTarefa.isPresent()) {
			 tarefaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}


