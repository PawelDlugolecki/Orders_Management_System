package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.*;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

public class StockCreator {

    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();
    private static ProductCreator productCreator = new ProductCreator();

    private static String productName;
    private static String categoryName;
    private static String producerName;
    private static String shopName;
    private static String countryName;
    private static int productId;
    private static int shopId;
    private static int quantity;

    public static StockDTO create() {
        return StockDTO.builder()
                .productDTO(ProductDTO
                        .builder()
                        .name(askForProductName())
                        .categoryDTO(CategoryDTO
                                .builder()
                                .name(askForCategoryName())
                                .build())
                        .producerDTO(ProducerDTO
                                .builder()
                                .name(askForProducerName())
                                .build())
                        .build())
                .shopDTO(ShopDTO
                        .builder()
                        .name(askForShopName())
                        .countryDTO(CountryDTO
                                .builder()
                                .name(askForCountryName())
                                .build())
                        .build())
                .quantity(askForQuantity())
                .build();

    }

    public static String askForProductName() {
        helpers.showAvailableProduct();
        return producerName = dataService.getString("Enter Product name: ");
    }

    public static String askForCategoryName() {
        helpers.showAvailableProduct();
        return categoryName = dataService.getString("Enter Category name");
    }

    public static String askForProducerName() {
        helpers.showAvailableProduct();
        return producerName = dataService.getString("Enter Producer name");
    }

    public static String askForShopName() {
        helpers.showAvailableShop();
        return shopName = dataService.getString("Enter Shop name");
    }

    public static String askForCountryName() {
        helpers.showAvailableShop();
        return countryName = dataService.getString("Enter Country name");
    }

    public static int askForQuantity() {
        return quantity = dataService.getInt("Enter quantity");
    }
}
