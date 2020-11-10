package co.unicauca.template.restaurante.client.service;

import co.unicauca.template.restaurant.common.Plato;
import co.unicauca.travelagency.client.access.ICustomerAccess;

/**
 * Es una fachada para comunicar la presentación con el
 * dominio
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public class ClientService {

    private final ICustomerAccess service;

    /**
     * Constructor privado que evita que otros objetos instancien
     * @param service implementacion de tipo ICustomerService
     */
    public ClientService(ICustomerAccess service) {
        this.service = service;
    }

    public String adiccionarPlato(Plato p) throws Exception{
        return service.adicionarPlato(p);
    }

}
