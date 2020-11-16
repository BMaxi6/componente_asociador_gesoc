package Repositorio;

import java.util.ArrayList;
import java.util.List;

public class DaoMemoria<T> implements Dao<T> {
    private ArrayList<EntidadPersistente> persistidos = new ArrayList<EntidadPersistente>();

    @Override
    public void agregar(Object objeto){
       persistidos.add((EntidadPersistente)objeto);
    }

    @Override
    public T buscar(int id){
        return (T) this.persistidos.stream().filter(elemento -> elemento.getId() == id).findFirst().get();
    }

    @Override
    public void eliminar(Object objeto) {
        this.persistidos.remove(objeto);
    }

    @Override
    public void modificar(Object objeto) {
        EntidadPersistente objetoSimilar = (EntidadPersistente) persistidos.stream().filter(elemento -> elemento.getId()==((EntidadPersistente)objeto).getId()).findFirst().get();
        this.persistidos.set(this.persistidos.indexOf(objetoSimilar),(EntidadPersistente)objeto);
    }

    @Override
    public List<T> buscarTodos() {
        return (List<T>)this.persistidos;
    }

    @Override
    public void eliminarTodos(){
        this.persistidos.clear();
    }

    public DaoMemoria(ArrayList<EntidadPersistente> lista) {
        this.persistidos = lista;
    }

    public DaoMemoria(){ }
}
