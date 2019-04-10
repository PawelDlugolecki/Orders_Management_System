package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.*;
import dlugolecki.pawel.model.enums.EGuarantee;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProductCreator {

    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();

    private static String name;
    private static String categoryName;
    private static String producerName;
    private static String tradeNameFromProducer;
    private static String countryNameFromProducer;
    private static BigDecimal price;
    private static int quantity;


    public static ProductDTO create() {
        return ProductDTO.builder()
                .name(askForProductName())
                .categoryDTO(CategoryDTO
                        .builder()
                        .name(askForCategory())
                        .build())
                .producerDTO(ProducerDTO
                        .builder()
                        .name(askForProducer())
                        .countryDTO(CountryDTO.builder().name(askForCountryNameFromProducer()).build())
                        .tradeDTO(TradeDTO.builder().name(askForTradeNameFromProducer()).build())
                        .build())
                .price(askForPrice())
                .quantity(askForQuantity())
                .build();
    }

    private static String askForProductName() {
        helpers.showAvailableProduct();
        return name = dataService.getString("Enter Product name: ");
    }

    private static String askForCategory() {
        helpers.showAvailableCategory();
        return categoryName = dataService.getString("Enter Category name");
    }

    private static String askForProducer() {
        helpers.showAvailableProducer();
        return producerName = dataService.getString("Enter Producer name");
    }

    private static String askForCountryNameFromProducer() {
        helpers.showAvailableProducer();
        return countryNameFromProducer = dataService.getString("Enter Country name from Producer");
    }

    private static String askForTradeNameFromProducer() {
        helpers.showAvailableProducer();
        return tradeNameFromProducer = dataService.getString("Enter Trade name from Producer");
    }

    private static BigDecimal askForPrice() {
        return price = dataService.getBigDecimal("Enter price");
    }

    private static int askForQuantity() {
        return quantity = dataService.getInt("Enter quantity");
    }
}
