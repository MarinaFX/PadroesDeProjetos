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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		menuOpcoes(sc);
		registraEntradaCliente(sc,clientesNoBar);

	}

	public static void menuOpcoes(Scanner sc) {
		SistemaBar sist = new SistemaBar();

		int opc=0;

		while(true) {

			System.out.println("1) Registrar entrada de Cliente");
			System.out.println("2) Registrar saída de Cliente");
			System.out.println("3) Pessoas no Bar");
			System.out.println("4) Procurar Cliente no Bar");
			System.out.println("5) Distribuição por Gênero");
			System.out.println("6) Distribuição de Sócios");
			System.out.println("7) Encerrar Dia");

			if(opc==1) {
				registraEntradaCliente(sc,clientesNoBar);
			} else if(opc==2) {
				registraSaidaCliente();
			} else if(opc==3) {
				pessoasNoBar();
			} else if(opc==4) {
				procurarPessoaNoBar();
			} else if(opc==5) {
				distribuicaoGenero();
			} else if(opc==6) {
				distribuicaoSocio();
			} else if(opc==7) {

			} else {
				System.out.println("Opção inválida.");
			}
		}


	}

	public static void registraEntradaCliente(Scanner sc, List<Cliente> c) {
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

	}

	public static void pessoasNoBar() {

	}

	public static void procurarPessoaNoBar() {

	}

	public static void distribuicaoGenero() {

	}

	public static void distribuicaoSocio() {

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