package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Administrador;
import model.Veiculo;
import model.Vendedor;
import repository.AdministradorRepository;
import util.Normaliza;

public class AdminService {
	
	Scanner sc;
	VeiculoService veiculoService;
	AdministradorRepository adminRepository = new AdministradorRepository();
	VendedorService vendedorService;
	
	public AdminService(Scanner sc, VeiculoService veiculoService, VendedorService vendedorService) {
		this.sc = sc;
		this.adminRepository.salvar(new Administrador("admin", "admin@aluguel.com", "Poa", "1111"));
		this.veiculoService = veiculoService;
		this.vendedorService = vendedorService;
	}
	
	public void confereEntrada(int entrada) throws SistemaException {
		sc.nextLine();
		
		if(entrada == 1) {
			veiculoService.cadastrarVeiculo();
		} else if (entrada == 2) {
			this.removerVeiculo();
		} else if(entrada == 3) {
			vendedorService.cadastrarVendedor();
		} else if (entrada == 4) {
			this.removerVendedor();
		}
	}
	
	public void removerVeiculo() throws SistemaException {
		System.out.println("Todos os veículos cadastrados e livres no sistema: ");
		veiculoService.buscarVeiculosLivres();
		int opcaoVeiculo = sc.nextInt();
		Veiculo veiculo = veiculoService.repository.buscarPorId(opcaoVeiculo);
		if(veiculo == null) {
			throw new SistemaException("Veículo não encontrado!");
		}
		veiculoService.repository.removerPorId(opcaoVeiculo);
		
		System.out.println("Veículo removido com sucesso!");
	}
	
	public void removerVendedor() throws SistemaException {
		System.out.println("Todos os vendedores cadastrados no sistema: ");
		vendedorService.retornaTodosVendedores();
		int opcaoVendedor = sc.nextInt();
		Vendedor vendedor = vendedorService.repository.buscarPorId(opcaoVendedor);
		if(vendedor == null) {
			throw new SistemaException("Vendedor não encontrado!");
		}
		vendedorService.repository.removerPorId(opcaoVendedor);
		
		System.out.println("Vendedor removido com sucesso!");
	}

	public Administrador confereEmail(String email) {
		List<Administrador> adminCadastrados = adminRepository.buscarTodos();
		
		for(Administrador admin : adminCadastrados) {
			if(admin.getEmail().equals(Normaliza.normalizaEmail(email))) {
				return admin;
			}
		}
		return null;
	}

	public boolean conferirSenha(Administrador adminParam, String senha) {
		Administrador admin = adminRepository.buscarPorId(adminParam.getId());
		return admin.getSenha().equals(senha);
	}
	
}
