package projProg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class gereMedicamentos implements Serializable {

	ArrayList <medicamentos> medicamentosList;
	
	gereMedicamentos() {
		medicamentosList = new ArrayList<medicamentos>();
	}
	
	public boolean inserirMedicamentos(medicamentos aMedicamentos) {
		if(medicamentosList != null) {
			return medicamentosList.add(aMedicamentos);
		}
		return false;
	}
	
	public boolean listaVazia() {
        return medicamentosList.isEmpty();
    }
	
	
//	Ordenar medicamentos por designacao/nome
	public void ordenaMedicamentos() {
		Collections.sort(medicamentosList);
	}
	
//	Listar todos os medicamentos
	public String ListarMedicamentos() {
		Enumeration<medicamentos> e = Collections.enumeration(medicamentosList);
		String medicamentosInfo = "-------------------------";
		while (e.hasMoreElements()) {
			medicamentosInfo += e.nextElement() + "-------------------------" + "\n";
		}
		return medicamentosInfo;
	}
	
//	pesquisa medicamentos por nome/designacao
	public medicamentos pesquisaMedicamentosNome (String aNome) {
		Enumeration<medicamentos> e = Collections.enumeration(medicamentosList);
		medicamentos medicamentosAux;
		while (e.hasMoreElements()) {
			medicamentosAux = e.nextElement();
			if(medicamentosAux.getNome().equals(aNome)) {
				return medicamentosAux;
			}
		}
		return null;
	}
	
//	pesquisa medicamentos por categoria
	public medicamentos pesquisaMedicamentosCat (categoria aCategoria) {
		Enumeration<medicamentos> e = Collections.enumeration(medicamentosList);
		medicamentos medicamentosAux;
		while (e.hasMoreElements()) {
			medicamentosAux = e.nextElement();
			if(medicamentosAux.getCat().equals(aCategoria)) {
				return medicamentosAux;
			}
		}
		return null;
	}
	
//	pesquisa medicamentos por componente Ativa
	public medicamentos pesquisaMedicamentosCompAAtiva (String designacao) {
		Enumeration<medicamentos> e = Collections.enumeration(medicamentosList);
		medicamentos medicamentosAux;
		while (e.hasMoreElements()) {
			medicamentosAux = e.nextElement();
			if(medicamentosAux.getComponenteAtivo().getDesignacao().equalsIgnoreCase(designacao)) {
				return medicamentosAux;
			}
		}
		return null;
	}
	
//	pesquisa medicamentos por generico
	public medicamentos pesquisaMedicamentosGen (boolean aGenerico) {
		Enumeration<medicamentos> e = Collections.enumeration(medicamentosList);
		medicamentos medicamentosAux;
		while (e.hasMoreElements()) {
			medicamentosAux = e.nextElement();
			if(medicamentosAux.isGenerico() == (aGenerico)) {
				return medicamentosAux;
			}
		}
		return null;
	}
	
	
	

	
}
