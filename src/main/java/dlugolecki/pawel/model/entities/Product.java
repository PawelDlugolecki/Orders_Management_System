package dlugolecki.pawel.model.entities;

import dlugolecki.pawel.model.enums.EGuarantee;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Stock> stocks = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<CustomerOrder> customerOrders = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "guarantee_components",
            joinColumns = {@JoinColumn(name = "product_id")}
    )
    @Column(name = "guarantee_component")
    @Enumerated(EnumType.STRING)
    private Set<EGuarantee> guaranteeComponents = new HashSet<>();
}