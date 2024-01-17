package projProg;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class principal {
	
	private static gereUtilizador gereUsers = new gereUtilizador();
	private static gereMedicamentos gereMedicamentos = new gereMedicamentos();
	private static gereServicos gereServicos = new gereServicos();
	private static gereLog log = new gereLog();
	private static  gereInformacao info = new gereInformacao();
	private static gereFicheiroObj memoria ;
	
	public static void main(String[] args) {
		System.out.println(info.lerFicheiroInfo()+"\n");
		memoria = lerficheiro();
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
			log.adicionarFicheiroLog(nome,"Criou conta como gestor");
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
				case 0:{
					System.out.println("O utilizador nao existe !!!");
					break;
				}
//			Registar
				case 1: {
					userAux = RegistoUser();
					log.adicionarFicheiroLog(userAux.getNome(),"Registou-se na aplicação como "+userAux.getTipoString());
					break;
				}
//				Login
				case 2: {
					userAux = LoginUser();
					int tipo = 0;
					if(userAux != null){
						tipo = userAux.getTipo();
						log.adicionarFicheiroLog(userAux.getNome(),"Iniciou sessao na aplicacao como " + userAux.getTipoString());
						info.ficheiroInfo(userAux.getLogin());
					}
					switch (tipo){
						//Gestor
						case 1:{
							do {
								opcao = MenuGestor();
								switch (opcao){
									// Gerir Utilizadores
									case 1:{
										log.adicionarFicheiroLog(userAux.getNome(),"Selecionou a opcao gerir utilizadores");
										do {
											opcao = Menu_GerirUtilizadores();
											switch (opcao){
												case 1:{
													do {
														log.adicionarFicheiroLog(userAux.getNome(),"Selecionou a de gerir pedidos de registo");
														opcao = Menu_PedidosUtilizador();
														switch (opcao){
															// Listar Pedidos
															case 1:{
																log.adicionarFicheiroLog(userAux.getNome(),"Visualizou os pedidos de registo dos utilizadores");
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
																log.adicionarFicheiroLog(userAux.getNome(),"Selecionou a opcao de aceitar um pedido de um utilizador");
																System.out.println(gereUsers.PedidosRegisto());
																String login = dadosStringsIn("Introduza o login do utilizador que pretende aprovar");
																gereUsers.AprovaPedido(login);
																System.out.println("O pedido de registo foi aceite");
																log.adicionarFicheiroLog(userAux.getNome(),"Aceitou o pedido de registo do user "+login);
															break;
															}
														}
													}while (opcao!=3);
													log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de gestao de pedidos de registo");
													break;
												}
												case 2:{
													log.adicionarFicheiroLog(userAux.getNome(),"Exibiu todos os utilizadores existentes no sistema");
													System.out.println("""
															###############
															Utilizadores existentes no sistema
															###############
															""");
													System.out.println(gereUsers.ListarUsers());
													break;
												}
												case 3:{
													log.adicionarFicheiroLog(userAux.getNome(),"Listou os utilizadores por tipo");
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
													log.adicionarFicheiroLog(userAux.getNome(),"Ordenou todos os users por ordem alfabetica do nome");
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
													log.adicionarFicheiroLog(userAux.getNome(),"Pesquisou por um utilizador com o login: " + login);
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
													log.adicionarFicheiroLog(userAux.getNome(),"Pesquisou por um utilizador com o nome: " + nome );
													break;
												}

											}
										}while (opcao != 7);
										log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de gestao de utilizadores");
										break;
									}
									// Gerir Medicamentos
									case 2:{
										MenuGereMedicamentos(userAux);
										break;
									}
									// Gerir Servicos
									case 3:{
										log.adicionarFicheiroLog(userAux.getNome(),"Entrou no menu de gestao de servicos");
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
													log.adicionarFicheiroLog(userAux.getNome(),"Listou todos os servicos existentes no sistema");
													break;
												}
												// Aprovar e Encerrar servicos
												case 2:{
													log.adicionarFicheiroLog(userAux.getNome(),"Entrou no menu de aprovacao e encerramento de servicos");
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
																log.adicionarFicheiroLog(userAux.getNome(),"Selecionou a opcao de aceitar um servico");
																System.out.println("Servicos por aceitar:\n"+gereServicos.listarServicos());
																System.out.println(gereServicos.ListarServicosEstado(1));
																int codigo = dadosIntIn("Insira o codigo do servico que pretende aceitar: ");
																boolean resultado = gereServicos.AceitarServico(codigo);
																if(resultado){
																	System.out.println("O servico foi aceite com sucesso.");
																	log.adicionarFicheiroLog(userAux.getNome(),"Aprovou o servico " + codigo);
																}
																else {
																	System.out.println("Nao existe nenhum servico com esse codigo por aceitar.");
																	log.adicionarFicheiroLog(userAux.getNome(),"Tentou aceitar um servico invalido");
																}
																break;
															}
															case 2:{
																log.adicionarFicheiroLog(userAux.getNome(),"Selecionou a opcao de encerrar um servico");
																System.out.println("Servicos concluidos:\n"+gereServicos.listarServicos());
																System.out.println(gereServicos.ListarServicosEstado(4));
																int codigo = dadosIntIn("Insira o codigo do servico que pretende encerrar: ");
																boolean resultado = gereServicos.EncerrarServico(codigo);
																if(resultado){
																	System.out.println("O servico foi encerrado com sucesso.");
																	log.adicionarFicheiroLog(userAux.getNome(),"Encerrou o servico " +codigo);
																}
																else {
																	System.out.println("Nao existe nenhum servico com esse codigo por encerrar.");
																	log.adicionarFicheiroLog(userAux.getNome(),"Tentou encerrar um servico invalido");
																}
																break;
															}
														}

													}while (opcao != 3);
													log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de aprovacao e encerramento de servicos");
													break;
												}
												// Listar os servicos de um cliente
												case 3:{
													log.adicionarFicheiroLog(userAux.getNome(),"Selecionou a opcao de pesquisar servicos de um cliente");
													System.out.println("""
															###############
															Lista de clientes
															###############
															""");
													System.out.println(gereUsers.ListarUsersTipo(3));
													int nif = dadosIntIn("Introduza o NIF do cliente: ");
													gereServicos gereServicosAux = gereServicos.pesquisaServicosCliente(nif);
													System.out.println(gereServicosAux.listarServicos());
													log.adicionarFicheiroLog(userAux.getNome(),"Listou os servicos associados ao NIF: "+ nif);
													break;
												}
												case 4:{
													log.adicionarFicheiroLog(userAux.getNome(),"Exibiu os servicos por estado");
													System.out.println("""
															###############
															Servicos
															###############
															""");
													System.out.println("A decorrer:");
													gereServicos.ListarServicosEstado(3);
													System.out.println("Concluidos");
													gereServicos.ListarServicosEstado(5);
													break;
												}
												case 5:{
													int codigo = dadosIntIn("Introduza o codigo de servico:");
													System.out.println(gereServicos.pesquisaServicosCodigo(codigo));
													log.adicionarFicheiroLog(userAux.getNome(),"Pesquisou pelo servico " + codigo);
													break;
												}
												case 6:{
													int tempo = dadosIntIn("Introduza o tempo despendido em horas:");
													gereServicos gereServicosAux = gereServicos.pesquisaServicosTempo(tempo);
													System.out.println(gereServicosAux.listarServicos());
													log.adicionarFicheiroLog(userAux.getNome(),"Pesquisou por servicos que tenham levado mais de " + tempo +" horas");
													break;
												}
											}
										}while (opcao != 7);
										log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de gestao de servicos");
										break;
									}
									case 4:{
										System.out.println("""
												###############
												Log
												###############
												""");
										log.adicionarFicheiroLog(userAux.getNome(),"Exibiu o log");
										log.lerFicheiroLog();
									}
								}
							}while (opcao!=5);
							System.out.println("Adeus " + userAux.getNome() );
							log.adicionarFicheiroLog(userAux.getNome(),"Fez LogOut");
							break;
						}
						//Farmaceutico
						case 2:{
							MenuFarmaceutico();
							do {
								opcao = MenuFarmaceutico();
								switch (opcao){
									case 1:{
										do {
											log.adicionarFicheiroLog(userAux.getNome(),"Acedeu ao menu de gestao de servicos");
											opcao = MenuGereServicos();
											switch (opcao){
												case 1:{
													System.out.println("""
														###############
														Lista de servicos
														###############
														""");
													System.out.println(gereServicos.listarServicosFarmaceutico(userAux.getNif()));
													log.adicionarFicheiroLog(userAux.getNome(),"Listou os seus servicos");
													break;
												}
												case 2:{
													System.out.println("""
														###############
														Servicos por iniciar
														###############
															""");
													System.out.println(gereServicos.pesquisaServicosFarmaceuticos(userAux.getNif()).ListarServicosEstado(2));
													log.adicionarFicheiroLog(userAux.getNome(),"Listou os servicos que estao por ser aceites por ele");
													int codigo = dadosIntIn("Insira o codigo do servico que deseja iniciar: ");
													boolean resultado = gereServicos.IniciarServico(codigo);
													if(resultado){
														System.out.println("O servico foi iniciado com sucesso.");
														log.adicionarFicheiroLog(userAux.getNome(),"Aceitou o servico"+codigo);
														break;
													}
													else {
														System.out.println("Nao existe nenhum servico com esse codigo por iniciar.");
														log.adicionarFicheiroLog(userAux.getNome(),"Tentou aceitar um servico invalido");
													}
													break;
												}
												case 3:{
													System.out.println("""
														###############
														Servicos por concluir
														###############
															""");
													System.out.println(gereServicos.pesquisaServicosFarmaceuticos(userAux.getNif()).ListarServicosEstado(3));
													log.adicionarFicheiroLog(userAux.getNome(),"Listou os servicos que estao por ser concluidos por ele");
													int codigo = dadosIntIn("Insira o codigo do servico que deseja concluir: ");
													if(gereServicos.pesquisaServicosCodigo(codigo) != null && gereServicos.pesquisaServicosCodigo(codigo).getEstado() == 3){
														int tempo = dadosIntIn("Intoduza as horas que o pedido levou :" );
														boolean resultado = gereServicos.ConcluirServico(codigo, tempo);
														System.out.println("O pedido foi concluido com sucesso.");
														log.adicionarFicheiroLog(userAux.getNome(),"Concluiu o servico"+codigo+" em "+tempo+" horas");
													}
													else {
														System.out.println("Nao existe nenhum servico com esse codigo por concluir.");
														log.adicionarFicheiroLog(userAux.getNome(),"Tentou concluir um servico invalido");
													}
													break;
												}
											}
										}while (opcao != 4);
										log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de gestao de servicos");
										break;
									}
									case 2:{
										MenuGereMedicamentos(userAux);
										break;
									}
									//Nao foi feito
									case 3:{
										System.out.println("Gerir categorias");
										log.adicionarFicheiroLog(userAux.getNome(),"Entrou no menu de gestao de categorias");
										break;
									}
									//Nao foi feito
									case 4:{
										System.out.println("Gerir ecxipientes");
										log.adicionarFicheiroLog(userAux.getNome(),"Entrou no menu de gestao de ecxipientes");
										break;
									}
								}
							}while (opcao != 5);
							System.out.println("Adeus "+ userAux.getNome());
							log.adicionarFicheiroLog(userAux.getNome(),"Fez LogOut");
							break;
						}
						//Cliente
						case 3:{
							do {
								opcao = MenuCliente();
								switch (opcao){
									case 1:{
										System.out.println("""
												###############
												Pedir Servico
												###############
												""");

									}
									case 2:{

									}
								}

							}while (opcao != 3);
							break;
						}
					}
					break;
				}
				default:{
					System.out.println("Opcao invalida !!!");
					break;
				}
			}
		}while (opcao != 3);
		escreverFicheiro(memoria);
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
		boolean estado = true;
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
			estado = false;
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
		utilizador userAux = new utilizador(user, password, nome, estado, email, tipo, nif, morada, contacto);
		info.ficheiroCredenciais(user,password);
		gereUsers.inserirUser(userAux);
		return userAux;
	}
	private static void MenuGereMedicamentos(utilizador userAux){
			log.adicionarFicheiroLog(userAux.getNome(),"Entrou no menu de gestao de medicamentos");
			int opcao;
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
						log.adicionarFicheiroLog(userAux.getNome(),"Listou todos os medicamentos");
						break;
					}
					case 2:{
						gereMedicamentos.ordenaMedicamentos();
						System.out.println("Os medicamentos foram ordenados pela designacao");
						log.adicionarFicheiroLog(userAux.getNome(),"Ordenou os medicamentos pela sua designacao");
						break;
					}
					case 3:{
						System.out.println("""
															###############
															Pesquisa por designacao
															###############
															""");
						String designacao = dadosStringsIn("Introduza a designacao do medicamento: ");
						System.out.println(gereMedicamentos.pesquisaMedicamentosNome(designacao));
						log.adicionarFicheiroLog(userAux.getNome(),"Procurou por um medicamento com o nome: "+designacao);
						break;
					}
					//Nao funciona
					case 4:{
						System.out.println("""
															###############
															Pesquisa por categoria
															###############
															""");
						int codigo = dadosIntIn("Introduza a categoria do medicamento: ");
						//System.out.println(gereMedicamentos.pesquisaMedicamentosCat(codigo));
						break;
					}
					case 5:{
						System.out.println("""
															###############
															Pesquisa de medicamento por componente ativo
															###############
															""");
						String designacao = dadosStringsIn("Introduza a designacao do componente ativo");
						System.out.println(gereMedicamentos.pesquisaMedicamentosCompAAtiva(designacao));
						log.adicionarFicheiroLog(userAux.getNome(),"Pesquisou por um medicamento com o componenente ativo: " + designacao);
						break;
					}
					case 6:{
						do {
							log.adicionarFicheiroLog(userAux.getNome(),"Entrou no menu de exibir medicamento genericos ou nao genericos");
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
									log.adicionarFicheiroLog(userAux.getNome(),"Exibiu os medicamentos genericos existentes no sistema");
									break;
								}
								case 2:{
									System.out.println("""
																		###############
																		Medicamentos nao genericos
																		###############
																		""");
									System.out.println(gereMedicamentos.pesquisaMedicamentosGen(false));
									log.adicionarFicheiroLog(userAux.getNome(),"Exibiu os medicamentos nao genericos existentes no sistema");
									break;
								}
								default:{
									System.out.println("Opcao invalida !!!");
									log.adicionarFicheiroLog(userAux.getNome(),"Selecionou uma opcao inalvida no menu de exibicao de medicamento genericos ou nao genericos");
								}
							}
						}while (opcao != 3);
						log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de exibicao de medicamentos genericos ou nao genericos");
						break;
					}
					case 7:{
						int quantia = dadosIntIn("Introduza a quantia maxima do stock");
						gereMedicamentos gereMedicamentosAux = gereMedicamentos.pesquisaMedicamentosStock(quantia);
						System.out.println(gereMedicamentosAux.ListarMedicamentos());
						log.adicionarFicheiroLog(userAux.getNome(),"Exibiu os medicamentos com stock inferior a " + quantia + " unidades");
						break;
					}
					case 8:{
						System.out.println("""
															###############
															Inserir medicamento
															###############
															""");
						medicamentos medAux = Criamed();
						if(gereMedicamentos.inserirMedicamentos(medAux)){
							System.out.println("O medicamento foi inserido no sistema com exito.");
							log.adicionarFicheiroLog(userAux.getNome(),"Inseriu o medicamento " + medAux.getNome() + "no sistema" );
						}
					}
				}
			}while (opcao != 9);
			log.adicionarFicheiroLog(userAux.getNome(),"Saiu do menu de gestao de medicamentos");
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
	//------------------Medicamentos-----------------------
	private static medicamentos Criamed(){
		String nome = dadosStringsIn("Introduza o nome do medicamento: ");
		String marca = dadosStringsIn("Introduza a marca do medicamento: ");
		int lote = dadosIntIn("Introduza o lote do medicamento: ");
		int dosagem = dadosIntIn("Introduza a dosagem: ");
		int quantidade = dadosIntIn("Introduza a quantidade em stock: ");
		float preco = dadosFloatIn("Introduza o preco: ");
		int ano = dadosIntIn("Introduza o ano de fabrico: ");
		boolean autorização = false;
		int confirmacao = dadosIntIn("""
          \tO medicamento necessita de autorizacao medica ? S/N :
          \t[1] Sim
          \t[2] Nao
          """);
		if(confirmacao == 1){
			autorização = true;
		}
		boolean generico = false;
		int gen = dadosIntIn("""
          \tO medicamento e generico ?:
          \t[1] Sim
          \t[2] Nao
          \tEscolha uma opcao:\s
          """);
		if(gen == 1){
			generico = true;
		}
		return new medicamentos(nome,marca,lote,Criacomponente(),dosagem,quantidade,preco,ano,autorização,generico);
	}
	private static compAtivo Criacomponente(){
		String designacao = dadosStringsIn("Introduza a designacao do co componenete ativo");
		int codigo = dadosIntIn("Introduza o codigo do componente ativo");
		int quantidade = dadosIntIn("Introduza a quantidade: ");
		return new compAtivo(designacao,codigo,quantidade);
	}
	private static exipiente Criaexipiente(){
		String designacao = dadosStringsIn("Introduza a designacao do co componenete ativo");
		int classificacao = dadosIntIn("Introduza o codigo do componente ativo");
		int quantidade = dadosIntIn("Introduza a quantidade: ");
		return new exipiente(designacao,classificacao,quantidade);
	}
	private static categoria Criacategoria(){
		String designacao = dadosStringsIn("Introduza a designacao do co componenete ativo");
		int classificacao = dadosIntIn("Introduza o codigo do componente ativo");
		int quantidade = dadosIntIn("Introduza a quantidade: ");
		String fornecedor = dadosStringsIn("Introduza o nome do fornecedor: ");
		return new categoria(designacao,classificacao,quantidade,fornecedor);
	}
	//-------------------Servicos--------------------------
	private static servicos CriaServico(utilizador userAux){
		int opcao;
		projProg.gereMedicamentos medsAux = new gereMedicamentos();
		do {
			opcao = dadosMenuOut("""
                ###############
                Criar Servico
                ###############
                \t[1]Adicionar medicamento
                \t[2]Remover medicamento
                \t[3]Listar medicamento associados
                \t[4]Continuar
                \tEscolha uma Opcao:\s""");
			switch (opcao){
				case 1:{
					System.out.println("""
					###############
					Medicamentos disponiveis
					###############
					""");
					gereMedicamentos.ListarMedicamentos();
					String nome = dadosStringsIn("Indique o nome do medicamento que prentende adicionar ao servico");
					if(medsAux.pesquisaMedicamentosNome(nome) != null){
						System.out.println("O medicamento ja esta associado ao servico");
					}
					else {
						System.out.println("O medicamento foi associado ao servico");
					}
					break;
				}
				case 2:{
					System.out.println("""
					###############
					Medicamentos associados
					###############
					""");
					medsAux.ListarMedicamentos();
					String nome = dadosStringsIn("Indique o nome do medicamento que prentende remover do servico");
					if (medsAux.removeMedicamento(medsAux.pesquisaMedicamentosNome(nome))){
						System.out.println("O medicamento foi removido com sucesso.");
					}
					else {
						System.out.println("Ocorreu um erro a remover o medicamento.");
					}
					break;
				}
				case 3:{
					System.out.println("""
					###############
					Medicamentos associados
					###############
					""");
					medsAux.ListarMedicamentos();
					break;
				}
			}
		}while (opcao != 4);
		return new servicos(medsAux,userAux);
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
                \t[4]Visualizar log
                \t[5]Exit
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
                \t[8]Inserir Medicamento
                \t[9]Exit
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
	private static int MenuGereServicos(){
		return dadosMenuOut("""
                ###############
                Gerir Servicos
                ###############
                \t[1]Listar os seus Servicos
                \t[2]Iniciar um servico
                \t[3]Concluir um servico
                \t[4]Exit
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
		try {
			while(true) {
				System.out.println(aDados);
				String input = (new Scanner(System.in)).next();
				if (input.length() >= 2) {
					return input;
				} else {
					System.out.println("Input invalido. Insira no minimo 2 caracteres.");
				}
			}
		} catch(InputMismatchException a) {
			return null;
		}
	}
	
	private static int dadosIntIn(String aDados) {
		try {
			System.out.println(aDados);
			return (new Scanner(System.in)).nextInt();
		}catch(InputMismatchException a) {
			return -1;
		}
	}
	private static float dadosFloatIn(String aDados) {
		try {
			System.out.println(aDados);
			return (new Scanner(System.in)).nextFloat();
		} catch (InputMismatchException a) {
			return -1.0f;
		}
	}
	//------------------Ficheiros-----------------------
	private static gereFicheiroObj lerficheiro() {
		File file = new File("dados_apl.dat");
		if(file.exists()) {
			try {
				FileInputStream fIS = new FileInputStream( file );
				ObjectInputStream oIS = new ObjectInputStream( fIS );
				gereFicheiroObj ficheiro = ( (gereFicheiroObj)oIS.readObject() );
				oIS.close();
				fIS.close();
				return verificaFicheiro(ficheiro);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}

		}
		else {
			System.out.println("Ficheiro de Objetos nao existe");
		}
		return new gereFicheiroObj(gereUsers,gereMedicamentos,gereServicos);
	}
	private static void escreverFicheiro(gereFicheiroObj ficheiro) {
		try {
			File file = new File("dados_apl.dat");
			FileOutputStream fOS = new FileOutputStream( file);
			ObjectOutputStream oOS = new ObjectOutputStream( fOS );
			oOS.writeObject(ficheiro);
			oOS.flush();
			oOS.close();
			fOS.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	private static gereFicheiroObj verificaFicheiro(gereFicheiroObj ficheiro){
		if(ficheiro != null) {
			gereUsers = ficheiro.getGereUtilizadores();
			gereMedicamentos= ficheiro.getGereMedicamentos();
			gereServicos = ficheiro.getGereServicos();
		}
		else  {
			ficheiro = new gereFicheiroObj(gereUsers,gereMedicamentos,gereServicos);
		}
		return ficheiro;
	}
	
}