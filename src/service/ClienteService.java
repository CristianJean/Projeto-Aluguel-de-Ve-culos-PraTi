package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Cliente;
import model.Veiculo;
import repository.ClienteRepository;
import util.Normaliza;

public class ClienteService {
	
	Scanner sc;
	ClienteRepository clienteRepository = new ClienteRepository();
	
	public ClienteService(Scanner sc) {
		this.sc = sc;
		this.clienteRepository.salvar(new Cliente("Crístian", "cristian@gmail.com", "Canoas", "1234"));
		
	}
	
	public void alugarVeiculo(Cliente cliente, Veiculo veiculo) {
		cliente.getVeiculos().add(veiculo);
		this.clienteRepository.salvar(cliente);
	}
	
	public void buscarVeiculosAlugados(Cliente cliente) {
		List<Veiculo> veiculosAlugados = cliente.getVeiculos();
		
		for(Veiculo veiculo : veiculosAlugados){
			System.out.println(veiculo);
		}
	}
	
	public void removerVeiculo(Cliente clienteParam, Veiculo veiculoParam) throws SistemaException {
		Cliente cliente = this.clienteRepository.buscarPorId(clienteParam.getId());
		if(cliente == null) {
			throw new SistemaException("Cliente não encontrado!");
		}
		cliente.getVeiculos().remove(veiculoParam);
		this.clienteRepository.salvar(cliente);
	}
	
	public Cliente confereEmail(String email) {
		
		List<Cliente> clientesCadastrados = clienteRepository.buscarTodos();
		
		for(Cliente cliente : clientesCadastrados) {
			if(cliente.getEmail().equals(Normaliza.normalizaEmail(email))) {
				return cliente;
			}
		}
		
		return this.cadastrarCliente(email);
	}
	
	private Cliente cadastrarCliente(String email) {
		
		System.out.println("Digite seu nome:");
		String nome = sc.nextLine();
		System.out.println("Digite sua cidade:");
		String cidade = sc.nextLine();
		System.out.println("Digite sua senha:");
		String senha = sc.nextLine();
		
		Cliente cliente = new Cliente(nome, email, cidade, senha);
		
		this.clienteRepository.salvar(cliente);
		
		return cliente;
	}
	
	public boolean conferirSenha(Cliente clienteParam, String senha) {
		Cliente cliente = clienteRepository.buscarPorId(clienteParam.getId());
		
		return cliente.getSenha().equals(senha);
	}

}
