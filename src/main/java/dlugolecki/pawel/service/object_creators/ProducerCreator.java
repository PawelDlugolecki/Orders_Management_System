package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.CountryDTO;
import dlugolecki.pawel.dto.ProducerDTO;
import dlugolecki.pawel.dto.TradeDTO;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

public class ProducerCreator {

    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();

    private static String name;
    private static String countryName;
    private static String tradeName;

    public static ProducerDTO create() {
        return ProducerDTO.builder()
                .name(askForName())
                .countryDTO(CountryDTO.builder().name(askForCountry()).build())
                .tradeDTO(TradeDTO.builder().name(askForTrade()).build())
                .build();
    }

    private static String askForName() {
        return name = dataService.getString("Enter Producer name: ");
    }

    private static String askForCountry() {
        helpers.showAvailableCountry();
        return countryName = dataService.getString("Enter Country name");
    }

    private static String askForTrade() {
        helpers.showAvailableTrade();
        return tradeName = dataService.getString("Enter Trade name");
    }
}
