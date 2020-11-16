package ApiController;

import CriteriosAsociacion.*;
import Entidades.Egreso;
import Entidades.Ingreso;
import Entidades.VinculacionEgresoIngreso;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class ApiController {

    AsociadorEgresoIngreso asociadorDeEgresosIngresos = new AsociadorEgresoIngreso();

    // --------------------------- Lógica --------------------------- //

    @GetMapping(path = "/asociaciones" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> asociaciarEgresosAIngresos(@RequestParam Integer idCriterio) {
        List<Egreso> egresosAsociados = asociadorDeEgresosIngresos.asociarPor(idCriterio);
        asociadorDeEgresosIngresos.limpiarHistorial();
        List<VinculacionEgresoIngreso> vinculados = new ArrayList<VinculacionEgresoIngreso>();
        for (Egreso unEgreso: egresosAsociados) {
            VinculacionEgresoIngreso vinculado = new VinculacionEgresoIngreso();
            vinculado.setIdEgreso(unEgreso);
            if(unEgreso.getIngreso() != null) {
                vinculado.setIdIngreso(unEgreso.getIngreso());
            } else {
                vinculado.setIdIngreso(-1);
            }
            vinculados.add(vinculado);
        }
        return new ResponseEntity<Object>(vinculados, HttpStatus.OK);
    }

    @GetMapping(path = "/criterios" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> entregarCriterios(){
        Map<Integer, String> criterios = new HashMap<Integer, String>();
        for(int i = 1; i <=  asociadorDeEgresosIngresos.getCriteriosAsociacion().size(); i++){
            criterios.put(i, asociadorDeEgresosIngresos.getCriteriosAsociacion().get(i).getNombre());
        }
        return new ResponseEntity<Object>(criterios, HttpStatus.OK);
    }
    
    @PostMapping(path = "/egresos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> enviarEgresos(@RequestBody Egreso[] listaEgresos){
        if(listaEgresos.length > 0){
            for(Egreso unEgreso: listaEgresos){
                asociadorDeEgresosIngresos.guardarEgreso(unEgreso);
            }
            return new ResponseEntity<Object>("Se guardaron los egresos",HttpStatus.OK);
        }
        return new ResponseEntity<Object>("No ingresaste egresos",HttpStatus.OK);
    }

    @PostMapping(path = "/ingresos", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> enviarIngresos(@RequestBody Ingreso[] listaIngresos){
        if(listaIngresos.length > 0){
            for(Ingreso unIngreso: listaIngresos){
                asociadorDeEgresosIngresos.guardarIngreso(unIngreso);
            }
            return new ResponseEntity<Object>("Se guardaron los ingresos",HttpStatus.OK);
        }
        return new ResponseEntity<Object>("No ingresaste egresos",HttpStatus.OK);
    }

    // --------------------------- Malas Rutas --------------------------- //

    @GetMapping(path = "*")
    public ResponseEntity<Object> mensajeDeError(){
        return new ResponseEntity<Object>("La página solicitada no existe", HttpStatus.CONFLICT);
    }

    @GetMapping(path = "*/*")
    public ResponseEntity<Object> otroMensajeDeError(){
        return new ResponseEntity<Object>("La página solicitada no existe", HttpStatus.CONFLICT);
    }

    // --------------------------- Seguridad --------------------------- //

}
