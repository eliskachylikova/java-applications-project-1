package cz.mendelu.ja.leteckaposta.country;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
interface CountryRepository extends PagingAndSortingRepository<Country, String> {

    @Query("select c.borders from Country as c where c.cca3 = :cca3")
    Iterable<Country> getCountriesByBorders(
            @Param("cca3") String cca3);
}
