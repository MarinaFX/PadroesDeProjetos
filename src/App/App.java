package App;

import clientes.Cliente;
import clientes.Genero;
import clientes.Socio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	private static List<Cliente> clientesNoBar = new ArrayList<>();
	private static List<Cliente> clientesTotal = new ArrayList<>();
	private static SistemaBar sist = new SistemaBar();

	public static void main(String[] args) {

		menuOpcoes();

	}

	public static void menuOpcoes() {
		Scanner sc = new Scanner(System.in);

		int opc = 0;

		while(true) {

			System.out.println("1) Registrar entrada de Cliente");
			System.out.println("2) Registrar saída de Cliente");
			System.out.println("3) Pessoas no Bar");
			System.out.println("4) Procurar Cliente no Bar");
			System.out.println("5) Distribuição por Gênero");
			System.out.println("6) Distribuição de Sócios");
			System.out.println("7) Encerrar Dia");

			opc = sc.nextInt();
			sc.nextLine();

			if(opc==1) {
				registraEntradaCliente();
			} else if(opc==2) {
				registraSaidaCliente();
			} else if(opc==3) {
				printLista(sist.getClientes());
			} else if(opc==4) {
				procurarPessoaNoBar();
			} else if(opc==5) {
				distribuicaoGenero();
			} else if(opc==6) {
				distribuicaoSocio();
			} else if(opc==7) {
				printLista(clientesTotal);
			} else {
				System.out.println("Opção inválida.");
			}
		}


	}

	public static void registraEntradaCliente() {
		Scanner sc = new Scanner(System.in);

		String nome, cpf, generoAux, isSocio, socio;
		int idade;
		Genero genero;
		Cliente cliente;

		System.out.println("~~~Cadastro de Entrada de Cliente~~~");

		System.out.print("Informe o nome: ");
		nome = sc.nextLine();

		System.out.print("CPF: ");
		cpf = sc.nextLine();

		System.out.print("Idade: ");
		idade = sc.nextInt();
		sc.nextLine();

		System.out.print("Gênero: ");
		generoAux = sc.nextLine();
		genero = trataGenero(generoAux);

		System.out.println("Você é sócio do bar?");
		isSocio = sc.nextLine();

		if(isSocio.equalsIgnoreCase("sim")) {

			System.out.println("Número de socio: ");
			socio = sc.nextLine();

			cliente = new Socio(nome,cpf,idade,genero,socio);

		} else {

			cliente = new Cliente(nome,cpf,idade,genero);

		}

		clientesNoBar.add(cliente);
		clientesTotal.add(cliente);

		System.out.println("Cadastro finalizado.");

	}

	public static void registraSaidaCliente() {
		Scanner sc = new Scanner(System.in);
		String cpf;

		System.out.println("~~~Cadastro de Saída de Cliente~~~");

		System.out.println("Informe o CPF: ");
		cpf = sc.nextLine();

		Cliente cliente = sist.procuraCliente(cpf);

		clientesNoBar.remove(cliente);

		System.out.println("Saída registrada.");

	}

	public static void printLista(List<Cliente> lst) {
		lst.forEach(System.out::println);
	}


	public static void procurarPessoaNoBar() {
		Scanner sc = new Scanner(System.in);
		String cpf;
		Cliente cliente = null;

		System.out.println("~~~Procurar Cliente~~~");

		System.out.println("Informe o CPF do Cliente: ");
		cpf = sc.nextLine();

		try {
			cliente = sist.procuraCliente(cpf);
			System.out.println("O cliente " + cliente.getName() + " está no bar.");
		}catch(NullPointerException e) {
			System.out.println("O cliente não está no Bar.");
		}
	}

	public static void distribuicaoGenero() {
		double homens = sist.getDistribuicaoMasculina();
		double mulheres = sist.getDistribuicaoFeminina();

		System.out.println("~~~ Distribuição por Gênero ~~~");
		System.out.println(mulheres + "% mulheres.");
		System.out.println(homens + "% homens.");
	}

	public static void distribuicaoSocio() {
		double socios = sist.getDistribuicaoStatusSocio();
		double naoSocios = sist.getDistribuicaoNaoSocios();

		System.out.println("~~~ Distribuição por Sócios ~~~");
		System.out.println(socios + "% sócios.");
		System.out.println(naoSocios + "% não sócios.");

	}

	public static Genero trataGenero(String genero) {

		if(genero.equalsIgnoreCase("feminino")) {
			return Genero.FEMININO;
		} else if(genero.equalsIgnoreCase("masculino")) {
			return Genero.MASCULINO;
		} else {
			return null;
		}

	}

}