package dlugolecki.pawel.app;

import dlugolecki.pawel.dto.*;
import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.service.*;
import dlugolecki.pawel.service.object_creators.*;

public class MenuService {

    private UserDataService userDataService = new UserDataService();
    private DataServiceImpl dataService = new DataServiceImpl();
    private ErrorService errorService = new ErrorServiceImpl();
    private ServiceHelpers helpers = new ServiceHelpers();


    public void mainMenu() {
        chooseOptionFromMainMenu();
    }

    private void chooseOptionFromMainMenu() {
        try {
            System.out.println("CHOOSE OPERATION FOR:");
            System.out.println("1.  Category ");
            System.out.println("2.  Country");
            System.out.println("3.  Customer");
            System.out.println("4.  Trade");
            System.out.println("5.  Shop");
            System.out.println("6.  Producer");
            System.out.println("7.  Product");
            System.out.println("8.  Stock");
            System.out.println("9.  Customer_Order");
            System.out.println("10. Delete all data ");
            System.out.println("11. Initialize data from txt.files");
            System.out.println("ERROR STATISTICS");
            System.out.println("12. Show table which caused most errors");
            System.out.println("13. Show date on which most errors occurred");
            System.out.println("14. Show most present error message in the errors table");
            System.out.println("STATISTICS");
            System.out.println("15. Show most expensive product");
            System.out.println("16. EXIT");
            int option = userDataService.getInt("Choose option");
            boolean isFinished = true;
            switch (option) {
                case 1:
                    categoryOption(mainOperations());
                    break;
                case 2:
                    countryOption(mainOperations());
                    break;
                case 3:
                    customerOption(mainOperations());
                    break;
                case 4:
                    tradeOption(mainOperations());
                    break;
                case 5:
                    shopOption(mainOperations());
                    break;
                case 6:
                    producerOption(mainOperations());
                    break;
                case 7:
                    productOption(mainOperations());
                    break;
                case 8:
                    stockOption(mainOperations());
                    break;
                case 9:
                    customerOrderOption(mainOperations());
                    break;
                case 10:
                    dataService.deleteAllData();
                    break;
                case 11:
                    break;
                case 12:
                    dataService.showTableWithMostExceptions();
                    break;
                case 13:
                    dataService.showDateWithMostExceptions();
                    break;
                case 14:
                    dataService.showCommonExceptionMessage();
                    break;
                case 15:
                    dataService.showMostExpensiveProduct();
                    break;
                case 16:
                    System.out.println("END OF APPLICATION");
                    isFinished = false;
                    break;
                default:
            }
            isFinished = true;

        } catch (MyException e) {
            System.out.println("\n****************************** EXCEPTIONS *************************************");
            System.out.println(e.getClassName());
            System.out.println(e.getErrorInfo());
            System.out.println(e.getTime());
            System.out.println("*******************************************************************************\n");
            errorService.add(e);
        }
    }

    private int mainOperations() {
        System.out.println("CHOOSE OPERATION:");
        System.out.println("1. ADD");
        System.out.println("2. FIND ONE");
        System.out.println("3. DELETE ONE");
        System.out.println("4. DELETE ALL");
        System.out.println("5. FIND ALL");
        return userDataService.getInt("Choose option");
    }

    private void categoryOption(int option) {
        switch (option) {
            case 1:
                CategoryDTO categoryDTO = CategoryCreator.create();
                dataService.addCategory(categoryDTO);
                break;

            case 2:
                helpers.showAvailableCategory();
                dataService.findOneCategory(userDataService.getLong("Enter category id"));
                break;

            case 3:
                helpers.showAvailableCategory();
                dataService.deleteOneCategory(userDataService.getLong("Enter category id"));
                break;

            case 4:
                dataService.deleteAllCategories();
                break;

            case 5:
                dataService.findAllCategory();
                break;
        }
    }

    private void countryOption(int option) {
        switch (option) {
            case 1:
                CountryDTO countryDTO = CountryCreator.create();
                dataService.addCountry(countryDTO);
                break;

            case 2:
                helpers.showAvailableCountry();
                dataService.findOneCountry(userDataService.getLong("Enter country id"));
                break;

            case 3:
                helpers.showAvailableCountry();
                dataService.deleteOneCountry(userDataService.getLong("Enter country id"));
                break;

            case 4:
                dataService.deleteAllCountries();
                break;

            case 5:
                dataService.findAllCountry();
                break;
        }
    }

