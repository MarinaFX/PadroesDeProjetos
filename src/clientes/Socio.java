package clientes;

public class Socio extends Cliente {
	
	private String vip;

	public Socio(String name, String cpf, int age, Genero gender, String vip) {
		super(name, cpf, age, gender);
		this.vip = vip;
	}

}
