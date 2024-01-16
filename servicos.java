package projProg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class servicos implements Serializable{

	private utilizador farmaceutico;
	ArrayList <medicamentos> medi; 
	private float valTotal;
	private String data;
	private int tempo;
	private String descricao;
	private boolean tipo;
	private int estado;
	private static int codigo;
	private utilizador cliente;
	
	servicos (utilizador aFarmaceutico, float aValTotal, String aData, int aTempo,
			String aDescricao, boolean aTipo, 
			int aEstado, utilizador aCliente){
		farmaceutico = aFarmaceutico;
		medi = new ArrayList<medicamentos>();
		valTotal = aValTotal;
		data = aData;
		tempo = aTempo;
		descricao = aDescricao;
		tipo = aTipo;
		estado = aEstado;
		codigo = codigo++;
		cliente = aCliente;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public utilizador getCliente() {
		return cliente;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public utilizador getFarmaceutico() {
		return farmaceutico;
	}


	public String toString() {
		String tipoAux = "";
		String estadoAux = "";
		switch (estado) {
			case 1:{
				estadoAux = "Pendente";
				break;
			}
			case 2:{
				estadoAux = "Aceite";
				break;
			}
			case 3:{
				estadoAux = "Decorrer";
				break;
			}
			case 4:{
				estadoAux = "Concluida";
				break;
			}
			case 5:{
				estadoAux = "Encerrado";
				break;
			}
		}
		if (tipo) {
			tipoAux = "Normal";
		}else {
			tipoAux = "Urgente";
		} return "\n\tTotal: "+valTotal+"\n\tData: "+data+
				"\n\tTempo: "+tempo+"\n\tDescricao: "
				+descricao+"\n\tTipo: "+tipoAux+"\n\tEstado: "
				+estadoAux+"\n\tCodigo; "+codigo;
	}
	
}
