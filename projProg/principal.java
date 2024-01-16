package projProg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class principal {
	
	private static gereUtilizador gereUsers = new gereUtilizador();
	private static gereMedicamentos gereMedicamentos = new gereMedicamentos();
	private static gereServicos gereServicos = new gereServicos();
	
	public static void main(String[] args) {

		if (gereUsers.listaVazia()) {
			System.out.println("""
                    Nao existem utilizadores!
                    Por favor crie um gestor!
                    Insira os seguintes dados:\s""");
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
		do {
			opcao = dadosMenuOut("""
                    ###############
                    Gestao De Uma Farmacia
                    ###############
                    \t[1]Registar
                    \t[2]Login
                    \t[3]Exit
                    \tEscolha uma Opcao:\s""");
			switch (opcao) {
//			Registar
				case 1: {
					userAux = RegistoUser();
					break;
				}
//				Login
				case 2: {
					userAux = LoginUser();
					int tipo = 0;
					if(userAux != null){
						tipo = userAux.getTipo();
				}
					switch (tipo){
						//Gestor
						case 1:{
							do {
								opcao = MenuGestor();
								switch (opcao){
									// Gerir Utilizadores
									case 1:{
										do {
											opcao = Menu_GerirUtilizadores();
											switch (opcao){
												case 1:{
													do {
														opcao = Menu_PedidosUtilizador();
														switch (opcao){
															// Listar Pedidos
															case 1:{
																System.out.println("""
																		###############
																		Pedidos de Registo
																		###############
																		""");
																System.out.println(gereUsers.PedidosRegisto());
															break;
															}
															// Aceitar Pedidos
															case 2:{
																System.out.println(gereUsers.PedidosRegisto());
																String login = dadosStringsIn("Introduza o login do utilizador que pretende aprovar");
																gereUsers.AprovaPedido(login);
																System.out.println("O pedido de registo foi aceite");
															break;
															}
														}
													}while (opcao!=3);
													break;
												}
												case 2:{
													System.out.println("""
															###############
															Utilizadores existentes no sistema
															###############
															""");
													System.out.println(gereUsers.ListarUsers());
													break;
												}
												case 3:{
													System.out.println("""
															###############
															Listar utilizadores por tipo
															###############
															""");
													System.out.println("Gestores:");
													System.out.println(gereUsers.ListarUsersTipo(1));
													System.out.println("Farmaceuticos:");
													System.out.println(gereUsers.ListarUsersTipo(2));
													System.out.println("Clientes:");
													System.out.println(gereUsers.ListarUsersTipo(3));
													break;
												}
												case 4:{
													gereUsers.ordenaUsers();
													System.out.println("Os users foram ordenados por nome");
													break;
												}
												case 5:{
													System.out.println("""
															###############
															Pesquisar por login
															###############
															""");
													String login = dadosStringsIn("Introduza o login do user: ");
													System.out.println(gereUsers.pesquisaUserLogin(login));
													break;
												}
												case 6:{
													System.out.println("""
															###############
															Pesquisar por nome
															###############
															""");
													String nome = dadosStringsIn("Introduza o nome do user: ");
													System.out.println(gereUsers.pesquisaUserNome(nome));
													break;
												}

											}
										}while (opcao != 7);
										break;
									}
									// Gerir Medicamentos
									case 2:{
										do {
											opcao = Menu_GerirMedicamentos();
											switch (opcao){
												case 1:{
													System.out.println("""
															###############
															Lista de medicamentos
															###############
															""");
													System.out.println(gereMedicamentos.ListarMedicamentos());
													break;
												}
												case 2:{
													gereMedicamentos.ordenaMedicamentos();
													System.out.println("Os medicamentos foram ordenados pela designacao");
												}
												case 3:{
													System.out.println("""
															###############
															Pesquisa por designacao
															###############
															""");
													String designacao = dadosStringsIn("Introduza a designacao do medicamento: ");
													System.out.println(gereMedicamentos.pesquisaMedicamentosNome(designacao));
												}
												case 4:{
													System.out.println("""
															###############
															Pesquisa por categoria
															###############
															""");
													int codigo = dadosIntIn("Introduza a categoria do medicamento: ");
													//System.out.println(gereMedicamentos.pesquisaMedicamentosCat(codigo));
												}
												case 5:{
													System.out.println("""
															###############
															Pesquisa de medicamento por componente ativa
															###############
															""");
													String designacao = dadosStringsIn("Introduza a designacao da componente ativa");
													System.out.println(gereMedicamentos.pesquisaMedicamentosCompAAtiva(designacao));
												}
												case 6:{
													do {
														opcao = dadosMenuOut("""
																Mostrar medicamentos:\\s
																\\t[1]Genericos
																\\t[2]Nao genericos
																\\t[3]Exit
																Escolha uma opcao:\\s
																""");
														switch (opcao){
															case 1:{
																System.out.println("""
																	###############
																	Medicamentos genericos
																	###############
																	""");
																System.out.println(gereMedicamentos.pesquisaMedicamentosGen(true));
																break;
															}
															case 2:{
																System.out.println("""
																		###############
																		Medicamentos nao genericos
																		###############
																		""");
																System.out.println(gereMedicamentos.pesquisaMedicamentosGen(false));
																break;
															}
															default:{
																System.out.println("Opcao invalida !!!");
															}
														}
													}while (opcao != 3);
												}
												case 7:{

												}
											}
										}while (opcao != 8);
									}
									// Gerir Servicos
									case 3:{
										do {
											opcao = Menu_GerirServicos();
											switch (opcao){
												// Listar todos os servicos
												case 1:{
													System.out.println("""
															###############
															Servicos
															###############
															""");
													System.out.println(gereServicos.listarServicos());
													break;
												}
												// Aprovar e Encerrar servicos
												case 2:{
													do {
														opcao = dadosMenuOut("""
																Gerir Servicos\\s
																\\t[1]Aprovar um servico
																\\t[2]Encerrar um servico
																\\t[3]Exit
																Escolha uma opcao:\\s
																""");
														switch (opcao){
															case 1:{
																System.out.println("Servicos por aceitar:\n"+gereServicos.listarServicos());
																System.out.println(gereServicos.ListarServicosEstado(1));
																int codigo = dadosIntIn("Insira o codigo do servico que pretende aceitar: ");
																boolean resultado = gereServicos.AceitarServico(codigo);
																if(resultado){
																	System.out.println("O servico foi aceite com sucesso.");
																}
																else {
																	System.out.println("Nao existe nenhum servico com esse codigo por aceitar.");
																}
															}
															case 2:{
																System.out.println("Servicos concluidos:\n"+gereServicos.listarServicos());
																System.out.println(gereServicos.ListarServicosEstado(4));
																int codigo = dadosIntIn("Insira o codigo do servico que pretende concluir: ");
																boolean resultado = gereServicos.AceitarServico(codigo);
																if(resultado){
																	System.out.println("O servico foi concluido com sucesso.");
																}
																else {
																	System.out.println("Nao existe nenhum servico com esse codigo por concluir.");
																}
															}
														}

													}while (opcao != 3);
													break;
												}
												// Listar os servicos de um cliente
												case 3:{
													System.out.println("""
															###############
															Lista de clientes
															###############
															""");
													System.out.println(gereUsers.ListarUsersTipo(3));
													int nif = dadosIntIn("Introduza o NIF do cliente: ");
													gereServicos gereServicosAux = gereServicos.pesquisaServicosCliente(nif);
													System.out.println(gereServicosAux.listarServicos());
												}
												case 4:{
													System.out.println("""
															###############
															Servicos
															###############
															""");
													System.out.println("A decorrer:");
													gereServicos.ListarServicosEstado(3);
													System.out.println("Concluidos");
													gereServicos.ListarServicosEstado(5);
												}
												case 5:{
													int codigo = dadosIntIn("Introduza o codigo de servico:");
													System.out.println(gereServicos.pesquisaServicosCodigo(codigo));
													break;
												}
												case 6:{
													int tempo = dadosIntIn("Introduza o tempo despendido em horas:");
													gereServicos gereServicosAux = gereServicos.pesquisaServicosTempo(tempo);
													System.out.println(gereServicosAux.listarServicos());
												}
											}
										}while (opcao != 7);
									}
								}
							}while (opcao!=4);
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
						default:{
							System.out.println("Opcao invalida !!!");
						}
					}
				}
				default:{
					System.out.println("Opcao invalida !!!");
					break;
				}
			}
		}while (opcao != 3);

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
			tipo = dadosIntIn("""
                    Registar como:\s
                    \t[1]Gestor
                    \t[2]Farmaceutico
                    \t[3]Cliente
                    Escolha uma opcao:\s""");
			if(tipo < 1 || tipo > 3) {
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
				else {
					break;
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
			if(userAux.isEstado()) {
				System.out.println("Bem-vindo " + userAux.getNome());
				return userAux;
			}
			else {
				System.out.println("A sua conta ainda nao foi ativada");
				return null;
			}
		} else {
			System.out.println("Os dados estao incorretos!");
			return null;
		}
	}


	//-------------------Menus--------------------------
	private static int MenuGestor(){
        return dadosMenuOut("""
                ###############
                Menu do Gestor
                ###############
                \t[1]Gerir Utilizadores
                \t[2]Gerir Medicamentos
                \t[3]Gerir Servicos
                \t[4]Exit
                \tEscolha uma Opcao:\s""");
	}
	private static int Menu_GerirUtilizadores(){
        return dadosMenuOut("""
                ###############
                Gerir Utilizadores
                ###############
                \t[1]Pedidos de registo
                \t[2]Listar todos os utilizadores
                \t[3]Listar por tipo
                \t[4]Ordenar por nome
                \t[5]Pesquisar por login
                \t[6]Pesquisar por nome
                \t[7]Exit
                \tEscolha uma Opcao:\s""");
	}
	private static int Menu_PedidosUtilizador(){
        return dadosMenuOut("""
                ###############
                Pedidos de registo
                ###############
                \t[1]Listar pedidos
                \t[2]Aprovar um pedido
                \t[3]Exit
                \tEscolha uma Opcao:\s""");
	}
	private static int Menu_GerirMedicamentos(){
        return dadosMenuOut("""
                ###############
                Gerir Medicamentos
                ###############
                \t[1]Listar todos os medicamentos
                \t[2]Ordenar por designacao
                \t[3]Pesquisar por designacao
                \t[4]Pesquisar por categoria
                \t[5]Pesquisar por componente ativa
                \t[6]Pesquisar por generico
                \t[7]Pesquisar por quantia do stock
                \t[8]Exit
                \tEscolha uma Opcao:\s""");
	}
	private static int Menu_GerirServicos(){
        return dadosMenuOut("""
                ###############
                Gerir Servicos
                ###############
                \t[1]Listar todos os servicos
                \t[2]Aprovar/Encerrar um servico
                \t[3]Listar todos os servicos de um cliente
                \t[4]Listar todos os servicos por estado
                \t[5]Pesquisar servico por codigo
                \t[6]Pesquisar servico por tempo despendido
                \t[7]Exit
                \tEscolha uma Opcao:\s""");
	}
	private static int MenuFarmaceutico(){
        return dadosMenuOut("""
                ###############
                Menu Farmaceutico
                ###############
                \t[1]Gerir os seus Servicos
                \t[2]Gerir os medicamentos
                \t[3]Gerir as categorias
                \t[4]Gerir os exipientes
                \t[5]Exit
                \tEscolha uma Opcao:\s""");
	}
	private static int MenuCliente(){
        return dadosMenuOut("""
                ###############
                Menu Cliente
                ###############
                \t[1]Pedir um servico
                \t[2]Consultar os seus servicos
                \t[3]Exit
                \tEscolha uma Opcao:\s""");
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