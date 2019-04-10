package dlugolecki.pawel.service.object_creators;

import dlugolecki.pawel.dto.*;

import dlugolecki.pawel.repository.implement.CountryRepositoryImpl;
import dlugolecki.pawel.repository.interfaces.CountryRepository;
import dlugolecki.pawel.service.ServiceHelpers;
import dlugolecki.pawel.service.UserDataService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Customer_OrderCreator {

    private static UserDataService dataService = new UserDataService();
    private static ServiceHelpers helpers = new ServiceHelpers();
    private static Scanner sc = new Scanner(System.in);

    private static CountryRepository countryRepository = new CountryRepositoryImpl();

    private static String name;
    private static String surname;
    private static int age;
    private static String countryName;

    private static String productName;
    private static String producerName;
    private static String categoryName;
    private static String countryProducerName;
    private static String tradeProducerName;

    public static CustomerOrderDTO create() {
        return CustomerOrderDTO.builder()
                .customerDTO(CustomerDTO
                        .builder()
                        .name(askForName())
                        .surname(askForSurname())
                        .country(CountryDTO
                                .builder()
                                .name(askForCountry())
                                .build())
                        .build())
                .productDTO(ProductDTO
                        .builder()
                        .name(askForProductName())
                        .categoryDTO(CategoryDTO
                                .builder()
                                .name(askForCategory())
                                .build())
                        .producerDTO(ProducerDTO
                                .builder()
                                .name(askForProducer())
                                .countryDTO(CountryDTO
                                        .builder()
                                        .name(askForCountryNameFromProducer())
                                        .build())
                                .tradeDTO(TradeDTO
                                        .builder()
                                        .name(askForTradeNameFromProducer())
                                        .build())
                                .build())
                        .build())
                .time(LocalDateTime.parse(askForTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .quantity(askForQuantity())
                .discount(askForDiscount())
                .build();
    }

    private static String askForName() {
        helpers.showAvailableCustomer();
        return name = dataService.getString("Enter Customer name: ");
    }

    private static String askForSurname() {
        helpers.showAvailableCustomer();
        return surname = dataService.getString("Enter Customer surname: ");
    }

    private static String askForCountry() {
        helpers.showAvailableCustomer();
        return countryName = dataService.getString("Enter Country name");
    }

    private static BigDecimal askForDiscount() {
        return dataService.getBigDecimal("Enter discount");
    }

    private static int askForQuantity() {
        return dataService.getInt("Enter quantity");
    }

    private static String askForTime() {
        System.out.print("Enter the time in format /year-month-day/: ");
        String userInput = sc.nextLine();
        return userInput + " 00:00";
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
        return countryProducerName = dataService.getString("Enter Country name from Producer");
    }

    private static String askForTradeNameFromProducer() {
        helpers.showAvailableProducer();
        return tradeProducerName = dataService.getString("Enter Trade name from Producer");
    }
}
