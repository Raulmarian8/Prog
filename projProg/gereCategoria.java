package projProg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class gereCategoria implements Serializable{
    ArrayList <categoria> categoriaList;

    public boolean inserirCategoria(categoria aCategoria) {
        if(categoriaList != null) {
            return categoriaList.add(aCategoria);
        }
        return false;
    }

    public boolean listaVazia() {
        return categoriaList.isEmpty();
    }
}
