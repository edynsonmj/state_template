package co.unicauca.template.restaurant.server.infra;
import co.unicauca.resturant.commons.infra.JsonError;
import co.unicauca.resturant.commons.infra.Protocol;
import co.unicauca.resturant.commons.infra.Utilities;
import co.unicauca.serversocket.serversockettemplate.infra.ServerSocketTemplate;
import co.unicauca.template.restaurant.common.Plato;
import co.unicauca.template.restaurant.server.access.Factory;
import co.unicauca.template.restaurant.server.access.ICustomerRepository;
import co.unicauca.template.restaurant.server.service.CustomerService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servidor Socket que está escuchando permanentemente solicitudes de los
 * clientes. Cada solicitud la atiende en un hilo de ejecución
 *
 * @author Libardo, Julio
 */
public class RestaurantServerSocket extends ServerSocketTemplate {

    /**
     * Servicio de clientes
     */
    private CustomerService service;

    /**
     * Constructor
     */
    public RestaurantServerSocket() {
    }
    
     /**
     * Inicialización
     * @return este mismo objeto
     */
    @Override
    protected ServerSocketTemplate init(){
        PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
         // Se hace la inyección de dependencia
        ICustomerRepository repository = Factory.getInstance().getRepository();
        this.setService(new CustomerService(repository));
        return this;
    }
    
    /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"customer","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
    @Override
    protected void processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);

        switch (protocolRequest.getResource()) {
            case "client":
                if (protocolRequest.getAction().equals("post")) {
                    processPostPlato(protocolRequest);
                }
                break;
        }

    }


    /**
     * Genera un ErrorJson de cliente no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Cliente no encontrado. Cédula no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }

    /**
     * @return the service
     */
    public CustomerService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(CustomerService service) {
        this.service = service;
    }

    private void processPostPlato(Protocol protocolRequest) {
        Plato p = new Plato();
    }
   
}
