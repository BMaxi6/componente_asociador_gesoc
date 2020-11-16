package Repositorio;


import java.util.List;

public interface Dao<T> {
    public void agregar(Object objeto);
    public T buscar(int id);
    public void eliminar(Object objeto);
    public void modificar(Object objeto);
    public List<T> buscarTodos();
    public void eliminarTodos();
}
