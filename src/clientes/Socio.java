package clientes;

public class Socio extends Cliente {
	
	private String vip;

	public Socio(String name, String cpf, int age, Genero gender, String vip) {
		super(name, cpf, age, gender);
		this.vip = vip;
	}
	
	public String getVip() {
		return vip;
	}

	public String toString() {
		return super.toString() + ", " + vip;
	}
}
