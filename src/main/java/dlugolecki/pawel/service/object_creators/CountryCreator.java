package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.CountryDTO;
import dlugolecki.pawel.service.UserDataService;

public class CountryCreator {

    private static UserDataService dataService = new UserDataService();

    private static String name;

    public static CountryDTO create() {
        return CountryDTO
                .builder()
                .name(askForName())
                .build();
    }

    private static String askForName() {
        return name = dataService.getString("Enter country name: ");
    }
}
