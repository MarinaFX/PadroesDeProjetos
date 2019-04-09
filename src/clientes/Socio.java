package clientes;

public class Socio extends Cliente {
	
	private double vip;

	public Socio(String name, String cpf, int age, String gender, double vip) {
		super(name, cpf, age, gender);
		this.vip = vip;
	}

}
