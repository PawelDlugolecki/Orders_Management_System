package dlugolecki.pawel.service;

import dlugolecki.pawel.exception.MyExceptionDTO;
import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.dto.*;
import dlugolecki.pawel.model.entities.*;
import dlugolecki.pawel.model.enums.EGuarantee;
import dlugolecki.pawel.model.enums.EPayment;
import dlugolecki.pawel.repository.implement.*;
import dlugolecki.pawel.repository.interfaces.*;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataServiceImpl implements DataService {

    private UserDataService dataService = new UserDataService();
    private DataMapper dataMapper = new DataMapper();
    private ServiceHelpers helpers = new ServiceHelpers();

    private CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private CustomerOrderRepository customerOrderRepository = new CustomerOrderImpl();
    private PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private ExceptionRepository exceptionRepository = new MyExceptionRepositoryImpl();
    private GuaranteesRepository guaranteesRepository = new GuaranteesRepositoryImpl();

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        try {

            Category category = dataMapper
                    .fromCategoryDtoToCategory(categoryDTO);

            if (helpers.doesCategoryExistInDb(category.getName())) {
                throw new MyException("CATEGORY", "Category already exist in database");

            }
            categoryRepository.add(category);
            dataService.getString("Category : " + category.getName() + " added");

        } catch (MyException e) {
            throw new MyException("CATEGORY", "Error during adding category ");
        }

    }

    @Override
    public void addCountry(CountryDTO countryDTO) {
        try {

            Country country = dataMapper
                    .fromCountryDtoToCountry(countryDTO);

            if (helpers.doesCountryExistInDb(country.getName())) {
                throw new MyException("COUNTRY", "Country already exist in database");
            }
            countryRepository.add(country);
            dataService.getString(country.getName() + " added");

        } catch (MyException e) {
            throw new MyException("COUNTRY", "Error during adding country");
        }
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        try {

            if (helpers.doesCustomerExistInDb(
                    customerDTO.getName(),
                    customerDTO.getSurname(),
                    customerDTO.getCountryDTO().getName())) {
                throw new MyException("CUSTOMER", "Duplicate customer");
            }

            if (!helpers.doesCountryExistInDb(customerDTO.getCountryDTO().getName())) {
                throw new MyException("CUSTOMER", "Given country does not exist in database");
            }
            Country country = countryRepository
                    .findOneByName(customerDTO.getCountryDTO().getName())
                    .orElseThrow(NullPointerException::new);

            Customer customer = dataMapper.fromCustomerDtoToCustomer(customerDTO);
            customer.setName(customerDTO.getName());
            customer.setSurname(customerDTO.getSurname());
            customer.setAge(customerDTO.getAge());
            customer.setCountry(country);

            customerRepository.add(customer);

        } catch (MyException e) {
            throw new MyException("CUSTOMER", "Error during adding customer");
        }
    }

    @Override
    public void addTrade(TradeDTO tradeDTO) {
        try {

            Trade trade = dataMapper
                    .fromTradeDtoToTrade(tradeDTO);

            if (helpers.doesTradeExistInDb(trade.getName())) {
                throw new MyException("TRADE", "Trade already exist in database");
            }
            tradeRepository.add(trade);
            dataService.getString(trade.getName() + " added");

        } catch (MyException e) {
            throw new MyException("TRADE", "Error during adding trade");
        }
    }

    @Override
    public void addShop(ShopDTO shopDTO) {
        try {

            if (helpers.doesShopExistInDb(
                    shopDTO.getName(),
                    shopDTO.getCountryDTO().getName())) {
                throw new MyException("SHOP", "Duplicate shop");
            }

            if (!helpers.doesCountryExistInDb(shopDTO.getCountryDTO().getName())) {
                throw new MyException("SHOP", "Given country does not exist in database");
            }
            Country country = countryRepository
                    .findOneByName(shopDTO.getCountryDTO().getName())
                    .orElseThrow(NullPointerException::new);

            Shop shop = dataMapper.fromShopDtoToShop(shopDTO);
            shop.setName(shopDTO.getName());
            shop.setCountry(country);

            shopRepository.add(shop);

        } catch (MyException e) {
            throw new MyException("SHOP", "Error during adding shop");
        }
    }

    @Override
    public void addProducer(ProducerDTO producerDTO) {
        try {

            if (helpers.doesProducerExistInDb(
                    producerDTO.getName(),
                    producerDTO.getCountryDTO().getName(),
                    producerDTO.getTradeDTO().getName())) {
                throw new MyException("PRODUCER", "Duplicate producer");
            }

            if (!helpers.doesCountryExistInDb(producerDTO.getCountryDTO().getName())) {
                throw new MyException("PRODUCER", "Given producer does not exist in database");
            }
            Country country = countryRepository
                    .findOneByName(producerDTO.getCountryDTO().getName())
                    .orElseThrow(NullPointerException::new);

            if (!helpers.doesTradeExistInDb(producerDTO.getTradeDTO().getName())) {
                throw new MyException("PRODUCER", "Given trade does not exist in database");
            }
            Trade trade = tradeRepository
                    .findOneByName(producerDTO.getTradeDTO().getName())
                    .orElseThrow(NullPointerException::new);

            Producer producer = dataMapper.fromProducerDtoToProducer(producerDTO);
            producer.setName(producerDTO.getName());
            producer.setCountry(country);
            producer.setTrade(trade);

            producerRepository.add(producer);

        } catch (MyException e) {
            throw new MyException("PRODUCER", "Error during adding producer");
        }
    }

    @Override
    public Set<EGuarantee> addGuarantees() {

        Set<EGuarantee> eGuaranteeSet = new HashSet<>();
        String guarantess;

        do {
            helpers.showGuarantees();
            guarantess = dataService.getString("Enter guarantees");
            eGuaranteeSet.add(EGuarantee.valueOf(guarantess));

        } while (eGuaranteeSet.size() < 2);
        return eGuaranteeSet;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        try {

            if (!helpers.doesProducerExistInDb(
                    productDTO.getProducerDTO().getName(),
                    productDTO.getProducerDTO().getCountryDTO().getName(),
                    productDTO.getProducerDTO().getTradeDTO().getName())) {
                throw new MyException("PRODUCT", "Given producer does not exist in database");
            }
            Producer producer = producerRepository
                    .findGivenProducer(
                            productDTO.getProducerDTO().getName(),
                            productDTO.getProducerDTO().getCountryDTO().getName(),
                            productDTO.getProducerDTO().getTradeDTO().getName())
                    .orElseThrow(NullPointerException::new);

            if (!helpers.doesCategoryExistInDb(productDTO.getCategoryDTO().getName())) {
                throw new MyException("PRODUCT", "Given category does not exist in database");
            }
            Category category = categoryRepository
                    .findOneByName(productDTO.getCategoryDTO().getName())
                    .orElseThrow(NullPointerException::new);


            Product product = dataMapper.fromProductDtoToProduct(productDTO);
            product.setName(product.getName());
            product.setProducer(producer);
            product.setCategory(category);
            product.setGuaranteeComponents(addGuarantees());
            productRepository.add(product);

        } catch (MyException e) {
            throw new MyException("PRODUCT", "Error during adding product");
        }
    }

    @Override
    public void addProductToStock(StockDTO stockDTO) {
        try {

            Stock stock = dataMapper.fromStockDtoToStock(stockDTO);

            if (!helpers.doesProductExistInDb(
                    stockDTO.getProductDTO().getName(),
                    stockDTO.getProductDTO().getCategoryDTO().getName(),
                    stockDTO.getProductDTO().getProducerDTO().getName())) {
                throw new MyException("STOCK", "Given product does not exist");
            }
            Product product = productRepository
                    .findGivenProduct(
                            stockDTO.getProductDTO().getName(),
                            stockDTO.getProductDTO().getCategoryDTO().getName(),
                            stockDTO.getProductDTO().getProducerDTO().getName())
                    .orElseThrow(NullPointerException::new);
            stock.setProduct(product);

            if (!helpers.doesShopExistInDb(
                    stockDTO.getShopDTO().getName(),
                    stockDTO.getShopDTO().getCountryDTO().getName())) {
                throw new MyException("STOCK", "Given shop does not exist");
            }
            Shop shop = shopRepository
                    .findGivenShop(
                            stockDTO.getShopDTO().getName(),
                            stockDTO.getShopDTO().getCountryDTO().getName())
                    .orElseThrow(NullPointerException::new);
            stock.setShop(shop);

            if (helpers.doesStockExist(
                    product.getName(),
                    product.getCategory().getName(),
                    product.getProducer().getName(),
                    shop.getName(),
                    shop.getCountry().getName())) {
                Stock stock1 = stockRepository
                        .findOneStock(
                                product.getName(),
                                product.getCategory().getName(),
                                product.getProducer().getName(),
                                shop.getName(),
                                shop.getCountry().getName())
                        .orElseThrow(NullPointerException::new);
                stock1.setQuantity(stock1.getQuantity() + stock.getQuantity());
                stockRepository.add(stock1);

            } else {
                stockRepository.add(stock);
            }

        } catch (MyException e) {
            throw new MyException("STOCK", "Error during adding stock");
        }
    }

    @Override
    public void addCustomer_Order(CustomerOrderDTO customerOrderDTO) {
        try {

            CustomerOrder customerOrder = dataMapper.fromCustomerOrderDtoToCustomerOrder(customerOrderDTO);

            if (!helpers.doesCustomerExistInDb(
                    customerOrderDTO.getCustomerDTO().getName(),
                    customerOrderDTO.getCustomerDTO().getSurname(),
                    customerOrderDTO.getCustomerDTO().getCountryDTO().getName())) {
                throw new MyException("CUSTOMER_ORDER", "Given customer doest not exist in database");
            }
            Customer customer = customerRepository.findGivenCustomer(
                    customerOrderDTO.getCustomerDTO().getName(),
                    customerOrderDTO.getCustomerDTO().getSurname(),
                    customerOrderDTO.getCustomerDTO().getCountryDTO().getName())
                    .orElseThrow(NullPointerException::new);

            Product product = productRepository.findGivenProduct(
                    customerOrderDTO.getProductDTO().getName(),
                    customerOrderDTO.getProductDTO().getCategoryDTO().getName(),
                    customerOrderDTO.getProductDTO().getProducerDTO().getName())
                    .orElseThrow(NullPointerException::new);

            customerOrder.setProduct(product);
            customerOrder.setCustomer(customer);
            customerOrder.setPayments(addPayment());

            customerOrderRepository.add(customerOrder);

        } catch (MyException e) {
            throw new MyException("CUSTOMER_ORDER", "Error during adding order");
        }
    }

    @Override
    public Set<EPayment> addPayment() {

        Set<EPayment> ePaymentsSet = new HashSet<>();
        helpers.showPayments();
        String payments = dataService.getString("Enter guarantees");

        ePaymentsSet.add(EPayment.valueOf(payments));
        return ePaymentsSet;
    }

    @Override
    public void findOneCategory(Long id) {
        try {

            if (!helpers.doesCategoryExistInDb(id)) {
                throw new MyException("CATEGORY", "Category with id does not exist");
            }

            Optional<Category> categoryOp = categoryRepository.findOneById(id);
            System.out.println(categoryOp);

        } catch (MyException e) {
            throw new MyException("CATEGORY", "Error during find one");
        }
    }

    @Override
    public void findOneCountry(Long id) {
        try {

            if (!helpers.doesCountryExistInDb(id)) {
                throw new MyException("COUNTRY", "Country with id does not exist");
            }
            Optional<Country> countryOp = countryRepository.findOneById(id);
            System.out.println(countryOp);

        } catch (MyException e) {
            throw new MyException("COUNTRY", "Error during find one");
        }
    }

    @Override
    public void findOneCustomer(Long id) {
        try {

            if (!helpers.doesCustomerExistInDb(id)) {
                throw new MyException("CUSTOMER", "Customer with id does not exist");
            }
            Optional<Customer> customerOp = customerRepository.findOneById(id);
            System.out.println(customerOp);

        } catch (MyException e) {
            throw new MyException("CUSTOMER", "Error during find one");
        }
    }

    @Override
    public void findOneTrade(Long id) {
        try {

            if (!helpers.doesTradeExistInDb(id)) {
                throw new MyException("TRADE", "Trade with id does not exist");
            }
            Optional<Trade> tradeOp = tradeRepository.findOneById(id);
            System.out.println(tradeOp);

        } catch (MyException e) {
            throw new MyException("TRADE", "Error during find one");
        }
    }

    @Override
    public void findOneShop(Long id) {
        try {

            if (!helpers.doesShopExistInDb(id)) {
                throw new MyException("SHOP", "Shop with id does not exist");
            }
            Optional<Shop> shopOp = shopRepository.findOneById(id);
            System.out.println(shopOp);

        } catch (MyException e) {
            throw new MyException("SHOP", "Error during find one");
        }
    }

    @Override
    public void findOneProducer(Long id) {
        try {

            if (!helpers.doesProducerExistInDb(id)) {
                throw new MyException("PRODUCER", "Producer with id does not exist");
            }
            Optional<Producer> producerOp = producerRepository.findOneById(id);
            System.out.println(producerOp);

        } catch (MyException e) {
            throw new MyException("PRODUCER", "Error during find one");
        }
    }

    @Override
    public void findOneProduct(Long id) {
        try {

            if (!helpers.doesProductExistInDb(id)) {
                throw new MyException("PRODUCT", "Product with id does not exist");
            }
            Optional<Product> productOp = productRepository.findOneById(id);
            System.out.println(productOp);

        } catch (MyException e) {
            throw new MyException("PRODUCT", "Error during find one");
        }
    }

    @Override
    public void findOneStock(Long id) {
        try {

            if (!helpers.doesStockExistInDb(id)) {
                throw new MyException("STOCK", "Stock with id does not exist");
            }
            Optional<Stock> stockOp = stockRepository.findOneById(id);
            System.out.println(stockOp);

        } catch (MyException e) {
            throw new MyException("STOCK", "Error during find one");
        }
    }

    @Override
    public void findOneCustomer_Order(Long id) {
        try {

            if (!helpers.doesCustomerOrderExist(id)) {
                throw new MyException("CUSTOMER_ORDER", "Customer_order with id does not exist");
            }
            Optional<CustomerOrder> customerOrderOp = customerOrderRepository.findOneById(id);
            System.out.println(customerOrderOp);

        } catch (MyException e) {
            throw new MyException("CUSTOMER_ORDER", "Error during find one");
        }
    }

    @Override
    public void deleteOneCategory(Long id) {
        try {

            if (!helpers.doesCategoryExistInDb(id)) {
                throw new MyException("CATEGORY", "Category with id does not exist");
            }
            Optional<Category> categoryOp = categoryRepository
                    .findOneById(id);
            categoryRepository.delete(id);
            System.out.println("CATEGORY WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("CATEGORY", "Error during delete one category");
        }
    }

    @Override
    public void deleteOneCountry(Long id) {
        try {

            if (!helpers.doesCountryExistInDb(id)) {
                throw new MyException("COUNTRY", "Country with id does not exist");
            }
            Optional<Country> countryOp = countryRepository
                    .findOneById(id);
            countryRepository.delete(id);
            System.out.println("COUNTRY WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("COUNTRY", "Error during delete one country");
        }
    }

    @Override
    public void deleteOneCustomer(Long id) {
        try {

            if (!helpers.doesCustomerExistInDb(id)) {
                throw new MyException("CUSTOMER", "Customer with id does not exist");
            }
            Optional<Customer> customerOp = customerRepository
                    .findOneById(id);
            customerRepository.delete(id);
            System.out.println("CUSTOMER WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("CUSTOMER", "Error during delete one customer");
        }
    }

    @Override
    public void deleteOneTrade(Long id) {
        try {

            if (!helpers.doesTradeExistInDb(id)) {
                throw new MyException("TRADE", "Trade with id does not exist");
            }
            Optional<Trade> tradeOp = tradeRepository
                    .findOneById(id);
            tradeRepository.delete(id);
            System.out.println("TRADE WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("TRADE", "Error during delete one trade");
        }
    }

    @Override
    public void deleteOneShop(Long id) {
        try {

            if (!helpers.doesShopExistInDb(id)) {
                throw new MyException("SHOP", "Shop with id does not exist");
            }
            Optional<Shop> shopOp = shopRepository
                    .findOneById(id);
            shopRepository.delete(id);
            System.out.println("SHOP WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("SHOP", "Error during delete one shop");
        }
    }

    @Override
    public void deleteOneProducer(Long id) {
        try {

            if (!helpers.doesProducerExistInDb(id)) {
                throw new MyException("PRODUCER", "Producer with id does not exist");
            }
            Optional<Producer> producerOp = producerRepository
                    .findOneById(id);
            producerRepository.delete(id);
            System.out.println("PRODUCER WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("PRODUCER", "Error during delete one producer");
        }
    }

    @Override
    public void deleteOneProduct(Long id) {
        try {

            if (!helpers.doesProductExistInDb(id)) {
                throw new MyException("PRODUCT", "Product with id does not exist");
            }
            Optional<Product> productOp = productRepository
                    .findOneById(id);
            productRepository.delete(id);
            System.out.println("PRODUCT WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("PRODUCT", "Error during delete one product");
        }
    }

    @Override
    public void deleteOneStock(Long id) {
        try {

            if (!helpers.doesStockExistInDb(id)) {
                throw new MyException("STOCK", "Stock with id does not exist");
            }
            Optional<Stock> stockOp = stockRepository
                    .findOneById(id);
            stockRepository.delete(id);
            System.out.println("STOCK WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("STOCK", "Error during delete one producer");
        }
    }

    @Override
    public void deleteOneCustomer_Order(Long id) {
        try {

            if (!helpers.doesCustomerOrderExist(id)) {
                throw new MyException("CUSTOMER_ORDER", "Customer order with id does not exist");
            }
            Optional<CustomerOrder> customerOrderOp = customerOrderRepository
                    .findOneById(id);
            customerOrderRepository.delete(id);
            System.out.println("CUSTOMER_ORDER WITH ID " + id + " HAS BEEN DELETE");

        } catch (MyException e) {
            throw new MyException("CUSTOMER_ORDER", "Error during delete one customer_order");
        }
    }

    @Override
    public void findAllCategory() {
        try {

            System.out.println("ALL CATEGORIES: ");
            List<Category> categories = categoryRepository.findAll();

            if (categories == null || categories.isEmpty()) {
                throw new MyException("CATEGORY", "Category is empty");
            }
            categories.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("CATEGORY", "Error during show all category");
        }
    }

    @Override
    public void findAllCountry() {
        try {

            System.out.println("ALL COUNTRIES: ");
            List<Country> countries = countryRepository.findAll();

            if (countries == null || countries.isEmpty()) {
                throw new MyException("COUNTRY", "Country is empty");
            }
            countries.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("CATEGORY", "Error during show all country");
        }
    }

    @Override
    public void findAllCustomer() {
        try {

            System.out.println("ALL CUSTOMERS: ");
            List<Customer> customers = customerRepository.findAll();

            if (customers == null || customers.isEmpty()) {
                throw new MyException("CUSTOMER", "Customer is empty");
            }
            customers.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("CUSTOMER", "Error during show all customer");
        }
    }

    @Override
    public void findAllTrade() {
        try {

            System.out.println("ALL TRADES: ");
            List<Trade> trades = tradeRepository.findAll();

            if (trades == null || trades.isEmpty()) {
                throw new MyException("TRADE", "Trade is empty");
            }
            trades.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("TRADE", "Error during show all trades");
        }
    }

    @Override
    public void findAllShop() {
        try {

            System.out.println("ALL SHOPS: ");
            List<Shop> shops = shopRepository.findAll();

            if (shops == null || shops.isEmpty()) {
                throw new MyException("SHOP", "Shop is empty");
            }
            shops.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("SHOP", "Error during show all shops");
        }
    }

    @Override
    public void findAllProducer() {
        try {

            System.out.println("ALL PRODUCERS: ");
            List<Producer> producers = producerRepository.findAll();

            if (producers == null || producers.isEmpty()) {
                throw new MyException("PRODUCER", "Producer is empty");
            }
            producers.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("PRODUCER", "Error during show all producers");
        }
    }

    @Override
    public void findAllProduct() {
        try {

            System.out.println("ALL PRODUCTS: ");
            List<Product> products = productRepository.findAll();

            if (products == null || products.isEmpty()) {
                throw new MyException("PRODUCT", "Product is empty");
            }
            products.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("PRODUCT", "Error during show all products");
        }
    }

    @Override
    public void findAllStock() {
        try {

            System.out.println("ALL STOCKS: ");
            List<Stock> stocks = stockRepository.findAll();

            if (stocks == null || stocks.isEmpty()) {
                throw new MyException("STOCK", "Stock is empty");
            }
            stocks.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("STOCK", "Error during show all stocks");
        }
    }

    @Override
    public void findAllCustomer_Orders() {
        try {

            System.out.println("ALL CUSTOMER_ORDERS: ");
            List<CustomerOrder> customerOrderOp = customerOrderRepository.findAll();

            if (customerOrderOp == null || customerOrderOp.isEmpty()) {
                throw new MyException("CUSTOMER_ORDER", "Customer order is empty");
            }
            customerOrderOp.forEach(s -> System.out.println(s));

        } catch (MyException e) {
            throw new MyException("CUSTOMER_ORDER", "Error during show all customer orders");
        }
    }


    @Override
    public void deleteAllCategories() {

        categoryRepository.deleteAll();
        System.out.println("SERVICE: ALL CATEGORIES HAS BEEN DELETE");
    }

    @Override
    public void deleteAllCountries() {

        countryRepository.deleteAll();
        System.out.println("SERVICE: ALL COUNTRIES HAS BEEN DELETE");
    }

    @Override
    public void deleteAllCustomers() {

        customerRepository.deleteAll();
        System.out.println("SERVICE: ALL CUSTOMERS HAS BEEN DELETE");
    }

    @Override
    public void deleteAllTrades() {

        tradeRepository.deleteAll();
        System.out.println("SERVICE: ALL TRADES HAS BEEN DELETE");
    }

    @Override
    public void deleteAllShops() {

        shopRepository.deleteAll();
        System.out.println("SERVICE: ALL SHOPS HAS BEEN DELETE");
    }

    @Override
    public void deleteAllProducers() {

        producerRepository.deleteAll();
        System.out.println("SERVICE: ALL PRODUCER HAS BEEN DELETE");
    }

    @Override
    public void deleteAllProducts() {

        productRepository.deleteAll();
        System.out.println("SERVICE: ALL PRODUCTS HAS BEEN DELETE");
    }

    @Override
    public void deleteAllStocks() {

        stockRepository.deleteAll();
        System.out.println("SERVICE: ALL STOCKS HAS BEEN DELETE");
    }

    @Override
    public void deleteAllCustomer_Orders() {

        customerOrderRepository.deleteAll();
        System.out.println("SERVICE: ALL CUSTOMER_ORDERS HAS BEEN DELETE");
    }

    @Override
    public void deleteAllPayments() {
        paymentRepository.deleteAll();
    }

    @Override
    public void deleteAllGuaranteeComponents() {
        guaranteesRepository.deleteAll();
    }

    @Override
    public void deleteAllExceptions() {
        exceptionRepository.deleteAll();
    }

    @Override
    public void deleteAllData() {

        deleteAllExceptions();
        deleteAllCustomer_Orders();
        deleteAllStocks();
        deleteAllProducts();
        deleteAllProducers();
        deleteAllGuaranteeComponents();
        deleteAllCategories();
        deleteAllTrades();
        deleteAllShops();
        deleteAllCustomers();
        deleteAllCountries();
        deleteAllPayments();
    }

    @Override
    public void showTableWithMostExceptions() {

        String tablesWithMostExceptions = exceptionRepository
                .findAll()
                .stream()
                .map(exception -> dataMapper.fromMyExceptionToMyExceptionDto(exception))
                .collect(Collectors.groupingBy(MyExceptionDTO::getClassName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(() -> new NoResultException("NO EXCEPTIONS IN DATABASE"))
                .getKey();

        dataService.getString("THE MOST POPULAR EXCEPTIONS - NAME: " + tablesWithMostExceptions);
    }

    @Override
    public void showDateWithMostExceptions() {

        String dateWithMostExceptions = exceptionRepository
                .findAll()
                .stream()
                .map(excepion -> dataMapper.fromMyExceptionToMyExceptionDto(excepion))
                .map(myExceptionDTO -> myExceptionDTO.getTime())
                .map(myExcetptionDate -> LocalDate.parse(myExcetptionDate.toString().substring(0, 10)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(() -> new NoResultException("NO EXCEPTIONS IN DATABASE"))
                .getKey()
                .toString();

        dataService.getString("THE MOST POPULAR EXCEPTIONS - DATE: " + dateWithMostExceptions);
    }

    @Override
    public void showCommonExceptionMessage() {

        String mostCommonExceptionMessage = exceptionRepository
                .findAll()
                .stream()
                .map(exception -> dataMapper.fromMyExceptionToMyExceptionDto(exception))
                .collect(Collectors.groupingBy(MyExceptionDTO::getErrorInfo, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(() -> new NoResultException("NO EXCEPTIONS IN DATABASE"))
                .getKey();

        dataService.getString("THE MOST POPULAR COMMON EXCEPTIONS - INFORMATION: " + mostCommonExceptionMessage);
    }

    @Override
    public void showMostExpensiveProduct() {

        Optional<Product> productOp = productRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Product::getPrice, Comparator.reverseOrder()))
                .findFirst();

        try {

            if (productOp.isPresent()) {
                System.out.println("The most expensive product: " +
                        productOp.get().getName() + " - " + productOp.get().getPrice() + " PLN");
            } else {
                throw new NoResultException("No product in database");
            }

        } catch (MyException e) {
            throw new MyException("SERVICE", "Show most expensive product");
        }
    }
}
