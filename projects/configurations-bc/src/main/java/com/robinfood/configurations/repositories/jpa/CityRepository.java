package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.models.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CityRepository extends SoftDeleteRepository<City, Long> {

    boolean existsByIdAndDeletedAtIsNull(Long id);

    @Query(
        "SELECT city FROM City city " +
            "INNER JOIN FETCH city.state state " +
            "INNER JOIN state.country country " +
            "WHERE country.id = :countryId"
    )
    @Transactional(readOnly = true)
    List<City> findCitiesByCountry(Long countryId);
    @Transactional(readOnly = true)
    @Query(
        "SELECT city.timezone from City city where "
            + "city.deletedAt is null AND city.id "
            + "in(SELECT store.city.id from Store store where "
            + "store.company.id = :companyCountryId "
            + "AND store.deletedAt is null "
            + "GROUP by store.city.id ) "
        + "GROUP by city.timezone")
    List<String> listTimeZonesByCompanyCountryId(@Param("companyCountryId") Long companyCountryId);
}
