package co.unicauca.travelagency.client.access;

import co.unicauca.template.restaurant.common.Plato;


/**
 * Interface que define los servicios de persistencia de Clientes de la agencia
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public interface ICustomerAccess {

    String adicionarPlato(Plato p)throws Exception;
}
