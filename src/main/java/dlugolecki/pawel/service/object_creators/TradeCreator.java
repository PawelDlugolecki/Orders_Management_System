package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.TradeDTO;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

public class TradeCreator {

    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();

    private static String name;

    public static TradeDTO create() {
        return TradeDTO
                .builder()
                .name(askForName())
                .build();
    }

    private static String askForName() {
        helpers.showAvailableTrade();
        System.out.println("- - - - - - - - - - ");
        return name = dataService.getString("Enter Trade name: ");
    }
}
