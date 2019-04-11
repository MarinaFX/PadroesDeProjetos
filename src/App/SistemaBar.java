package App;

import clientes.Cliente;
import clientes.Genero;
import clientes.Socio;

import java.util.List;
import java.util.stream.Collectors;

public class SistemaBar {
    private List<Cliente> clientes;

    public int getNumeroPessoas() {
        return clientes.size();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente procuraCliente(String newCPF) {
        Cliente cliente = null;

        for (Cliente a : clientes) {
            if (a.getCpf().equals(newCPF)) {
                cliente = a;
                break;
            }
        }

        return cliente;
    }

    public double getDistribuicaoMasculina() {
        List<Cliente> listHomens = clientes.stream()
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
        double percent = 0;

        List<Cliente> listSocios = clientes.stream()
                .filter(Cliente -> Cliente.getClass().equals(Socio.class))
                .collect(Collectors.toList());

        int distribuicaoSocio = listSocios.size();

        percent = ((distribuicaoSocio * 100.0) / getNumeroPessoas());

        return percent;

    }

    public double getDistribuicaoNaoSocios(){
        return (100 - getDistribuicaoStatusSocio());
    }



}
