package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrdenFecha implements CriterioAsociacion {

    public String nombre = "Ordenar por Fecha";

    public String getNombre() {
        return nombre;
    }

    @Override
    public List<Egreso> asociar(List<Egreso> egresos, List<Ingreso> ingresos, List<CondicionAsociacion> condiciones) {
        egresos.sort(Comparator.comparing(Egreso::getFecha));
        ingresos.sort(Comparator.comparing(Ingreso::getFecha));
        egresos.forEach(unEgreso -> unEgreso.asociarIngresos(ingresos, condiciones));
        List<Egreso> egresosOrdenados = new ArrayList<Egreso>();
        for(int i=0; i<egresos.size(); i++) {
            egresosOrdenados.add(egresos.get(i));
        }
        return egresosOrdenados;
    }
}
