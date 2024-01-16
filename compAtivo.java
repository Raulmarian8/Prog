package projProg;

import java.io.Serializable;

public class compAtivo {
	
	private String designacao;
	private int codigo;
	private int quantidade;
	
	compAtivo(String aDesignacao, 
			int aCodigo, int aQuantidade) {
		designacao = aDesignacao;
		codigo = aCodigo;
		quantidade = aQuantidade;
	}
	
	public String toString() {
        return "\n\tDesignacao: "+designacao + 
        		"\n\tCodigo: "+codigo+
        		"\n\tQuantidade: "+quantidade;
    }

}
