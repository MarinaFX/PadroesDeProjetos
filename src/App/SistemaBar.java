package App;

import clientes.Cliente;
import clientes.Genero;
import clientes.Socio;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaBar {

    private static final String fName = "listabar.txt";
    private static final String currDir = Paths.get("").toAbsolutePath().toString();
    private static final String nameComplete = currDir + "\\" + fName;
    private static final Path path = Paths.get(nameComplete);

    private List<Cliente> clientesTotal = new ArrayList<>();
    private List<Cliente> clientesNoBar = new ArrayList<>();

    public void cadastraCliente(Cliente c) {

        clientesTotal.add(c);
        clientesNoBar.add(c);
    }

    public void cadastraSaida(Cliente c) {
        clientesNoBar.remove(c);
    }

    public int getNumeroPessoas() {
        return clientesNoBar.size();
    }

    public List<Cliente> getTotalClientes() {
        return clientesTotal;
    }

    public List<Cliente> getClientesNoBar() {
        return clientesNoBar;
    }

    public Cliente procuraCliente(String newCPF) {
        Cliente cliente = null;

        for (Cliente a : clientesNoBar) {
            if (a.getCpf().equals(newCPF)) {
                cliente = a;
                break;
            }
        }

        return cliente;
    }

    public double getDistribuicaoMasculina() {
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        List<Cliente> listHomens = clientesNoBar.stream()
                .filter(Cliente -> Cliente.getGender() == Genero.MASCULINO)
                .collect(Collectors.toList());

        int distruibuicaoMasculina = listHomens.size();

        double percent = ((distruibuicaoMasculina * 100.0) / getNumeroPessoas());

        return percent;
    }

    public double getDistribuicaoFeminina() {
        return (100 - getDistribuicaoMasculina());
    }

    public double getDistribuicaoStatusSocio() {

        List<Cliente> listSocios = clientesNoBar.stream()
                .filter(Cliente -> Cliente.getClass().equals(Socio.class))
                .collect(Collectors.toList());

        int distribuicaoSocio = listSocios.size();

        double percent = ((distribuicaoSocio * 100.0) / getNumeroPessoas());

        return percent;

    }

    public double getDistribuicaoNaoSocios(){
        return (100 - getDistribuicaoStatusSocio());
    }


    public void guardaLista() {

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))) {

            for (Cliente c : clientesTotal) {

                String linha = c.getName() + ";" +
                            c.getCpf() + ";" +
                            c.getAge() + ";" +
                            c.getGender() + ";" +
                            c.getClass().getSimpleName();
                writer.println(linha);
            }

        } catch (IOException x) {
            System.err.format("Erro ao salvar", x);
        }
    }
}

