package com.robinfood.configurations.samples;

import com.robinfood.configurations.models.City;
import com.robinfood.configurations.models.State;

import java.util.List;

public class CitySample {

    public static City getCity1() {
        return City.builder()
            .id(1L)
            .name("City 1")
            .timezone("UTC")
            .state(getStateDefault())
            .build();
    }

    public static City getCity2() {
        return City.builder()
            .id(1L)
            .name("City 2")
            .timezone("UTC")
            .state(getStateDefault())
            .build();
    }

    public static State getStateDefault() {
        return State.builder()
            .id(1L)
            .name("Default")
            .build();
    }

    public static List<City> getCities() {
        return List.of(
            getCity1(),
            getCity2()
        );
    }
}
