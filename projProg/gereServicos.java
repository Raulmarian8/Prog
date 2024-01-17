package projProg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class gereServicos implements Serializable {
	ArrayList<servicos>servicosList;
	
	gereServicos() {
		servicosList = new ArrayList<servicos>();
	}
	
	public boolean inserirServico(servicos aServicos) {
		if (servicosList != null) {
			return servicosList.add(aServicos);
		}
		return false;
	}

	public boolean AceitarServico(int codigo) {
		servicos  servico = pesquisaServicosCodigo(codigo);
			if (servico.getCodigo() == codigo && servico.getEstado() == 1 ) {
				servico.setEstado(2);
				return true;
			}
		return false;
	}
	public boolean IniciarServico(int codigo) {
		servicos  servico = pesquisaServicosCodigo(codigo);
		if (servico.getCodigo() == codigo && servico.getEstado() == 2) {
			servico.setEstado(3);
			return true;
		}
		return false;
	}
	public boolean ConcluirServico(int codigo, int tempo) {
		servicos  servico = pesquisaServicosCodigo(codigo);
		if (servico.getCodigo() == codigo && servico.getEstado() == 3) {
			servico.setEstado(4);
			servico.setTempo(tempo);
			return true;
		}
		return false;
	}
	public boolean EncerrarServico(int codigo) {
		servicos  servico = pesquisaServicosCodigo(codigo);
			if (servico.getCodigo() == codigo && servico.getEstado() == 4) {
				servico.setEstado(5);
				return true;
			}
		return false;
	}

//	Pesquisas servicos por codigo
	public servicos pesquisaServicosCodigo (int aCodigo) {
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getCodigo() == (aCodigo)) {
				return servicosAux;
			}
		}
		return null;
	}
//	Listar todos os servicos
	public String listarServicos(){
		Enumeration <servicos> e = Collections.enumeration(servicosList);
		String servicosInfo = "-------------------------";
		servicos servico;
		while (e.hasMoreElements()) {
			servicosInfo +=  e.nextElement() + "-------------------------" +"\n";
		}
		return servicosInfo;
	}
//	Listar todos os servicos de um cliente
	public String listarServicosCliente(utilizador aCliente){
        Enumeration <servicos> e = Collections.enumeration(servicosList);
        String servicosInfo = "-------------------------";
        servicos servico;
        while (e.hasMoreElements()) {
        	servico = e.nextElement();
            if (servico.getCliente() == aCliente) {
            	servicosInfo += servico + "-------------------------" + "\n";
            }
        }
        return servicosInfo;
    }
	
//	Listar todos os servicos por estado
	public String ListarServicosEstado(int aEstado) {
		if(aEstado == 3 || aEstado == 5) {
			if(servicosList != null && servicosList.size()>0) {
				Enumeration<servicos> e = Collections.enumeration(servicosList);
				String servicosInfo = "-------------------------";
				servicos servicoAux;
				while (e.hasMoreElements()) {
					servicoAux = e.nextElement();
					if(servicoAux.getEstado() == aEstado) {
						servicosInfo += servicoAux + "-------------------------" + "\n";
					}
				}
				return servicosInfo;
			}
			return null;
		}return null;
	}
//    pesquisa servicos associado a um cliente
	public gereServicos pesquisaServicosCliente (int aNif) {
		gereServicos gereServicosAux = new gereServicos();
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getCliente().getNif() == aNif){
				gereServicosAux.inserirServico(servicosAux);
			}
		}
		return gereServicosAux;
	}//    pesquisa servicos associado a um farmaceutico
	public gereServicos pesquisaServicosFarmaceuticos (int aNif) {
		gereServicos gereServicosAux = new gereServicos();
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getFarmaceutico().getNif() == aNif) {
				gereServicosAux.inserirServico(servicosAux);
			}
		}
		return gereServicosAux;
	}
	//	Listar todos os servicos associados a um farmaceuticos
	public String listarServicosFarmaceutico(int aNif){
		Enumeration <servicos> e = Collections.enumeration(servicosList);
		String servicosInfo = "-------------------------";
		servicos servico;
		while (e.hasMoreElements()) {
			servico = e.nextElement();
			if (servico.getFarmaceutico().getNif() == aNif) {
				servicosInfo += servico + "-------------------------" + "\n";
			}
		}
		return servicosInfo;
	}
//	pesquisa servicos por tempo despendido
	public gereServicos pesquisaServicosTempo (int aTempo) {
		gereServicos gereServicosAux = new gereServicos();
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getTempo() > (aTempo)) {
				gereServicosAux.inserirServico(servicosAux);
			}
		}
		return gereServicosAux;
	}


}
