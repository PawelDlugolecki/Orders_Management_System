package dlugolecki.pawel.model.entities;

import dlugolecki.pawel.model.enums.EPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime time;
    private BigDecimal discount;
    private int quantity;

    @ElementCollection
    @CollectionTable(
            name = "payments",
            joinColumns = {@JoinColumn(name = "customer_order_id")}
    )
    @Column(name = "payment")
    @Enumerated(EnumType.STRING)
    private Set<EPayment> payments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;
}