package menu;

public class Menu {
	
	public static void menu1() {
		System.out.println("================================");
		System.out.println("Bem vindo ao sistema de aluguel de veículos");
		System.out.println("Digite a opção correspondente: ");
		System.out.println("1 - cliente");
		System.out.println("2 - vendedor");
		System.out.println("3 - administrador");
		System.out.println("0 - para sair do sistema");
	}
	
	public static void menuCliente1() {
		System.out.println("Digite seu e-mail: ");
	}
	
	public static void menuCliente2() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Alugar um carro");
		System.out.println("2 - Devolver um carro");
	}
	
	public static void menuVendedor1() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Ver os carros que você alugou");
		System.out.println("2 - Ver seu salário");
		System.out.println("3 - Ver seu salário com a comissão atual");
	}
	
	public static void menuAdministrador() {
		System.out.println("Digite a opção desejada");
		System.out.println("1 - Cadastrar um veículo");
		System.out.println("2 - Remover um veículo");
		System.out.println("3 - Cadastrar um vendedor");
		System.out.println("4 - Remover um vendedor");
	}
}
