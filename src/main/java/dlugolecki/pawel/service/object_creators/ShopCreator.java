package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.CountryDTO;
import dlugolecki.pawel.dto.ShopDTO;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

public class ShopCreator {


    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();

    private static String name;
    private static String countryName;

    public static ShopDTO create() {
        return ShopDTO.builder()
                .name(askForName())
                .countryDTO(CountryDTO.builder()
                        .name(askForCountry())
                        .build())
                .build();
    }

    private static String askForName() {
        return name = dataService.getString("Enter Shop name: ");
    }

    private static String askForCountry() {
        helpers.showAvailableCountry();
        return countryName = dataService.getString("Enter Country name");
    }


}
