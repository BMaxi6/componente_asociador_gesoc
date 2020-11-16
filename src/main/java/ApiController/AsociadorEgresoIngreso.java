package ApiController;

import CriteriosAsociacion.*;
import Entidades.Egreso;
import Entidades.Ingreso;
import Repositorio.Repositorio;
import Repositorio.DaoMemoria;

import java.util.*;

public class AsociadorEgresoIngreso implements Asociador {

    List<CondicionAsociacion> condicionesObligatorias = new ArrayList<>();
    Map<Integer, CriterioAsociacion> criteriosAsociacion = new HashMap<Integer, CriterioAsociacion>();
    Repositorio repoEgresos = new Repositorio<Egreso>(new DaoMemoria<Egreso>());
    Repositorio repoIngresos = new Repositorio<Ingreso>(new DaoMemoria<Ingreso>());;

    public AsociadorEgresoIngreso(){
        this.condicionesObligatorias.add(new PeriodoDeAceptabilidad());
        this.condicionesObligatorias.add(new MontoMenorOIgual());
        this.condicionesObligatorias.add(new EgresoSinAsociar());
        this.criteriosAsociacion.put(1,new OrdenValorPrimerIngreso());
        this.criteriosAsociacion.put(2,new OrdenValorPrimerEgreso());
        this.criteriosAsociacion.put(3,new OrdenFecha());
        this.criteriosAsociacion.put(4,new AsociacionMix());
    }

    public AsociadorEgresoIngreso(HashMap<Integer, CriterioAsociacion> criterios){
        this.condicionesObligatorias.add(new PeriodoDeAceptabilidad());
        this.condicionesObligatorias.add(new MontoMenorOIgual());
        this.condicionesObligatorias.add(new EgresoSinAsociar());
        this.criteriosAsociacion = criterios;
    }

    public List<CondicionAsociacion> getCondicionesObligatorias() {
        return condicionesObligatorias;
    }

    public void setCondicionesObligatorias(List<CondicionAsociacion> nuevasCondiciones){
        this.condicionesObligatorias = nuevasCondiciones;
    }

    public void agregarCondicionDeAsociacion (CondicionAsociacion nuevaCondicion){
        this.condicionesObligatorias.add(nuevaCondicion);
    }

    public HashMap<Integer, CriterioAsociacion> getCriteriosAsociacion(){
        return (HashMap<Integer, CriterioAsociacion>) criteriosAsociacion;
    }

    public void setCriteriosAsociacion(Map<Integer, CriterioAsociacion> criterios){
        this.criteriosAsociacion = criterios;
    }

    public void guardarEgreso(Egreso unEgreso){
        repoEgresos.agregar(unEgreso);
    }

    public void guardarIngreso(Ingreso unIngreso){
        repoIngresos.agregar(unIngreso);
    }

    public List<Egreso> asociarPor(Integer idCriterio){

        List<Ingreso> ingresos = repoIngresos.buscarTodos();
        List<Egreso> egresos = repoEgresos.buscarTodos();
        CriterioAsociacion unCriterio = criteriosAsociacion.get(idCriterio);

        if(unCriterio != null){
            return unCriterio.asociar(egresos, ingresos, this.condicionesObligatorias);
        } else {
            return null;
        }

    }

    public void limpiarHistorial() {
        this.repoEgresos.eliminarTodos();
        this.repoIngresos.eliminarTodos();
    }
}
