package cz.mendelu.ja.leteckaposta.country;

import org.junit.jupiter.api.Test
import spock.lang.Specification;

class CountryServiceTest extends Specification {

    private CountryService countryService;

    @Test
    void deleteAllCountries() {
        given:
        def a = 1

        when:
        def r = a+1

        then:
        r == 2
    }

    @Test
    void fetchCountries() {
        countryService.fetchCountries("ABC");
    }
}