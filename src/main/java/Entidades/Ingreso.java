package Entidades;

import CriteriosAsociacion.CondicionAsociacion;
import Entidades.Egreso;
import Repositorio.EntidadPersistente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ingreso implements EntidadPersistente {

    public int id;
    public Double valor;
    public LocalDate fecha;
    public LocalDate fechaDeAceptacion;
    private List<Egreso> egresos = new ArrayList<>();
    public Double valorRestante;

    public Ingreso(Double valor, LocalDate fecha, LocalDate fechaAceptacion, Double valorRestante) {
        this.valor = valor;
        this.fecha = fecha;
        this.fechaDeAceptacion = fechaAceptacion;
        this.valorRestante = valorRestante;
    }

    public Double getValorRestante() {
        return valorRestante;
    }

    public Double valor(){ return this.valor; }

    public LocalDate getFecha(){ return this.fecha; }

    public LocalDate getFechaDeAceptacion() { return fechaDeAceptacion; }

    public List<Egreso> getEgresos(){ return this.egresos; }

    public void setValor(Double unValor){ valor = unValor; }

    public void setFecha(LocalDate unaFecha){ fecha = unaFecha; }

    public void setFechaDeAceptacion(LocalDate date){ fechaDeAceptacion = date; }

    public Double valorEgresosAsociados(){
        return (Double) (egresos.stream().mapToDouble(unEgreso -> unEgreso.valor()).sum());
    }

    public void asociarEgreso(Egreso unEgreso){
        egresos.add(unEgreso);
        valorRestante = valorRestante - unEgreso.valor();
    }

    public void asociarEgresos(List<Egreso> egresosAAsociar, List<CondicionAsociacion> condiciones){
         for (Egreso unEgreso: egresosAAsociar) {
            if (this.puedeAsociar(unEgreso, condiciones) ) {
                this.asociarEgreso(unEgreso);
                unEgreso.setIngreso(this);
            }
         }
    }

    public boolean puedeAsociar(Egreso egreso, List<CondicionAsociacion> condiciones){
        for(CondicionAsociacion condicion: condiciones){
            if(!condicion.validar(this, egreso)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int getId() {
        return id;
    }
}