package Repositorio;

import java.util.List;

public class Repositorio<T> {
    private Dao<T> dao;

    public Repositorio(Dao<T> unDao) {
        this.dao = unDao;
    }
    public void agregar(Object objeto){
        dao.agregar(objeto);
    }
    public T buscar(int id){
        return dao.buscar(id);
    }

    public void cambiarDao(Dao<T> nuevoDao){
        dao = nuevoDao;
    }
    public void eliminar(Object objeto){
        dao.eliminar(objeto);
    }
    public void modificar(Object objeto){
        dao.modificar(objeto);
    }
    public List<T> buscarTodos(){
        return this.dao.buscarTodos();
    }
    public void eliminarTodos(){ this.dao.eliminarTodos(); }

}
