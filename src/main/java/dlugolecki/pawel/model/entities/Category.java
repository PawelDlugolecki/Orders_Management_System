package dlugolecki.pawel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category")
    private Set<Product> products = new HashSet<>();
}
