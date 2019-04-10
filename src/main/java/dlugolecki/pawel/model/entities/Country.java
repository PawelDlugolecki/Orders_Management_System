package dlugolecki.pawel.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "countries", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Country {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Customer> customers = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Shop> shops = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "country")
    private Set<Producer> producers = new HashSet<>();
}