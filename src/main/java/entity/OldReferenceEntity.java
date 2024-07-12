package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "core_oldreference")
public class OldReferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    @NotNull
    private String reference;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_property_id", referencedColumnName = "id")
    private PropertyEntity relatedProperty;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OldReferenceEntity)) {
            return false;
        }
        if (id == null && ((OldReferenceEntity) o).getId() == null) {
            return false;
        }
        if (id == null || ((OldReferenceEntity) o).getId() == null) {
            return false;
        }
        return id.equals(((OldReferenceEntity) o).getId());
    }
}