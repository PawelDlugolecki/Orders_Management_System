package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDTO {

    private Long id;
    private int quantity;
    private ShopDTO shopDTO;
    private ProductDTO productDTO;

    public StockDTO(StockDTOBuilder stockDTOBuilder) {
        this.id = stockDTOBuilder.id;
        this.quantity = stockDTOBuilder.quantity;
        this.shopDTO = stockDTOBuilder.shopDTO;
        this.productDTO = stockDTOBuilder.productDTO;
    }

    public static StockDTO.StockDTOBuilder builder() {
        return new StockDTOBuilder();
    }

    public static class StockDTOBuilder {
        private Long id;
        private int quantity;
        private ShopDTO shopDTO;
        private ProductDTO productDTO;

        public StockDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StockDTOBuilder quantity(int quantity) {
            try {
                if (quantity < 0) {
                    throw new IllegalArgumentException("QUANTITY LESS THAN ZERO");
                }
                this.quantity = quantity;
                return this;
            } catch (MyException e) {
                throw new MyException(this.getClass().getSimpleName(), e.getLocalizedMessage());
            }
        }

        public StockDTOBuilder shopDTO(ShopDTO shopDTO) {
            this.shopDTO = shopDTO;
            return this;
        }

        public StockDTOBuilder productDTO(ProductDTO productDTO) {
            this.productDTO = productDTO;
            return this;
        }

        public StockDTO build() {
            return new StockDTO(this);
        }
    }
}