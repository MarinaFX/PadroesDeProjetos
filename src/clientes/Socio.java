package clientes;

public class Socio extends Cliente {
	
	private String vip;

	public Socio(String name, String cpf, int age, String gender, String vip) {
		super(name, cpf, age, gender);
		this.vip = vip;
	}
	
	public String getVip() {
		return vip;
	}
}
