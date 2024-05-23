package org.example;

import org.example.repository.routes.RoutesRepository;

public class Clasnamemain {

    public static void main(String[] args) {
        RoutesRepository routesRepository = new RoutesRepository();
        routesRepository.searchRouteByFlowIdAndChannelId(2L , 1L);
    }
}
