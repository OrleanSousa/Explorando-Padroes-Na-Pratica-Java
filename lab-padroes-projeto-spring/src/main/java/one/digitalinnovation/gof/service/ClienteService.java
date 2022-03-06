package one.digitalinnovation.gof.service;

import one.digitalinnovation.got.model.Cliente;

public interface ClienteService {
		
	public Iterable<Cliente> buscarTodos();
	
	public Cliente buscarPorId(Long id);
	
	void inserir(Cliente cliente);
	
	void atualizar(Long id, Cliente cliente);
	
	void deletar(Long id);
} // eu so quero upar na main
