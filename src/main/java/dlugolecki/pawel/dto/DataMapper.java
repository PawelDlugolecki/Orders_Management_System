package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.exception.MyExceptionDTO;
import dlugolecki.pawel.model.entities.*;

import java.util.HashSet;

public class DataMapper {

    public CategoryDTO fromCategoryToCategoryDto(Category category) {

        return category == null ? null : CategoryDTO.builder()
                .name(category.getName())
                .build();
    }

    public Category fromCategoryDtoToCategory(CategoryDTO categoryDTO) {

        return categoryDTO == null ? null : Category.builder()
                .name(categoryDTO.getName())
                .products(new HashSet<>())
                .build();
    }

    public CountryDTO fromCountryToCountryDto(Country country) {

        return country == null ? null : CountryDTO.builder()
                .name(country.getName())
                .build();
    }

    public Country fromCountryDtoToCountry(CountryDTO countryDTO) {

        return countryDTO == null ? null : Country.builder()
                .name(countryDTO.getName())
                .customers(new HashSet<>())
                .producers(new HashSet<>())
                .shops(new HashSet<>())
                .build();
    }

    public CustomerDTO fromCustomerToCustomerDto(Customer customer) {

        return customer == null ? null : CustomerDTO.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .country(customer.getCountry() == null ? null : fromCountryToCountryDto(customer.getCountry()))
                .build();
    }

    public Customer fromCustomerDtoToCustomer(CustomerDTO customerDTO) {

        return customerDTO == null ? null : Customer.builder()
                .name(customerDTO.getName())
                .surname(customerDTO.getSurname())
                .age(customerDTO.getAge())
                .country(customerDTO.getCountryDTO() == null ? null : fromCountryDtoToCountry(customerDTO.getCountryDTO()))
                .customerOrders(new HashSet<>())
                .build();
    }

    public CustomerOrderDTO fromCustomerOrderToCustomerOrderDto(CustomerOrder customerOrder) {

        return customerOrder == null ? null : CustomerOrderDTO.builder()
                .quantity(customerOrder.getQuantity())
                .time(customerOrder.getTime())
                .discount(customerOrder.getDiscount())
                .customerDTO(customerOrder.getCustomer() == null ? null : fromCustomerToCustomerDto(customerOrder.getCustomer()))
                .productDTO(customerOrder.getProduct() == null ? null : fromProductToProductDto(customerOrder.getProduct()))
                .build();
    }

    public CustomerOrder fromCustomerOrderDtoToCustomerOrder(CustomerOrderDTO customerOrderDTO) {

        return customerOrderDTO == null ? null : CustomerOrder.builder()
                .discount(customerOrderDTO.getDiscount())
                .quantity(customerOrderDTO.getQuantity())
                .time(customerOrderDTO.getTime())
                .customer(customerOrderDTO.getCustomerDTO() == null ? null : fromCustomerDtoToCustomer(customerOrderDTO.getCustomerDTO()))
                .product(customerOrderDTO.getCustomerDTO() == null ? null : fromProductDtoToProduct(customerOrderDTO.getProductDTO()))
                .payments(new HashSet<>())
                .build();
    }

    public ProducerDTO fromProducerToProducerDto(Producer producer) {

        return producer == null ? null : ProducerDTO.builder()
                .name(producer.getName())
                .countryDTO(producer.getCountry() == null ? null : fromCountryToCountryDto(producer.getCountry()))
                .tradeDTO(producer.getTrade() == null ? null : fromTradeToTradeDto(producer.getTrade()))
                .build();
    }

    public Producer fromProducerDtoToProducer(ProducerDTO producerDTO) {

        return producerDTO == null ? null : Producer.builder()
                .name(producerDTO.getName())
                .country(producerDTO.getCountryDTO() == null ? null : fromCountryDtoToCountry(producerDTO.getCountryDTO()))
                .trade(producerDTO.getTradeDTO() == null ? null : fromTradeDtoToTrade(producerDTO.getTradeDTO()))
                .products(new HashSet<>())
                .build();
    }

    public ProductDTO fromProductToProductDto(Product product) {

        return product == null ? null : ProductDTO.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .categoryDTO(product.getCategory() == null ? null : fromCategoryToCategoryDto(product.getCategory()))
                .producerDTO(product.getProducer() == null ? null : fromProducerToProducerDto(product.getProducer()))
                .build();
    }

    public Product fromProductDtoToProduct(ProductDTO productDTO) {

        return productDTO == null ? null : Product.builder()
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .quantity(productDTO.getQuantity())
                .category(productDTO.getCategoryDTO() == null ? null : fromCategoryDtoToCategory(productDTO.getCategoryDTO()))
                .producer(productDTO.getProducerDTO() == null ? null : fromProducerDtoToProducer(productDTO.getProducerDTO()))
                .customerOrders(new HashSet<>())
                .guaranteeComponents(new HashSet<>())
                .build();
    }

    public ShopDTO fromShopToShopDto(Shop shop) {

        return shop == null ? null : ShopDTO.builder()
                .name(shop.getName())
                .countryDTO(shop.getCountry() == null ? null : fromCountryToCountryDto(shop.getCountry()))
                .build();
    }

    public Shop fromShopDtoToShop(ShopDTO shopDTO) {

        return shopDTO == null ? null : Shop.builder()
                .name(shopDTO.getName())
                .country(shopDTO.getCountryDTO() == null ? null : fromCountryDtoToCountry(shopDTO.getCountryDTO()))
                .stocks(new HashSet<>())
                .build();
    }

    public StockDTO fromStockToStockDto(Stock stock) {

        return stock == null ? null : StockDTO.builder()
                .quantity(stock.getQuantity())
                .shopDTO(stock.getShop() == null ? null : fromShopToShopDto(stock.getShop()))
                .build();
    }

    public Stock fromStockDtoToStock(StockDTO stockDTO) {

        return stockDTO == null ? null : Stock.builder()
                .quantity(stockDTO.getQuantity())
                .shop(stockDTO.getShopDTO() == null ? null : fromShopDtoToShop(stockDTO.getShopDTO()))
                .product(stockDTO.getProductDTO() == null ? null : fromProductDtoToProduct(stockDTO.getProductDTO()))
                .build();
    }

    public TradeDTO fromTradeToTradeDto(Trade trade) {

        return trade == null ? null : TradeDTO.builder()
                .name(trade.getName())
                .build();
    }

    public Trade fromTradeDtoToTrade(TradeDTO tradeDTO) {

        return tradeDTO == null ? null : Trade.builder()
                .name(tradeDTO.getName())
                .producers(new HashSet<>())
                .build();
    }

    public MyExceptionDTO fromMyExceptionToMyExceptionDto(MyException myException) {

        return myException == null ? null : MyExceptionDTO.builder()
                .className(myException.getClassName())
                .errorInfo(myException.getErrorInfo())
                .time(myException.getTime())
                .build();
    }

    public MyException fromMyExceptionDtoToMyException(MyExceptionDTO myExceptionDTO) {

        return myExceptionDTO == null ? null : MyException.builder()
                .className(myExceptionDTO.getClassName())
                .errorInfo(myExceptionDTO.getErrorInfo())
                .time(myExceptionDTO.getTime())
                .build();
    }
}