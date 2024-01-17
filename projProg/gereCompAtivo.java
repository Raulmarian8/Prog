package projProg;
import projProg.compAtivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class gereCompAtivo implements Serializable{
    ArrayList <compAtivo> compAtivoList;

    public boolean inserirCompAtivo(compAtivo aComponeteAtivo) {
        if(compAtivoList != null) {
            return compAtivoList.add(aComponeteAtivo);
        }
        return false;
    }

    public boolean listaVazia() {
        return compAtivoList.isEmpty();
    }
}
