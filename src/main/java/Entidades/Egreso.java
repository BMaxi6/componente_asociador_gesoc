package Entidades;

import CriteriosAsociacion.CondicionAsociacion;
import Repositorio.EntidadPersistente;

import java.time.LocalDate;
import java.util.List;

public class Egreso implements EntidadPersistente {

    public Integer numeroOp;
    public int id;
    public Double valor;
    public LocalDate fecha;
    private Ingreso ingreso = null;

    public Egreso(Integer numeroOp, int id, Double valor, LocalDate fecha) {
        this.numeroOp = numeroOp;
        this.id = id;
        this.valor = valor;
        this.fecha = fecha;
    }

    public Double valor(){ return this.valor; }

    public LocalDate getFecha(){ return this.fecha; }

    public Ingreso getIngreso(){ return this.ingreso; }

    public Integer getNumeroOp(){ return this.numeroOp; }

    public void setIngreso(Ingreso unIngreso){ this.ingreso = unIngreso; }

    public boolean disponibleParaAsociar(){
        return this.getIngreso() == null;
    }

    public boolean puedeAsociar(Ingreso ingreso, List<CondicionAsociacion> condiciones){
        for(CondicionAsociacion condicion: condiciones){
            if(!condicion.validar(ingreso, this)){
              return false;
            }
        }
       return true;
    }

    public void asociarIngresos(List<Ingreso> ingresosAAsociar, List<CondicionAsociacion> condiciones){
        for (Ingreso unIngreso: ingresosAAsociar) {
            Egreso egresoAux = new Egreso(this.numeroOp, this.id, this.valor(), this.fecha);
            if (this.puedeAsociar(unIngreso, condiciones) ) {
                this.setIngreso(unIngreso);
                unIngreso.asociarEgreso(egresoAux);
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }
}
