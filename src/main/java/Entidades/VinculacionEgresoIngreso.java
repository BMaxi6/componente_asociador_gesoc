package Entidades;

import java.util.List;

public class VinculacionEgresoIngreso {
    public int idIngreso;
    public int idEgresos;

    public void setIdIngreso(Ingreso unIngreso){
        this.idIngreso = unIngreso.getId();
    }

    public void setIdEgreso(Egreso unEgreso){
        this.idEgresos = unEgreso.getId();
    }

    public void setIdIngreso(int idIngreso){ this.idIngreso = idIngreso; }
}
