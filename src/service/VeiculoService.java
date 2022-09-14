package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import repository.VeiculoRepository;

public class VeiculoService {
	
	Scanner sc;
	VeiculoRepository repository = new VeiculoRepository();

	public VeiculoService(Scanner sc) {
		this.sc = sc;
		repository.salvar(new Veiculo("I30", "Hyndai", "Preto", "IXI8096", "carro", 130));
		repository.salvar(new Veiculo("Sandero", "Renault", "Vermelho", "ZXX5652", "carro", 159));
		repository.salvar(new Veiculo("CG 150", "Honda", "Azul", "ZZZ8096", "moto", 89));
	}
	
	public void cadastrarVeiculo() {
		System.out.println("Digite o modelo do veículo:");
		String modelo = sc.nextLine();
		
		System.out.println("Digite a marca do veículo:");
		String marca = sc.nextLine();
		
		System.out.println("Digite a cor do veículo:");
		String cor = sc.nextLine();
		
		System.out.println("Digite a placa do veículo:");
		String placa = sc.nextLine();
		
		System.out.println("Digite o segmento do veículo:");
		String tipo = sc.nextLine();
		
		System.out.println("Digite o valor da locação do veículo:");
		double valor = sc.nextDouble();
		
		Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, tipo, valor);
		
		this.repository.salvar(veiculo);
		
		System.out.println("Você cadastrou um veículo");
	}
	
	public void buscarVeiculosLivres(){
		List<Veiculo> todosVeiculos = this.repository.buscarTodos();
		
		for(Veiculo veiculo : todosVeiculos) {
			if(veiculo.getStatus() == Status.LIVRE) {
				System.out.println(veiculo);
			}
		}
	}
	
	public Veiculo alugarVeiculoId(int id) throws SistemaException {
		Veiculo veiculo = this.repository.buscarPorId(id);
		if(veiculo == null) {
			throw new SistemaException("Veículo não encontrado!");
		}
		veiculo.setStatus(Status.ALUGADO);
		this.repository.salvar(veiculo);
		return veiculo;
	}
	
	public Veiculo devolverVeiculo(Cliente cliente, int id) throws SistemaException {
		Veiculo veiculo = this.repository.buscarPorId(id);
		if(veiculo == null) {
			throw new SistemaException("Veículo não encontrado");
		}else if(!cliente.getVeiculos().contains(veiculo)) {
				throw new SistemaException("Você não possui esse veículo");
			}
		
		veiculo.setStatus(Status.LIVRE);
		this.repository.salvar(veiculo);
		return veiculo;
		}
	
	public Veiculo buscarVeiculoId(int id) {
		return this.repository.buscarPorId(id);
	}
}


