package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "core_district")
public class DistrictEntity extends AuditableEntity implements EntityToConvert {

    /**
     * @deprecated Always null
     */
    @Column(name = "adresse", length = 255)
    @Deprecated(since = "Refactoring", forRemoval = true)
    private String adress;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    private String geom;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private Double lat;

    private Double lng;

    @Column(length = 255, nullable = false)
    @NotNull
    private String name;


    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DistrictEntity)) {
            return false;
        }
        if (id == null && ((DistrictEntity) o).getId() == null) {
            return false;
        }
        if (id == null || ((DistrictEntity) o).getId() == null) {
            return false;
        }
        return id.equals(((DistrictEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}