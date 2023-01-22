package cz.mendelu.ja.leteckaposta.country;

import cz.mendelu.ja.leteckaposta.utils.Response;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/country")
@RequiredArgsConstructor
@Api(tags = "Country")
class CountryRestController {

    private final CountryRepository countryRepository;

    @GetMapping("/{cca3:[A-Z0-9]{3}}")
    @ResponseStatus(HttpStatus.OK)
    Response<Country> getByCca3(
            @PathVariable String cca3
    ) {
        return countryRepository
                .findById(cca3)
                .map(Response::of)
                .get();
    }

    @GetMapping("/{cca3:[A-Z0-9]{3}}/neighbour")
    @ResponseStatus(HttpStatus.OK)
    Iterable<Country> getCountryNeighbour(
            @PathVariable String cca3
    ) {
        return countryRepository
                .getCountriesByBorders(cca3);

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Page<Country> getAllCountry(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return countryRepository
                .findAll(PageRequest.of(page, size));
    }



}
