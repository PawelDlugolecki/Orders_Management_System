package dlugolecki.pawel.dto;

import dlugolecki.pawel.model.enums.EPayment;
import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerOrderDTO {
    private Long id;
    private LocalDateTime time;
    private BigDecimal discount;
    private int quantity;
    private CustomerDTO customerDTO;
    private ProductDTO productDTO;
    private EPayment ePayment;

    public CustomerOrderDTO(CustomerOrderDTOBuilder customerOrderDTOBuilder) {
        this.id = customerOrderDTOBuilder.id;
        this.time = customerOrderDTOBuilder.time;
        this.discount = customerOrderDTOBuilder.discount;
        this.quantity = customerOrderDTOBuilder.quantity;
        this.customerDTO = customerOrderDTOBuilder.customerDTO;
        this.productDTO = customerOrderDTOBuilder.productDTO;
        this.ePayment = customerOrderDTOBuilder.ePayment;
    }

    public static CustomerOrderDTO.CustomerOrderDTOBuilder builder() {
        return new CustomerOrderDTOBuilder();
    }

    public static class CustomerOrderDTOBuilder {
        private Long id;
        private LocalDateTime time;
        private BigDecimal discount;
        private int quantity;
        private CustomerDTO customerDTO;
        private ProductDTO productDTO;
        private EPayment ePayment;

        public CustomerOrderDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerOrderDTOBuilder time(LocalDateTime time) {
            this.time = time;
            return this;
        }

        public CustomerOrderDTOBuilder discount(BigDecimal discount) {
            try {
                if (discount == null || discount.compareTo(BigDecimal.ZERO) < 0) {
                    throw new MyException("CUSTOMER_ORDER", "CustomerOrder discount is not correct");
                }
                this.discount = discount;
                return this;
            } catch (MyException e) {
                throw new MyException("CUSTOMER_ORDER", "CustomerOrder builder");
            }
        }

        public CustomerOrderDTOBuilder quantity(int quantity) {
            try {
                if (quantity < 0) {
                    throw new MyException("CUSTOMER_ORDER", "CustomerOrder quantity is not correct");
                }
                this.quantity = quantity;
                return this;
            } catch (MyException e) {
                throw new MyException("CUSTOMER_ORDER", "CustomerOrder builder");
            }
        }

        public CustomerOrderDTOBuilder customerDTO(CustomerDTO customerDTO) {
            this.customerDTO = customerDTO;
            return this;
        }

        public CustomerOrderDTOBuilder productDTO(ProductDTO productDTO) {
            this.productDTO = productDTO;
            return this;
        }

        public CustomerOrderDTOBuilder ePayment(EPayment ePayment) {
            this.ePayment = ePayment;
            return this;
        }

        public CustomerOrderDTO build() {
            return new CustomerOrderDTO(this);
        }
    }
}