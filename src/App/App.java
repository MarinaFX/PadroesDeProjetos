package App;

import clientes.Cliente;
import clientes.Genero;
import clientes.Socio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

	private static SistemaBar sist = new SistemaBar();

	public static void main(String[] args) {
		menuOpcoes();
	}

	public static void menuOpcoes() {
		Scanner sc = new Scanner(System.in);

		int opc;

		System.out.println("Seja bem-vindo!");

		try {

			while(true) {

				System.out.println("1) Registrar entrada de Cliente");
				System.out.println("2) Registrar saída de Cliente");
				System.out.println("3) Lista de pessoas no Bar");
				System.out.println("4) Procurar Cliente no Bar");
				System.out.println("5) Distribuição por Gênero");
				System.out.println("6) Distribuição de Sócios");
				System.out.println("7) Encerrar o Dia");

				opc = sc.nextInt();
				sc.nextLine();

				if (opc == 1) {
					registraEntradaCliente();
				} else if (opc == 2) {
					registraSaidaCliente();
				} else if (opc == 3) {
					printLista(sist.getClientesNoBar());
				} else if (opc == 4) {
					procurarPessoaNoBar();
				} else if (opc == 5) {
					distribuicaoGenero();
				} else if (opc == 6) {
					distribuicaoSocio();
				} else if (opc == 7) {
					printLista(sist.getTotalClientes());
					sist.guardaLista();
					break;
				} else {
					System.out.println("Opção inválida.\n");
					break;
				}
			}

		} catch(InputMismatchException e) {
			System.out.println("Opção inválida.");
		}
	}

	public static void registraEntradaCliente() {
		Scanner sc = new Scanner(System.in);

		String nome, cpf, generoAux, socio, numSocio;
		int idade;
		Genero genero;
		Cliente cliente;

		System.out.println("\n~~~Cadastro de Entrada de Cliente~~~");

		System.out.print("Informe o nome: ");
		nome = sc.nextLine();

		System.out.print("CPF: ");
		cpf = sc.nextLine();

		System.out.print("Idade: ");
		idade = sc.nextInt();
		sc.nextLine();

		System.out.print("Gênero: ");
		generoAux = sc.nextLine();
		genero = sist.trataGenero(generoAux);

		System.out.println("Você é sócio do bar?");
		socio = sc.nextLine();

		if(socio.equalsIgnoreCase("sim")) {

			System.out.println("Número de socio: ");
			numSocio = sc.nextLine();

			cliente = new Socio(nome,cpf,idade,genero,numSocio);

		} else {

			cliente = new Cliente(nome,cpf,idade,genero);
		}

		sist.cadastraCliente(cliente);

		System.out.println("~~~Cadastro finalizado~~~\n");
	}

	public static void registraSaidaCliente() {
		Scanner sc = new Scanner(System.in);
		String cpf;

		System.out.println("\n~~~Cadastro de Saída de Cliente~~~");

		System.out.println("Informe o CPF: ");
		cpf = sc.nextLine();

		Cliente cliente = sist.procuraCliente(cpf);

		sist.cadastraSaida(cliente);

		System.out.println("~~~Saída registrada~~~\n");

	}

	public static void printLista(List<Cliente> lst) {
		lst.forEach(System.out::println);
	}


	public static void procurarPessoaNoBar() {
		Scanner sc = new Scanner(System.in);
		String cpf;
		Cliente cliente;

		System.out.println("\n~~~Procurar Cliente~~~");

		System.out.println("Informe o CPF do Cliente: ");
		cpf = sc.nextLine();

		try {
			cliente = sist.procuraCliente(cpf);
			System.out.println("O cliente " + cliente.getName() + " está no bar.");
		}catch(NullPointerException e) {
			System.out.println("O cliente não está no Bar.");
		}

		System.out.println("~~~~~~\n");
	}

	public static void distribuicaoGenero() {
		double homens = sist.getDistribuicaoMasculina();
		double mulheres = sist.getDistribuicaoFeminina();

		System.out.println("\n~~~ Distribuição por Gênero ~~~");
		System.out.println(mulheres + "% mulheres.");
		System.out.println(homens + "% homens.");
		System.out.println("~~~~~~\n");
	}

	public static void distribuicaoSocio() {
		double socios = sist.getDistribuicaoStatusSocio();
		double naoSocios = sist.getDistribuicaoNaoSocios();

		System.out.println("\n~~~ Distribuição por Sócios ~~~");
		System.out.println(socios + "% sócios.");
		System.out.println(naoSocios + "% não sócios.");
		System.out.println("~~~~~~\n");

	}


}