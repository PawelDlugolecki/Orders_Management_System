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
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trade")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Producer> producers = new HashSet<>();
}