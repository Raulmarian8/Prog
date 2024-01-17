package projProg;

import projProg.exipiente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
public class gereExipiente {
    ArrayList <exipiente> exipienteList;

    public boolean inserirExipiente(exipiente aExipiente) {
        if(exipienteList != null) {
            return exipienteList.add(aExipiente);
        }
        return false;
    }

    public boolean listaVazia() {
        return exipienteList.isEmpty();
    }
}
