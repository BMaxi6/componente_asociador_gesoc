package ApiController;

import CriteriosAsociacion.*;
import Entidades.Egreso;
import Entidades.Ingreso;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ComponenteAsociadorApplicationTest {

    Egreso egresoDe200 = new Egreso(1, 1,200.00, LocalDate.of(2016, 7, 10));
    Egreso egresoDe300 = new Egreso(2, 2,300.00, LocalDate.of(2016, 7, 20));
    Egreso egresoDe400 = new Egreso(3, 3,400.00, LocalDate.of(2016, 7, 15));
    Egreso egresoDe500 = new Egreso(4, 4,500.00, LocalDate.of(2016, 7, 11));

    Ingreso ingresoDe1000 = new Ingreso(1000.00, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 8, 1), 1000.00);
    Ingreso ingresoDe400 = new Ingreso(400.00, LocalDate.of(2016, 6, 25), LocalDate.of(2016, 8, 15), 400.00);

    Map<Integer, CriterioAsociacion> criterios = new HashMap<>();
    AsociadorEgresoIngreso asociadorDeEgresos = new AsociadorEgresoIngreso();

    @Test
    public void asociacionPorValorPrimerEgreso(){
        CriterioAsociacion ordenPrimerEgreso = new OrdenValorPrimerEgreso();
        criterios.put(1, ordenPrimerEgreso);
        asociadorDeEgresos.setCriteriosAsociacion(criterios);
        asociadorDeEgresos.guardarEgreso(egresoDe200);
        asociadorDeEgresos.guardarEgreso(egresoDe300);
        asociadorDeEgresos.guardarEgreso(egresoDe400);
        asociadorDeEgresos.guardarEgreso(egresoDe500);
        asociadorDeEgresos.guardarIngreso(ingresoDe1000);
        asociadorDeEgresos.guardarIngreso(ingresoDe400);

        List<Egreso> egresosAsociados = asociadorDeEgresos.asociarPor(1);

        boolean primerAsociacion = egresosAsociados.get(0).getIngreso().valor() == 400.00;
        boolean segundaAsociacion = egresosAsociados.get(2).getIngreso().valor() == 1000.00;
        assertTrue(primerAsociacion && segundaAsociacion);

    }

    @Test
    public void asociacionPorValorPrimerIngreso(){
        CriterioAsociacion ordenPrimerIngreso = new OrdenValorPrimerIngreso();

        criterios.put(2, ordenPrimerIngreso);
        asociadorDeEgresos.setCriteriosAsociacion(criterios);
        asociadorDeEgresos.guardarEgreso(egresoDe200);
        asociadorDeEgresos.guardarEgreso(egresoDe300);
        asociadorDeEgresos.guardarEgreso(egresoDe400);
        asociadorDeEgresos.guardarEgreso(egresoDe500);
        asociadorDeEgresos.guardarIngreso(ingresoDe1000);
        asociadorDeEgresos.guardarIngreso(ingresoDe400);

        List<Egreso> egresosAsociados = asociadorDeEgresos.asociarPor(2);
        boolean primerAsociacion = egresosAsociados.get(0).getIngreso().valor() == 400.00;
        boolean segundaAsociacion = egresosAsociados.get(2).getIngreso().valor() == 1000.00;
        assertTrue(primerAsociacion && segundaAsociacion);
    }

    @Test
    public void fallaIntentoDeAsociacionConCriterioInexistente(){
        CriterioAsociacion ordenPrimerIngreso = new OrdenValorPrimerIngreso();
        criterios.put(2, ordenPrimerIngreso);
        asociadorDeEgresos.setCriteriosAsociacion(criterios);
        asociadorDeEgresos.guardarEgreso(egresoDe200);
        asociadorDeEgresos.guardarIngreso(ingresoDe1000);

        assertNull(asociadorDeEgresos.asociarPor(1));
    }

    @Test
    public void asociacionPorFecha(){
        CriterioAsociacion ordenFecha = new OrdenFecha();
        CriterioAsociacion ordenPrimerEgreso = new OrdenValorPrimerEgreso();
        Egreso egresoDeJulio = new Egreso(1, 1,200.00, LocalDate.of(2016, 7, 10));
        Egreso egresoDeAgosto = new Egreso(2, 2,100.00, LocalDate.of(2016, 8, 2));
        Egreso otroEgresoDeJulio = new Egreso(3, 3,200.00, LocalDate.of(2016, 7, 5));
        Ingreso ingresoDe500 = new Ingreso(500.00, LocalDate.of(2016, 6, 20), LocalDate.of(2016, 8, 10), 500.00);

        criterios.put(2, ordenPrimerEgreso);
        criterios.put(3, ordenFecha);
        asociadorDeEgresos.setCriteriosAsociacion(criterios);
        asociadorDeEgresos.guardarEgreso(egresoDeJulio);
        asociadorDeEgresos.guardarEgreso(otroEgresoDeJulio);
        asociadorDeEgresos.guardarEgreso(egresoDeAgosto);
        asociadorDeEgresos.guardarIngreso(ingresoDe400);
        asociadorDeEgresos.guardarIngreso(ingresoDe500);

        List<Egreso> egresosAsociados = asociadorDeEgresos.asociarPor(3);

        assertTrue(egresosAsociados.stream().allMatch(egreso -> egreso.getIngreso().valor() == 500.00));
    }

    @Test
    public void asociacionPorMixCriterios(){
        AsociacionMix mixCriterios = new AsociacionMix();
        CriterioAsociacion ordenFecha = new OrdenFecha();
        CriterioAsociacion ordenPrimerEgreso = new OrdenValorPrimerEgreso();
        Egreso egresoDe100 = new Egreso(2, 2,100.00, LocalDate.of(2016, 8, 2));
        Ingreso ingresoDe600 = new Ingreso(600.00, LocalDate.of(2016, 6, 27), LocalDate.of(2016, 8, 10), 600.00);

        mixCriterios.agregarCriterio(ordenFecha);
        mixCriterios.agregarCriterio(ordenPrimerEgreso);

        criterios.put(4, mixCriterios);
        asociadorDeEgresos.setCriteriosAsociacion(criterios);
        asociadorDeEgresos.guardarEgreso(egresoDe100);
        asociadorDeEgresos.guardarEgreso(egresoDe200);
        asociadorDeEgresos.guardarEgreso(egresoDe300);
        asociadorDeEgresos.guardarEgreso(egresoDe400);
        asociadorDeEgresos.guardarEgreso(egresoDe500);
        asociadorDeEgresos.guardarIngreso(ingresoDe600);
        asociadorDeEgresos.guardarIngreso(ingresoDe400);

        List<Egreso> egresosAsociados = asociadorDeEgresos.asociarPor(4);
        boolean primerAsociacion = egresosAsociados.get(1).getIngreso().valor() == 400.00;
        boolean segundaAsociacion = egresosAsociados.get(4).getIngreso().valor() == 600.00;

        assertTrue(primerAsociacion && segundaAsociacion);
    }

}