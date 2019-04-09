package clientes;

public class Cliente {
	private String name;
	private String cpf;
	private int age;
	private String gender;
	
	public Cliente (String name,  String cpf, int age, String gender) {
		this.name = name;
		this.cpf = cpf;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}
	
	
}
