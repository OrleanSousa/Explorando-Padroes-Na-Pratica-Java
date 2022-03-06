package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.service.ViaCepService;
import one.digitalinnovation.got.model.Cliente;
import one.digitalinnovation.got.model.ClienteRepository;
import one.digitalinnovation.got.model.Endereco;
import one.digitalinnovation.got.model.EnderecoRepository;

@Service
public class ClienteServiveImpl {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	private Endereco endereco;
	
	public Iterable<Cliente> buscarTodos(){
		return clienteRepository.findAll();
	}
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}
	
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
		return null;});
		
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}
	
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if(clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
		
	}
	
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

}
