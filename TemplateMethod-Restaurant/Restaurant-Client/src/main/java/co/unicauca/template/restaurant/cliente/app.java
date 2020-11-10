/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.template.restaurant.cliente;

import co.unicauca.template.restaurant.common.Plato;
import co.unicauca.template.restaurante.client.service.ClientService;
import co.unicauca.travelagency.client.access.Factory;
import co.unicauca.travelagency.client.access.ICustomerAccess;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EdynsonMJ
 */
public class app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ICustomerAccess service = Factory.getInstance().getCustomerService();
        // Inyecta la dependencia
        ClientService ser = new ClientService(service);
        try {
            ser.adiccionarPlato(new Plato(1,"nom","jslkfjsljf"));
        } catch (Exception ex) {
            Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
