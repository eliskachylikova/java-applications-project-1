package cz.mendelu.ja.leteckaposta.parcel;

import cz.mendelu.ja.leteckaposta.country.Country;
import cz.mendelu.ja.leteckaposta.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParcelRouter {

    private final HashMap<String, ArrayList<String>> countryBorders;

    private CountryRepository countryRepository;

    @Autowired
    public ParcelRouter(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        List<Country> countries = (List<Country>) countryRepository.findAll();
        List<String> countryNames = countries
                .stream()
                .map(Country::getCca3)
                .toList();
        HashMap<String, ArrayList<String>> countryMap = new HashMap<>();

        for (String country : countryNames) {
            List<Country> borderCountries = (List<Country>) countryRepository.getCountriesByBorders(country);
            List<String> borderCountryNames = borderCountries
                    .stream()
                    .map(Country::getCca3)
                    .toList();
            countryMap.put(country, new ArrayList<>(borderCountryNames));
        }
        this.countryBorders = countryMap;
    }

    public ArrayList<String> route(String location, String destination) {
        ArrayList<String> route = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> cameFrom = new HashMap<>();

        queue.add(location);
        cameFrom.put(location, null);

        while (!queue.isEmpty()) {
            String current = queue.remove();

            if (current.equals(destination)) {
                break;
            }

            System.out.println("BORDERS OF CURRENT");
            System.out.println(countryBorders.get(current));

            for (String next : countryBorders.get(current)) {
                if (!cameFrom.containsKey(next)) {
                    cameFrom.put(next, current);
                    queue.add(next);
                }
            }
        }

        if (cameFrom.get(destination) == null) {
            return null;
        }

        String current = destination;
        while (current != null) {
            route.add(0, current);
            current = cameFrom.get(current);
        }

        return route;
    }


}
