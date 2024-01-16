package projProg;

import java.io.Serializable;


public class categoria {

	private String designacao;
	private int classificacao;
	private int codigo;
	private String fornecedor;
	
	categoria(String aDesignacao, int aClassificacao,
			int aCodigo, String aFornecedor){
		designacao = aDesignacao;
		classificacao = aClassificacao;
		codigo = aCodigo;
		fornecedor = aFornecedor;
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String toString() {
		return "\n\tDesignacao: "+designacao+"\n\tClassificacao: "
				+classificacao+"\n\tCodigo: "+codigo+
				"\n\tFornecedor: "+fornecedor;
	}
	
	
}
