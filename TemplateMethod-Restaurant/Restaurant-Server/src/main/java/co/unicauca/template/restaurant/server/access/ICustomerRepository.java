package co.unicauca.template.restaurant.server.access;

import co.unicauca.template.restaurant.common.Plato;


/**
 * Interface del respositorio de clientes
 * @author Libardo Pantoja
 */
public interface ICustomerRepository {
    String adicionarPlato(Plato p);
    
}
