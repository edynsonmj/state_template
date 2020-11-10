package co.unicauca.template.restaurant.server.access;

import co.unicauca.template.restaurant.common.Plato;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ICustomerRepository. Utilliza arreglos en memoria
 *
 * @author Libardo Pantoja, Julio Hurtado
 */
public final class CustomerRepositoryImplArrays implements ICustomerRepository {

    /**
     * Array List de clientes
     */
    private static List<Plato> customers;

    public CustomerRepositoryImplArrays() {
        if (customers == null){
            customers = new ArrayList();
        }
        
        if (customers.size() == 0){
            inicializar();
        }
    }

    public void inicializar() {

    }

    

}
