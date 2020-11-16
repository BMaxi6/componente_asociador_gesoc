package CriteriosAsociacion;

import Entidades.Egreso;
import Entidades.Ingreso;

public interface CondicionAsociacion {

    public boolean validar(Ingreso unIngreso, Egreso unEgreso);

}


