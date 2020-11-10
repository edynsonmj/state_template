package co.unicauca.template.restaurant.server.service;

import co.unicauca.template.restaurant.common.Plato;
import co.unicauca.template.restaurant.server.access.ICustomerRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de clientes. Da acceso a la lógica de negocio
 *
 * @author Libardo, Julio
 */
public class CustomerService {

    /**
     * Repositorio de clientes
     */
    ICustomerRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo ICustomerRepository
     */
    public CustomerService(ICustomerRepository repo) {
        this.repo = repo;
    }

    public String createPlato(Plato p) {
        return repo.adicionarPlato(p);
    }
}
