package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

public class PeriodoDeAceptabilidad implements CondicionAsociacion {

    @Override
    public boolean validar(Ingreso unIngreso, Egreso unEgreso) {
        return unEgreso.getFecha().isAfter(unIngreso.getFecha()) && unEgreso.getFecha().isBefore(unIngreso.getFechaDeAceptacion());
    }
}
