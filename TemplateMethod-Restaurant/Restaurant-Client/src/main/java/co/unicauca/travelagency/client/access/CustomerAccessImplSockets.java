package co.unicauca.travelagency.client.access;

import co.unicauca.resturant.commons.infra.JsonError;
import co.unicauca.resturant.commons.infra.Protocol;
import co.unicauca.template.restaurant.cliente.infra.RestauranteSocket;
import co.unicauca.template.restaurant.common.Plato;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de Cliente. Permite hacer el CRUD de clientes solicitando los
 * servicios con la aplicación server. Maneja los errores devueltos
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class CustomerAccessImplSockets implements ICustomerAccess {

    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private RestauranteSocket mySocket;

    public CustomerAccessImplSockets() {
        mySocket = new RestauranteSocket();
    }

    @Override
    public String adicionarPlato(Plato p) throws Exception{
        String jsonResponse = null;
        String requestJson = createPlatoJson(p);
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve la cedula del customer 
                return p.getId()+"";
            }

        }
    }
    private String createPlatoJson(Plato p){
        Protocol protocol = new Protocol();
        protocol.setResource("client");
        protocol.setAction("post");
        protocol.addParameter("id", ""+p.getId());
        protocol.addParameter("nomber", p.getNombre());
        protocol.addParameter("decripcion", p.getDescripcion());
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: " + requestJson);

        return requestJson;
    }
    
    /**
     * Extra los mensajes de la lista de errores
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     *
     * @param idCustomer identificación del cliente
     * @return solicitud de consulta del cliente en formato Json, algo como:
     * {"resource":"customer","action":"get","parameters":[{"name":"id","value":"98000001"}]}
     */
    private String findCustomerRequestJson(String idCustomer) {
        //{"recource":"customer", "action":"get", "parametrers":"[{"name": "id", "value": 9800001"},{}]"}
        Protocol protocol = new Protocol();
        protocol.setResource("customer");
        protocol.setAction("get");
        protocol.addParameter("id", idCustomer);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    

}
