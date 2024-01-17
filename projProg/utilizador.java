package projProg;

import java.io.Serializable;

public class utilizador implements Comparable<utilizador>, Serializable{

	private String login;
	private String password;
	private String nome;
	private boolean estado;
	private String email;
	private int tipo;
//	cliente e farmaceutico
	private int nif;
	private String morada;
	private int contacto;
	
	
	utilizador(String aLogin, String aPassword, 
				String aNome, boolean aEstado, 
				String aEmail, int aTipo){
		login = aLogin;
		password = aPassword;
		nome = aNome;
		estado = aEstado;
		email = aEmail;
		tipo = aTipo;
		nif = 0;
		morada = "Nao tem.";
		contacto = 0;
	}

	utilizador(String aLogin, String aPassword, 
				String aNome, boolean aEstado, 
				String aEmail, int aTipo, int aNif, 
				String aMorada, int aContacto){
		login = aLogin;
		password = aPassword;
		nome = aNome;
		estado = aEstado;
		email = aEmail;
		tipo = aTipo;
		nif = aNif;
		morada = aMorada;
		contacto = aContacto;
	}

	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public int getTipo() {
		return tipo;
	}
	public String getTipoString(){
		switch (tipo){
			case 1:
				return "Gestor";
			case 2:
				return "Farmaceutico";
			case 3:
				return "Cliente";
		}
		return "erro";
	}
	

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public int getNif() {
		return nif;
	}
	
	public int getContacto() {
		return contacto;
	}
	
	public int compareTo(utilizador aObj) {
		return nome.compareTo(aObj.getNome());
	}
	

	public String toString() {
		String tipoAux = "";
		String estadoAux;
		switch (tipo) {
			case 1:
				tipoAux = "Gestor";
				break;
			case 2:
				tipoAux = "Farmaceutico";
				break;
			case 3:
				tipoAux = "Cliente";
				break;
		}
		if (estado) {
			estadoAux = "Ativo";
		}else {
			estadoAux = "Inativo";
		} return "\n\tUser: "+login+"\n\tNome: "+nome+
				"\n\tEstado: "+estadoAux+"\n\tEmail: "
				+email+"\n\tTipo: "+tipoAux+"\n\tNif: "+nif+
				"\n\tMorada: "+morada+"\n\tContacto: "+contacto;
	}
}
