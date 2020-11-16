package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

import java.util.ArrayList;
import java.util.List;

public class AsociacionMix implements CriterioAsociacion {

    public String nombre = "Varios criterios";

    public String getNombre() {
        return nombre;
    }

    List<CriterioAsociacion> criteriosAAplicar = new ArrayList<>();

    public void setCriterios(List<CriterioAsociacion> criterios){
        this.criteriosAAplicar = criterios;
    }

    public void agregarCriterio(CriterioAsociacion unCriterio){
        criteriosAAplicar.add(unCriterio);
    }

    public void eliminarCriterio(CriterioAsociacion unCriterio){
        criteriosAAplicar.remove(unCriterio);
    }

    @Override
    public List<Egreso> asociar(List<Egreso> egresos, List<Ingreso> ingresos, List<CondicionAsociacion> condiciones) {
        List<Egreso> egresosResultantes = new ArrayList<>();

        for(CriterioAsociacion unCriterio: criteriosAAplicar){
            egresosResultantes = unCriterio.asociar(egresos, ingresos, condiciones);
        }

        return egresosResultantes;
    }
}
