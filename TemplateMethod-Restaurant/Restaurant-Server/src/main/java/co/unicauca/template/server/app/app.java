/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.template.server.app;

import co.unicauca.template.restaurant.server.infra.RestaurantServerSocket;

/**
 *
 * @author EdynsonMJ
 */
public class app {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RestaurantServerSocket server = new RestaurantServerSocket();
        server.startServer();
    }
    
}
