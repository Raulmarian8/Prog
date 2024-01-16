package projProg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class principal {
	
	private static gereUtilizador gereUsers = new gereUtilizador();
	
	public static void main(String[] args) {

		if (gereUsers.listaVazia()) {
			System.out.println("Nao existem utilizadores!"
					+ "\nPor favor crie um gestor!"
					+ "\nInsira os seguintes dados: ");
			String user = dadosStringsIn("User: ");
//			A password é gestor
			String password = dadosStringsIn("Password: ");
			String nome = dadosStringsIn("Nome: ");
			String email = dadosStringsIn("Email: ");
			utilizador userAux = new utilizador(user, password, nome, true, email, 1);
			gereUsers.inserirUser(userAux);
		}
		int opcao;
		utilizador userAux = null;
		String pass;
		do {
			opcao = dadosMenuOut("###############\n"
					+ "Gestao De Uma Farmacia\n"
					+ "###############\n\t[1]Registar\n\t"
					+ "[2]Login\n\t[3]Exit\n\tEscolha uma Opcao: ");
			switch (opcao) {
//			Registar
				case 1: {
					userAux = RegistoUser();
					break;
				}
//				Login
				case 2: {
					userAux = LoginUser();
					switch (userAux.getTipo()){
						//Gestor
						case 1:{
							do {
								opcao = MenuGestor();
								switch (opcao){
									case 1:{

									}
									case 2:{

									}
									case 3:{

									}
									case 4:{
										break;
									}
								}
							}while (true);
							break;
						}
						//Farmaceutico
						case 2:{
							MenuFarmaceutico();
							break;
						}
						//Cliente
						case 3:{
							MenuCliente();
							break;
						}
					}
					break;
					}
//				Exit
				case 3: {
					System.exit(0);
					break;
					}
				}

			}while (true);

		}
	

	//--------------------User--------------------------
	private static utilizador RegistoUser() {
		String user;
		String password;
		String nome;
		String email;
		int nif = 0;
		String morada = "Nao tem";
		int contacto = 0;
		int tipo;
		do {
			user = dadosStringsIn("User: ");
			if (gereUsers.userExistUti(user)) {
				System.out.println("Este user já existe!");
			}
			else {
				break;
			}
		}while (true);
		password = dadosStringsIn("Password: ");
		nome = dadosStringsIn("Nome: ");
		do {
			email = dadosStringsIn("Email: ");
			if (gereUsers.emailExistUti(email)) {
				System.out.println("Este email já existe!");
			}
			else {
				break;
			}
		}while (true);
		do {
			tipo = dadosIntIn("Registar como: \n\t[1]Gestor"
					+ "\n\t[2]Farmaceutico\n\t[3]Cliente\nEscolha uma opcao: ");
			if(tipo < 1 && tipo > 3) {
				System.out.println("Opcao Invalida!");
			}else {
				break;
			}
		}while(true);
		if (tipo == 2 || tipo == 3) {
			do{
				nif = dadosIntIn("Nif: ");
				if (gereUsers.nifExistUti(nif)) {
					System.out.println("Este NIF já existe!");
				}
				else {
					break;
				}
			}while (true);
			morada = dadosStringsIn("Morada: ");
			do {
				contacto = dadosIntIn("Contacto: ");
				if (gereUsers.contactoExistUti(contacto)) {
					System.out.println("Este contacto já existe!");
				}
			}while (true);
		}
		utilizador userAux = new utilizador(user, password, nome, false, email, tipo, nif, morada, contacto);
		gereUsers.inserirUser(userAux);
		return userAux;
	}

	private static utilizador LoginUser(){
		String user = dadosStringsIn("User: ");
		String password = dadosStringsIn("Password: ");
		utilizador userAux = gereUsers.login(user, password);
		if (userAux != null) {
			System.out.println("Bem-vindo " + userAux.getNome());
			return userAux;
		} else {
			System.out.println("Os dados estao incorretos!");
			return null;
		}
	}


	//-------------------Menus--------------------------
	private static int MenuGestor(){
		int opcao = dadosMenuOut("###############\n"
				+ "Menu do Gestor\n"
				+ "###############\n\t"
				+ "[1]Gerir Utilizadores\n\t"
				+ "[2]Gerir Medicamentos\n\t"
				+ "[3]Gerir Servicos\n\t"
				+ "[4]Exit\n\t"
				+ "Escolha uma Opcao: ");
		return opcao;
	}
	private static int Menu_GerirUtilizadores(){
		int opcao = dadosMenuOut("###############\n"
				+ "Gerir Utilizadores\n"
				+ "###############\n\t"
				+ "[1]Listar pedidos de registo\n\t"
				+ "[2]Listar todos os utilizadores\n\t"
				+ "[3]Listar por tipo\n\t"
				+ "[4]Ordenar por nome\n\t"
				+ "[5]Pesquisar por login\n\t"
				+ "[6]Pesquisar por nome\n\t"
				+ "[7]Exit\n\t"
				+ "Escolha uma Opcao: ");
		return opcao;
	}
	private static int Menu_GerirMedicamentos(){
		int opcao = dadosMenuOut("###############\n"
				+ "Gerir Medicamentos\n"
				+ "###############\n\t"
				+ "[1]Listar todos os medicamentos\n\t"
				+ "[2]Listar por tipo\n\t"
				+ "[3]Ordenar por designacao\n\t"
				+ "[4]Pesquisar por designacao\n\t"
				+ "[5]Pesquisar por categoria\n\t"
				+ "[6]Pesquisar por componente ativa\n\t"
				+ "[7]Pesquisar por generico\n\t"
				+ "[8]Pesquisar por quantia do stock\n\t"
				+ "[9]Exit\n\t"
				+ "Escolha uma Opcao: ");
		return opcao;
	}
	private static int Menu_GerirServicos(){
		int opcao = dadosMenuOut("###############\n"
				+ "Gerir Servicos\n"
				+ "###############\n\t"
				+ "[1]Listar todos os servicos\n\t"
				+ "[2]Aprovar/Encerrar um servico\n\t"
				+ "[3]Listar todos os servicos de um cliente\n\t"
				+ "[4]Listar todos os servicos por estado\n\t"
				+ "[5]Pesquisar servico por codigo\n\t"
				+ "[6]Pesquisar servico por tempo despendido\n\t"
				+ "[7]Exit\n\t"
				+ "Escolha uma Opcao: ");
		return opcao;
	}
	private static int MenuFarmaceutico(){
		int opcao = dadosMenuOut("###############\n"
				+ "Menu Farmaceutico\n"
				+ "###############\n\t"
				+ "[1]Gerir os seus Servicos\n\t"
				+ "[2]Gerir os medicamentos\n\t"
				+ "[3]Gerir as categorias\n\t"
				+ "[4]Gerir os exipientes\n\t"
				+ "[5]Exit\n\t"
				+ "Escolha uma Opcao: ");
		return opcao;
	}
	private static int MenuCliente(){
		int opcao = dadosMenuOut("###############\n"
				+ "Menu Cliente\n"
				+ "###############\n\t"
				+ "[1]Pedir um servico\n\t"
				+ "[2]Consultar os seus servicos\n\t"
				+ "[3]Exit\n\t"
				+ "Escolha uma Opcao: ");
		return opcao;
	}

	//------------------Ler dados-----------------------
	private static int dadosMenuOut(String aDados) {
		try {
			return(dadosIntIn(aDados));
		}catch(InputMismatchException erro) {
			System.out.println("Erro!");
		}
		return 0;
	}
	
	private static String dadosStringsIn(String aDados){
		Scanner teclado = new Scanner(System.in);
		System.out.print(aDados);
		return teclado.nextLine();
	}
	
	private static int dadosIntIn(String aDados) {
		Scanner teclado = new Scanner(System.in);
		System.out.print(aDados);
		return teclado.nextInt();
	}
	
}