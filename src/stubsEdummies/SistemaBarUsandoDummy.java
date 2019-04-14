package stubsEdummies;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clientes.Genero;

public class SistemaBarUsandoDummy {

    private static final String fName = "listabar.txt";
    private static final String currDir = Paths.get("").toAbsolutePath().toString();
    private static final String nameComplete = currDir + "\\" + fName;
    private static final Path path = Paths.get(nameComplete);

    private List<StubCliente> clientesTotal = new ArrayList<>();
    private List<StubCliente> clientesNoBar = new ArrayList<>();

    public void cadastraCliente(StubCliente c) {

        clientesTotal.add(c);
        clientesNoBar.add(c);
    }

    public void cadastraSaida(StubCliente c) {
        clientesNoBar.remove(c);
    }

    public int getNumeroPessoas() {
        return clientesNoBar.size();
    }

    public List<StubCliente> getTotalClientes() {
        return clientesTotal;
    }

    public List<StubCliente> getClientesNoBar() {
        return clientesNoBar;
    }

    public StubCliente procuraCliente(String newCPF) {
        StubCliente cliente = null;

        for (StubCliente a : clientesNoBar) {
            if (a.getCpf().equals(newCPF)) {
                cliente = a;
                break;
            }
        }

        return cliente;
    }

    public double getDistribuicaoMasculina() {

        List<StubCliente> listHomens = clientesNoBar.stream()
                .filter(StubCliente -> StubCliente.getGender() == Genero.MASCULINO)
                .collect(Collectors.toList());

        int distruibuicaoMasculina = listHomens.size();

        double percent = 0.00;
        percent = ((distruibuicaoMasculina * 100.0) / getNumeroPessoas());

        return percent;
    }

    public double getDistribuicaoFeminina() {
        return (100 - getDistribuicaoMasculina());
    }

    public double getDistribuicaoStatusSocio() {

        List<StubCliente> listSocios = clientesNoBar.stream()
                .filter(StubCliente -> StubCliente.getClass().equals(DummySocio.class))
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

            for (StubCliente c : clientesTotal) {

                String linha = //c.getName() + ";" +
                            c.getCpf() + ";" +
                            //c.getAge() + ";" +
                            c.getGender() + ";" +
                            c.getClass().getSimpleName();
                writer.println(linha);
            }

        } catch (IOException x) {
            System.err.format("Erro ao salvar", x);
        }
    }
}
