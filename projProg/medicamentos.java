package projProg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class medicamentos implements Comparable<medicamentos>, Serializable{

	private String nome;
	private String marca;
	private int lote;
	private compAtivo componenteAtivo;
	private int dosagem;
	private int quantidade;
	private float preco;
	private int ano;
	private boolean autorizacao;
	private boolean generico;
	ArrayList<exipiente> exc;
	ArrayList<categoria> cat;
	
	medicamentos(String aNome, String aMarca,
			int aLote, compAtivo aComponenteAtivo,
			int aDosagem, int aQuantidade, float aPreco,
			int aAno, boolean aAutorizacao, boolean aGenerico) {
		
		nome = aNome;
		marca = aMarca;
		lote = aLote;
		componenteAtivo = aComponenteAtivo;
		dosagem = aDosagem;
		quantidade = aQuantidade;
		preco = aPreco;
		ano = aAno;
		autorizacao = aAutorizacao;
		generico = aGenerico;
		exc = new ArrayList<>();
		cat  = new ArrayList<>();
		
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}
	
	public ArrayList<categoria> getCat() {
		return cat;
	}
	

	public compAtivo getComponenteAtivo() {
		return componenteAtivo;
	}
	
	

	public boolean isGenerico() {
		return generico;
	}


	public void setGenerico(boolean generico) {
		this.generico = generico;
	}


	public int getQuantidade() {
		return quantidade;
	}


	public int compareTo(medicamentos aObj) {
		return nome.compareTo(aObj.getNome());	
	}
	
	
	
	
	
	
}
