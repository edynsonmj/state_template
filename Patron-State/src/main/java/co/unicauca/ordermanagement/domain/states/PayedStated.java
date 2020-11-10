
package co.unicauca.ordermanagement.domain.states;

import co.unicauca.ordermanagement.domain.Order;

/**
 *
 * @author Camilo Gonzalez
 */
public class PayedStated extends OrderState{

    public PayedStated(Order order) {
        super(order);
    }

    @Override
    public String getStateDescription() {
        return "Pagada";
    }
    
    @Override
    public OrderState orderSendOut(String parcelNumber) {
        return new SendState(getOrder());
    }
    }

