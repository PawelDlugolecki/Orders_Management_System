package dlugolecki.pawel.service;

import dlugolecki.pawel.dto.*;
import dlugolecki.pawel.model.enums.EGuarantee;
import dlugolecki.pawel.model.enums.EPayment;

import java.util.Set;

public interface DataService {

    /*************************************************************************/
    // ADDING

    /*************************************************************************/
    void addCategory(CategoryDTO categoryDTO);

    void addCountry(CountryDTO countryDTO);

    void addCustomer(CustomerDTO customerDTO);

    void addTrade(TradeDTO tradeDTO);

    void addShop(ShopDTO shopDTO);

    void addProducer(ProducerDTO producerDTO);

    void addProduct(ProductDTO productDTO);

    Set<EGuarantee> addGuarantees();

    Set<EPayment> addPayment();

    void addProductToStock(StockDTO stockDTO);

    void addCustomer_Order(CustomerOrderDTO customerOrderDTO);

    /*************************************************************************/
    // FIND ONE

    /*************************************************************************/
    void findOneCategory(Long id);

    void findOneCountry(Long id);

    void findOneCustomer(Long id);

    void findOneTrade(Long id);

    void findOneShop(Long id);

    void findOneProducer(Long id);

    void findOneProduct(Long id);

    void findOneStock(Long id);

    void findOneCustomer_Order(Long id);

    /*************************************************************************/
    // DELETE ONE

    /*************************************************************************/
    void deleteOneCategory(Long id);

    void deleteOneCountry(Long id);

    void deleteOneCustomer(Long id);

    void deleteOneTrade(Long id);

    void deleteOneShop(Long id);

    void deleteOneProducer(Long id);

    void deleteOneProduct(Long id);

    void deleteOneStock(Long id);

    void deleteOneCustomer_Order(Long id);

    /*************************************************************************/
    // FIND ALL

    /*************************************************************************/
    void findAllCategory();

    void findAllCountry();

    void findAllCustomer();

    void findAllTrade();

    void findAllShop();

    void findAllProducer();

    void findAllProduct();

    void findAllStock();

    void findAllCustomer_Orders();

    /*************************************************************************/
    // DELETE ONE TABLE

    /*************************************************************************/
    void deleteAllCategories();

    void deleteAllCountries();

    void deleteAllCustomers();

    void deleteAllTrades();

    void deleteAllShops();

    void deleteAllProducers();

    void deleteAllProducts();

    void deleteAllStocks();

    void deleteAllCustomer_Orders();

    void deleteAllPayments();

    void deleteAllGuaranteeComponents();

    void deleteAllExceptions();

    /*************************************************************************/
    //DELETE ALL DATABASE

    /*************************************************************************/
    void deleteAllData();

    /*************************************************************************/
    //EXCEPTION STATISTICS

    /*************************************************************************/
    void showTableWithMostExceptions();

    void showDateWithMostExceptions();

    void showCommonExceptionMessage();

    /*************************************************************************/
    // STATISTICS

    /*************************************************************************/
    void showMostExpensiveProduct();
}
