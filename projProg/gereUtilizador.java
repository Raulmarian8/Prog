package projProg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

public class gereUtilizador {
	ArrayList <utilizador> userList;
	
	gereUtilizador(){
		userList = new ArrayList<utilizador>();
	}
	
	public boolean inserirUser(utilizador aUser) {
		if (userList != null) {
			return userList.add(aUser);
		}return false;
	}
	
	public boolean listaVazia() {
        return userList.isEmpty();
    }
	
	public utilizador login(String aLogin, String aPassword) {
		if(!listaVazia()) {
			for (int i = 0; i < userList.size(); i++) {
				if(aLogin.equals(userList.get(i).getLogin())) {
					if (aPassword.equals(userList.get(i).getPassword())) {
						return userList.get(i);
					}
				}else {
					return null;
				}
			}
		} return null;
	}
	
	public boolean userExistUti(String aLogin) {
		if(!listaVazia()) {
			for (int i = 0; i < userList.size(); i++) {
				if(aLogin.equals(userList.get(i).getLogin())) {
					return true;
				}
			}
		} return false;
	}
	
	public boolean emailExistUti(String aEmail) {
		if(!listaVazia()) {
			for (int i = 0; i < userList.size(); i++) {
				if(aEmail.equals(userList.get(i).getEmail())) {
					return true;
				}
			}
		} return false;
	}
	
	public boolean nifExistUti(int aNif) {
		if(!listaVazia()) {
			for (int i = 0; i < userList.size(); i++) {
				if(aNif == (userList.get(i).getNif())) {
					return true;
				}
			}
		} return false;
	}
	
	public boolean contactoExistUti(int aContacto) {
		if(!listaVazia()) {
			for (int i = 0; i < userList.size(); i++) {
				if(aContacto == (userList.get(i).getContacto())) {
					return true;
				}
			}
		} return false;
	}

	public String PedidosRegisto(){
		Enumeration<utilizador> e = Collections.enumeration(userList);
		String usersInfo = "-------------------------";
		while (e.hasMoreElements()) {
			if(!e.nextElement().isEstado()){
				usersInfo += e.nextElement() + "-------------------------" + "\n";
			}
		}
		return usersInfo;
	}
	public void AprovaPedido(String login){
		pesquisaUserLogin(login).setEstado(true);
	}
	
//	Ordenar user pelo nome
	public void ordenaUsers() {
		Collections.sort(userList);
	}
	
//	Listar todos os users
	public String ListarUsers() {
		Enumeration<utilizador> e = Collections.enumeration(userList);
		String usersInfo = "-------------------------";
		while (e.hasMoreElements()) {
			usersInfo += e.nextElement() + "-------------------------" + "\n";
		}
		return usersInfo;
	}
	
//	Listar os users por tipo
	public String ListarUsersTipo(int aTipo) {
		if(userList != null && !userList.isEmpty()) {
			Enumeration<utilizador> e = Collections.enumeration(userList);
			String userInfo = "-------------------------";
			utilizador userAux;
			while (e.hasMoreElements()) {
				userAux = e.nextElement();
				if(userAux.getTipo() == aTipo) {
					userInfo += userAux + "-------------------------" + "\n";
				}
			}
			return userInfo;
		}
		return null;
	}
	
//	pesquisa user por login
	public utilizador pesquisaUserLogin (String aLogin) {
		Enumeration<utilizador> e = Collections.enumeration(userList);
		utilizador userAux;
		while (e.hasMoreElements()) {
			userAux = e.nextElement();
			if(userAux.getLogin().equals(aLogin)) {
				return userAux;
			}
		}
		return null;
	}
	
//	pesquisa user por nome
	public utilizador pesquisaUserNome (String aNome) {
		Enumeration<utilizador> e = Collections.enumeration(userList);
		utilizador userAux;
		while (e.hasMoreElements()) {
			userAux = e.nextElement();
			if(userAux.getNome().equals(aNome)) {
				return userAux;
			}
		}
		return null;
	}
	
	
	
	

}
