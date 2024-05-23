package com.robinfood.app.usecases.changepaidstate;

public interface IChangePaidStateUseCase {

    /**
     * Change the paid status of  an order
     * @param idOrder id of an Orden
     * @return an Order State
     */
    Boolean changePaid (Long idOrder);
}
