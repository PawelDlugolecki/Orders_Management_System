package dlugolecki.pawel.dto;

import dlugolecki.pawel.model.enums.EGuarantee;
import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private ProducerDTO producerDTO;
    private StockDTO stockDTO;
    private CategoryDTO categoryDTO;
    private EGuarantee eGuarantee;

    public ProductDTO(ProductDTOBuilder productDTOBuilder) {
        this.id = productDTOBuilder.id;
        this.name = productDTOBuilder.name;
        this.price = productDTOBuilder.price;
        this.quantity = productDTOBuilder.quantity;
        this.producerDTO = productDTOBuilder.producerDTO;
        this.stockDTO = productDTOBuilder.stockDTO;
        this.categoryDTO = productDTOBuilder.categoryDTO;
        this.eGuarantee = productDTOBuilder.eGuarantee;
    }

    public static ProductDTO.ProductDTOBuilder builder() {
        return new ProductDTOBuilder();
    }

    public static class ProductDTOBuilder {
        private Long id;
        private String name;
        private BigDecimal price;
        private int quantity;
        private ProducerDTO producerDTO;
        private StockDTO stockDTO;
        private CategoryDTO categoryDTO;
        private EGuarantee eGuarantee;

        private static final String NAME_REGEX = "[A-Z\\s]+";

        public ProductDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProductDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("PRODUCT", "Product name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                throw new MyException("PRODUCT", "Product builder");
            }
        }

        public ProductDTOBuilder price(BigDecimal price) {
            try {
                if (price.compareTo(BigDecimal.ZERO) < 0) {
                    throw new MyException("PRODUCT", "Product price is not correct");
                }
                this.price = price;
                return this;
            } catch (MyException e) {
                throw new MyException("PRODUCT", "Product builder");
            }
        }

        public ProductDTOBuilder quantity(int quantity) {
            try {
                if (quantity < 0) {
                    throw new MyException("PRODUCT", "Product quantity is not correct");
                }
                this.quantity = quantity;
                return this;
            } catch (Exception e) {
                throw new MyException("PRODUCT", "Product builder");
            }
        }

        public ProductDTOBuilder producerDTO(ProducerDTO producerDTO) {
            this.producerDTO = producerDTO;
            return this;
        }

        public ProductDTOBuilder stockDTO(StockDTO stockDTO) {
            this.stockDTO = stockDTO;
            return this;
        }

        public ProductDTOBuilder categoryDTO(CategoryDTO categoryDTO) {
            this.categoryDTO = categoryDTO;
            return this;
        }

        public ProductDTOBuilder eGuarantee(EGuarantee eGuarantee) {
            this.eGuarantee = eGuarantee;
            return this;
        }

        public ProductDTO build() {
            return new ProductDTO(this);
        }
    }
}