    private void customerOption(int option) {
        switch (option) {
            case 1:
                CustomerDTO customerDTO = CustomerCreator.create();
                dataService.addCustomer(customerDTO);
                break;

            case 2:
                helpers.showAvailableCustomer();
                dataService.findOneCustomer(userDataService.getLong("Enter customer id"));
                break;

            case 3:
                helpers.showAvailableCustomer();
                dataService.deleteOneCustomer(userDataService.getLong("Enter customer id"));
                break;

            case 4:
                dataService.deleteAllCustomers();
                break;

            case 5:
                dataService.findAllCustomer();
                break;
        }
    }

    private void tradeOption(int option) {
        switch (option) {

            case 1:
                TradeDTO tradeDTO = TradeCreator.create();
                dataService.addTrade(tradeDTO);
                break;

            case 2:
                helpers.showAvailableTrade();
                dataService.findOneTrade(userDataService.getLong("Enter trade id"));
                break;

            case 3:
                helpers.showAvailableTrade();
                dataService.deleteOneTrade(userDataService.getLong("Enter trade id"));
                break;

            case 4:
                dataService.deleteAllTrades();
                break;

            case 5:
                dataService.findAllTrade();
                break;
        }
    }

    private void shopOption(int option) {
        switch (option) {

            case 1:
                ShopDTO shopDTO = ShopCreator.create();
                dataService.addShop(shopDTO);
                break;

            case 2:
                helpers.showAvailableShop();
                dataService.findOneShop(userDataService.getLong("Enter shop id"));
                break;

            case 3:
                helpers.showAvailableShop();
                dataService.deleteOneShop(userDataService.getLong("Enter shop id"));
                break;

            case 4:
                dataService.deleteAllShops();
                break;

            case 5:
                dataService.findAllShop();
                break;
        }
    }

    private void producerOption(int option) {
        switch (option) {

            case 1:
                ProducerDTO producerDTO = ProducerCreator.create();
                dataService.addProducer(producerDTO);
                break;

            case 2:
                helpers.showAvailableProducer();
                dataService.findOneProducer(userDataService.getLong("Enter producer id"));
                break;

            case 3:
                helpers.showAvailableProducer();
                dataService.deleteOneProducer(userDataService.getLong("Enter producer id"));
                break;

            case 4:
                dataService.deleteAllProducers();
                break;

            case 5:
                dataService.findAllProducer();
                break;
        }
    }

    private void productOption(int option) {
        switch (option) {

            case 1:
                ProductDTO productDTO = ProductCreator.create();
                dataService.addProduct(productDTO);
                break;

            case 2:
                helpers.showAvailableProduct();
                dataService.findOneProduct(userDataService.getLong("Enter product id"));
                break;

            case 3:
                helpers.showAvailableProduct();
                dataService.deleteOneProduct(userDataService.getLong("Enter product id"));
                break;

            case 4:
                dataService.deleteAllProducts();
                break;

            case 5:
                dataService.findAllProduct();
                break;
        }
    }

    private void stockOption(int option) {
        switch (option) {

            case 1:
                StockDTO stockDTO = StockCreator.create();
                dataService.addProductToStock(stockDTO);
                break;

            case 2:
                helpers.showAvailableStock();
                dataService.findOneStock(userDataService.getLong("Enter stock id"));
                break;

            case 3:
                helpers.showAvailableStock();
                dataService.deleteOneStock(userDataService.getLong("Enter stock id"));
                break;

            case 4:
                dataService.deleteAllStocks();
                break;

            case 5:
                dataService.findAllStock();
                break;
        }
    }

    private void customerOrderOption(int option) {
        switch (option) {

            case 1:
                CustomerOrderDTO customerOrderDTO = Customer_OrderCreator.create();
                dataService.addCustomer_Order(customerOrderDTO);
                break;

            case 2:
                helpers.showAvailableCustomer_Orders();
                dataService.findOneCustomer_Order(userDataService.getLong("Enter customer_order id"));
                break;

            case 3:
                helpers.showAvailableCustomer_Orders();
                dataService.deleteOneCustomer_Order(userDataService.getLong("Enter customer_order id"));
                break;

            case 4:
                helpers.showAvailableCustomer_Orders();
                dataService.deleteAllCustomer_Orders();
                break;

            case 5:
                dataService.findAllCustomer_Orders();
                break;
        }
    }
}

