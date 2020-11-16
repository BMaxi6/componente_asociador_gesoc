package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

public class MontoMenorOIgual implements CondicionAsociacion {

    @Override
    public boolean validar(Ingreso unIngreso, Egreso unEgreso) {
        return unEgreso.valor() <= unIngreso.getValorRestante();
    }
}
