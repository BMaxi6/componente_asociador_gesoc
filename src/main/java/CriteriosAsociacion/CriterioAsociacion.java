package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

import java.util.List;

public interface CriterioAsociacion {
    public List<Egreso> asociar(List<Egreso> egresos, List<Ingreso> ingresos, List<CondicionAsociacion> condiciones);
    public String getNombre();
}
