package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenValorPrimerEgreso implements CriterioAsociacion {

    public String nombre = "Ordenar por Valores de Egresos";

    public String getNombre() {
        return nombre;
    }

    public OrdenValorPrimerEgreso(){

    }

    @Override
    public List<Egreso> asociar(List<Egreso> egresos, List<Ingreso> ingresos, List<CondicionAsociacion> condiciones) {
        List<Egreso> egresosOrdenados = egresos.stream().sorted(Comparator.comparing(unEgreso -> unEgreso.valor())).collect(Collectors.toList());
        List<Ingreso> ingresosOrdenados = ingresos.stream().sorted(Comparator.comparing(unIngreso -> unIngreso.valor())).collect(Collectors.toList());
        ingresosOrdenados.forEach(unIngreso -> unIngreso.asociarEgresos(egresosOrdenados, condiciones));

        return egresosOrdenados;
    }
}
