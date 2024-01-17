package projProg;

import java.io.Serializable;

public class gereFicheiroObj implements Serializable {
    private gereUtilizador gereUtilizadores;
    private gereMedicamentos gereMedicamentos;
    private gereServicos gereServicos;

    public gereUtilizador getGereUtilizadores() {
        return gereUtilizadores;
    }

    public projProg.gereMedicamentos getGereMedicamentos() {
        return gereMedicamentos;
    }

    public projProg.gereServicos getGereServicos() {
        return gereServicos;
    }

    public gereFicheiroObj() {
    }

    public gereFicheiroObj(gereUtilizador gereUtilizadores, projProg.gereMedicamentos gereMedicamentos, projProg.gereServicos gereServicos) {
        this.gereUtilizadores = gereUtilizadores;
        this.gereMedicamentos = gereMedicamentos;
        this.gereServicos = gereServicos;
    }
}
