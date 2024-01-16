package projProg;

import java.io.Serializable;

public class exipiente implements Serializable{

	private String designacao;
	private int classificacao;
	private int quantidade;
	
	exipiente(String aDesignacao, int aClassificacao,
			int aQuantidade) {
		designacao = aDesignacao;
		classificacao = aClassificacao;
		quantidade = aQuantidade;
		
	}
	
	public String toString() {
		return "\n\tDesignacao: "+designacao+
				"\n\tClassificacao: "+classificacao+
				"\n\tQuantidade: "+quantidade;
	}
}
