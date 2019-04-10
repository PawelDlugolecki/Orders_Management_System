package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.CountryDTO;
import dlugolecki.pawel.dto.CustomerDTO;

import dlugolecki.pawel.repository.implement.CountryRepositoryImpl;
import dlugolecki.pawel.repository.interfaces.CountryRepository;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

public class CustomerCreator {

    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();

    private static CountryRepository countryRepository = new CountryRepositoryImpl();

    private static String name;
    private static String surname;
    private static int age;
    private static String countryName;

    public static CustomerDTO create() {
        return CustomerDTO.builder()
                .name(askForName())
                .surname(askForSurname())
                .age(askForAge())
                .country(CountryDTO.builder().name(askForCountry()).build())
                .build();
    }

    private static String askForName() {
        return name = dataService.getString("Enter Customer name: ");
    }

    private static String askForSurname() {
        return surname = dataService.getString("Enter Customer surname: ");
    }

    private static int askForAge() {
        return age = dataService.getInt("Enter Customer age");
    }

    private static String askForCountry() {
        helpers.showAvailableCountry();
        return countryName = dataService.getString("Enter Country name");
    }
}
