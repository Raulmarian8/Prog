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
	
	
	
//	Listar todos os servicos de um cliente
	public String listarServicosCliente(utilizador aCliente){
        Enumeration <servicos> e = Collections.enumeration(servicosList);
        String servicosInfo = "";
        servicos servico;
        while (e.hasMoreElements()) {
        	servico = e.nextElement();
            if (servico.getCliente() == aCliente) {
            	servicosInfo += servico + "\n";
            }
        }
        return servicosInfo;
    }
	
//	Listar todos os servicos por estado
	public String ListarServicosEstado(int aEstado) {
		if(aEstado == 3 || aEstado == 5) {
			if(servicosList != null && servicosList.size()>0) {
				Enumeration<servicos> e = Collections.enumeration(servicosList);
				String servicosInfo = "";
				servicos servicoAux;
				while (e.hasMoreElements()) {
					servicoAux = e.nextElement();
					if(servicoAux.getEstado() == aEstado) {
						servicosInfo += servicoAux + "\n";
					}
				}
				return servicosInfo;
			}
			return null;
		}return null;
	}
	
	
//	pesquisa servicos por codigo
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
	
//	pesquisa servicos por tempo despendido
	public servicos pesquisaServicosTempo (int aTempo) {
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getTempo() > (aTempo)) {
				return servicosAux;
			}
		}
		return null;
	}

	
//	pesquisa servicos associado a um cliente
	public servicos pesquisaServicosCliente (int aNif) {
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getCliente().getNif() == aNif){
				return servicosAux;
			}
		}
		return null;
	}
	
//	pesquisa servicos associado a um farmaceutico
	public servicos pesquisaServicosFarmaceuticos (int aNif) {
		Enumeration<servicos> e = Collections.enumeration(servicosList);
		servicos servicosAux;
		while (e.hasMoreElements()) {
			servicosAux = e.nextElement();
			if(servicosAux.getFarmaceutico().getNif() == aNif) {
				return servicosAux;
			}
		}
		return null;
	}
	
//	Listar todos os servicos associados a um farmaceutico
	public String listarServicosFarmaceutico(utilizador aFarmaceutico){
        Enumeration <servicos> e = Collections.enumeration(servicosList);
        String servicosInfo = "";
        servicos servico;
        while (e.hasMoreElements()) {
        	servico = e.nextElement();
            if (servico.getFarmaceutico() == aFarmaceutico) {
            	servicosInfo += servico + "\n";
            }
        }
        return servicosInfo;
    }
	
//	Listar todos os servicos
	public String ListarServicos(){
        Enumeration <servicos> e = Collections.enumeration(servicosList);
        String servicosInfo = "";
        while (e.hasMoreElements()) {
        	servicosInfo += e.nextElement() + "\n";
        }
        return servicosInfo;
    }
}
