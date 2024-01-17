package projProg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class compAtivo implements Serializable{
	
	private String designacao;
	private int codigo;
	private int quantidade;
	
	compAtivo(String aDesignacao, 
			int aCodigo, int aQuantidade) {
		designacao = aDesignacao;
		codigo = aCodigo;
		quantidade = aQuantidade;
	}

	public String getDesignacao() {
		return designacao;
	}

	public String toString() {
        return "\n\tDesignacao: "+designacao + 
        		"\n\tCodigo: "+codigo+
        		"\n\tQuantidade: "+quantidade;
    }

}
