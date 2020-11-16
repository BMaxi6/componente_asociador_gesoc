package ApiController;

import Entidades.Egreso;

import java.util.List;

public interface Asociador {
    public <T> List<T> asociarPor(Integer idCriterio);
}
