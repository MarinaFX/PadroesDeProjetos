package App;

import clientes.Cliente;
import clientes.Genero;
import clientes.Socio;
import com.sun.javaws.exceptions.InvalidArgumentException;

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

            while (true) {

                System.out.println("\n1) Registrar entrada de Cliente");
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
                    System.out.println(sist.getClientesNoBar().size() + " pessoas no Bar.\n");
                    printLista(sist.getClientesNoBar());
                } else if (opc == 4) {
                    procurarPessoaNoBar();
                } else if (opc == 5) {
                    distribuicaoGenero();
                } else if (opc == 6) {
                    distribuicaoSocio();
                } else if (opc == 7) {
                    System.out.println("\nClientes que estiveram no bar hoje: ");
                    printLista(sist.getTotalClientes());
                    sist.guardaLista();
                    break;
                } else {
                    System.out.println("Opção inválida.\n");
                    break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Opção inválida.");
        }
    }


//OPÇÕES DO MENU

    public static void registraEntradaCliente() {
        Scanner sc = new Scanner(System.in);

        String nome, cpf, generoAux, isSocio, numSocio;
        int idade;
        Genero genero;

        System.out.println("\n~~~Cadastro de Entrada de Cliente~~~");

        System.out.print("Informe o nome: ");
        nome = sc.nextLine();

        if (isNomeValido(nome)) {
            nome = trataNome(nome);

            System.out.print("CPF: ");
            cpf = sc.nextLine();

            if (isCPFValido(cpf)) {

                System.out.print("Idade: ");
                idade = sc.nextInt();
                sc.nextLine();

                if (isIdadeValida(idade)) {
                    System.out.print("Gênero: ");
                    generoAux = sc.nextLine();

                    isGeneroValido(generoAux);

                    if (isGeneroValido(generoAux)) {
                        genero = trataGenero(generoAux);

                        System.out.print("É sócio do bar? ");
                        isSocio = sc.nextLine();

                        if (isSocio.equalsIgnoreCase("sim")) {

                            System.out.print("Número de socio: ");
                            numSocio = sc.nextLine();

                            Cliente socio = new Socio(nome, cpf, idade, genero, numSocio);
                            sist.cadastraCliente(socio);

                            System.out.println("~~~Cadastro finalizado~~~");

                        } else if (isSocio.equalsIgnoreCase("não") || isSocio.equalsIgnoreCase("nao")) {

                            Cliente cliente = new Cliente(nome, cpf, idade, genero);
                            sist.cadastraCliente(cliente);

                            System.out.println("~~~Cadastro finalizado~~~");

                        } else {
                            System.out.println("Comando inválido");
                        }

                    }
                }
            }
        }
    }

    public static void registraSaidaCliente() {
        Scanner sc = new Scanner(System.in);
        String cpf;

        System.out.println("\n~~~Cadastro de Saída de Cliente~~~");

        System.out.println("Informe o CPF: ");
        cpf = sc.nextLine();


        Cliente cliente = sist.procuraCliente(cpf);

        sist.cadastraSaida(cliente);

        System.out.println("~~~Saída do cliente " + cliente.getName() + " registrada~~~\n");
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
        } catch (NullPointerException e) {
            System.out.println("O cliente não está no Bar.");
        }

        System.out.println("~~~~~~\n");
    }

    public static void distribuicaoGenero() {
        double homens = sist.getDistribuicaoMasculina();
        double mulheres = sist.getDistribuicaoFeminina();

        System.out.println("\n~~~ Distribuição por Gênero ~~~");
        System.out.printf("%%%.2f mulheres\n", mulheres);
        System.out.printf("%%%.2f homens\n", homens);
        System.out.println("~~~~~~\n");
    }

    public static void distribuicaoSocio() {
        double socios = sist.getDistribuicaoStatusSocio();
        double naoSocios = sist.getDistribuicaoNaoSocios();

        System.out.println("\n~~~ Distribuição por Sócios ~~~");
        System.out.printf("%%%.2f socios\n", socios);
        System.out.printf("%%%.2f não socios\n", naoSocios);
        System.out.println("~~~~~~\n");

    }


//VALIDAÇÃO DE ENTRADAS

    public static boolean isCPFValido(String cpf) throws NullPointerException {
        boolean is = true;

        try {
            if (cpf.length()<11 || cpf.contains("[a-zA-Z]+")) {
                is = false;
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("CPF inválido. O campo deve conter apenas 11 números.");
        }

        return is;
    }

    public static boolean isIdadeValida(int idade) throws NullPointerException {
        boolean is = true;

        try {
            if (idade <= 0) {
                is = false;
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Idade inválida.");
        } catch (InputMismatchException e) {
            System.out.println("A idade deve conter apenas números.");
        }

        return is;
    }

    public static boolean isNomeValido(String nome) throws InputMismatchException {
        boolean is = true;
        char[] chars = nome.toCharArray();

        try {
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    is = false;
                    throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("O nome deve conter apenas letras.");
        }

        return is;
    }

    public static boolean isGeneroValido(String genero) throws InputMismatchException {
        boolean is = true;

        try {

            if (!(genero.equalsIgnoreCase("feminino") || genero.equalsIgnoreCase("f") || genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("m"))) {
                is = false;
                throw new InputMismatchException();
            }

        } catch (InputMismatchException e) {
            System.out.println("O gênero deve ser informado como feminino, masculino, f ou m.");
        }

        return is;
    }


//TRANSFORMA ENTRADAS

    public static Genero trataGenero(String genero) {

        if(genero.equalsIgnoreCase("feminino") || genero.equalsIgnoreCase("f")) {
            return Genero.FEMININO;
        } else if(genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("m")) {
            return Genero.MASCULINO;
        } else {
            return null;
        }

    }

    public static String trataNome(String nome) {
        return Character.toUpperCase(nome.charAt(0)) + nome.substring(1);
    }

}
