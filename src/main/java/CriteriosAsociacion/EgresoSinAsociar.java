package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

public class EgresoSinAsociar implements CondicionAsociacion{
    @Override
    public boolean validar(Ingreso unIngreso, Egreso unEgreso) {
        return unEgreso.disponibleParaAsociar();
    }
}
