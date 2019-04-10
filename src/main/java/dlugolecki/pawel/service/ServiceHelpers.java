package dlugolecki.pawel.service;

import dlugolecki.pawel.dto.DataMapper;
import dlugolecki.pawel.model.entities.*;
import dlugolecki.pawel.repository.implement.*;
import dlugolecki.pawel.repository.interfaces.*;

import java.util.Comparator;
import java.util.Scanner;

public class ServiceHelpers {

    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private CustomerOrderRepository customerOrderRepository = new CustomerOrderImpl();
    private DataMapper dataMapper = new DataMapper();

    private UserDataService dataService = new UserDataService();
    private Scanner sc = new Scanner(System.in);

    public boolean doesCategoryExistInDb(String name) {
        return categoryRepository.findOneByName(name).isPresent();
    }

    public boolean doesCategoryExistInDb(Long id) {
        return categoryRepository.findOneById(id).isPresent();
    }

    public boolean doesCountryExistInDb(String name) {
        return countryRepository.findOneByName(name).isPresent();
    }

    public boolean doesCountryExistInDb(Long id) {
        return countryRepository.findOneById(id).isPresent();
    }

    public boolean doesCustomerExistInDb(String customerName, String customerSurname, String countryName) {
        return customerRepository.findGivenCustomer(customerName, customerSurname, countryName).isPresent();
    }

    public boolean doesCustomerExistInDb(Long id) {
        return customerRepository.findOneById(id).isPresent();
    }

    public boolean doesTradeExistInDb(String name) {
        return tradeRepository.findGivenTrade(name).isPresent();
    }

    public boolean doesTradeExistInDb(Long id) {
        return tradeRepository.findOneById(id).isPresent();
    }

    public boolean doesShopExistInDb(String shopName, String countryName) {
        return shopRepository.findGivenShop(shopName, countryName).isPresent();
    }

    public boolean doesShopExistInDb(Long id) {
        return shopRepository.findOneById(id).isPresent();
    }

    public boolean doesProducerExistInDb(String producerName, String countryName, String tradeName) {
        return producerRepository.findGivenProducer(producerName, countryName, tradeName).isPresent();
    }

    public boolean doesProducerExistInDb(Long id) {
        return producerRepository.findOneById(id).isPresent();
    }

    public boolean doesStockExistInDb(Long productId, Long shopId) {
        return stockRepository.findGivenStock(productId, shopId).isPresent();
    }

    public boolean doesStockExistInDb(Long id) {
        return stockRepository.findOneById(id).isPresent();
    }

    public boolean doesStockExist(String productName, String categoryName, String producerName, String shopName, String countryName) {
        return stockRepository.findOneStock(productName, categoryName, producerName, shopName, countryName).isPresent();
    }

    public boolean doesCustomerOrderExist(Long id) {
        return customerOrderRepository.findOneById(id).isPresent();
    }

    public boolean doesProductExistInDb(String productName, String categoryName, String producerName) {
        return productRepository.findGivenProduct(productName, categoryName, producerName).isPresent();
    }

    public boolean doesProductExistInDb(Long id) {
        return productRepository.findOneById(id).isPresent();
    }

    public void showAvailableCategory() {
        categoryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Category::getName))
                .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));
    }

    public void showAvailableCountry() {
        countryRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Country::getName))
                .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));
    }

    public void showAvailableCustomer() {
        customerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Customer::getSurname))
                .forEach(s -> System.out.println(s.getId() + ": " + s.getName() + " " + s.getSurname() + " " + s.getCountry().getName()));
    }

    public void showAvailableTrade() {
        tradeRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Trade::getName))
                .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));
    }

    public void showAvailableShop() {
        shopRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Shop::getName))
                .forEach(s -> System.out.println(s.getId() + ": " + s.getName() + "  " + s.getCountry().getName()));
    }

    public void showAvailableProducer() {
        producerRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Producer::getName))
                .forEach(s -> System.out.println(s.getId() + " " + s.getName() +
                        ", " + s.getCountry().getName() + " " + s.getTrade().getName()));
    }

    public void showGuarantees() {
        System.out.println("Choose your product`s guarantees\nYou can choose two guarantees per product");
        System.out.println("HELP_DESK");
        System.out.println("MONEY_BACK");
        System.out.println("SERVICE");
        System.out.println("EXCHANGE");
    }

    public void showPayments() {
        System.out.println("Choose the type of payment");
        System.out.println("CASH");
        System.out.println("CARD");
        System.out.println("MONEY_TRANSFER");
    }

    public void showAvailableProduct() {
        productRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getName))
                .forEach(s -> System.out.println(s.getId()
                        + " " + s.getName()
                        + " " + s.getCategory().getName()
                        + " " + s.getProducer().getName()
                        + " " + s.getProducer().getCountry().getName()
                        + " " + s.getProducer().getTrade().getName()));
    }

    public void showAvailableStock() {
        stockRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Stock::getId))
                .forEach(s -> System.out.println(s.getId()
                        + " " + s.getShop().getName()
                        + " " + s.getProduct().getName()
                        + " - " + s.getShop().getCountry().getName()));
    }

    public void showAvailableCustomer_Orders() {
        customerOrderRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(CustomerOrder::getId))
                .forEach(s -> System.out.println(s.getId()
                        + " " + s.getCustomer().getName()
                        + " " + s.getCustomer().getSurname()
                        + " " + s.getCustomer().getAge()
                        + " " + s.getCustomer().getCountry().getName()
                        + " " + s.getProduct().getName()));
    }
}
