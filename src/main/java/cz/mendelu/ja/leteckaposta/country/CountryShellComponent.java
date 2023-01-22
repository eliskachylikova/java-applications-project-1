package cz.mendelu.ja.leteckaposta.country;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;

import org.springframework.shell.standard.ShellComponent;


@ShellComponent
@RequiredArgsConstructor
class CountryShellComponent {

    private final CountryService countryService;

    @ShellMethod("Delete all countires.")
    void deleteCountries() {
        countryService.deleteAllCountries();
    }

    @ShellMethod("Insert countires from region")
    void insertCountries(
        @ShellOption(value = { "-r" }, defaultValue = "europe") String region
    ) {
        countryService.fetchCountries(region, null);
    }

    @ShellMethod("Insert boarders from region")
    void insertBoarders(
            @ShellOption(value = { "-r" }, defaultValue = "europe") String region
    ) {
        countryService.fetchBoarders(region, null);
    }

}
