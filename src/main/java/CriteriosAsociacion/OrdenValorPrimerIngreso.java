package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenValorPrimerIngreso implements CriterioAsociacion {

    public String nombre = "Ordenar por Valores de Ingresos";

    public String getNombre() {
        return nombre;
    }

    @Override
    public List<Egreso> asociar(List<Egreso> egresos, List<Ingreso> ingresos, List<CondicionAsociacion> condiciones) {
        List<Egreso> egresosOrdenados = egresos.stream().sorted(Comparator.comparing(unEgreso -> unEgreso.valor())).collect(Collectors.toList());
        List<Ingreso> ingresosOrdenados = ingresos.stream().sorted(Comparator.comparing(unIngreso -> unIngreso.valor())).collect(Collectors.toList());

        egresosOrdenados.forEach(unIngreso -> unIngreso.asociarIngresos(ingresosOrdenados, condiciones));
        return egresosOrdenados;
    }
}
