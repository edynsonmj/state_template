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
    private static List<Plato> platos;

    public CustomerRepositoryImplArrays() {
        if (platos == null){
            platos = new ArrayList();
        }
    }

    @Override
    public String adicionarPlato(Plato p) {
        platos.add(p);
        return ""+platos.size();
    }

}
