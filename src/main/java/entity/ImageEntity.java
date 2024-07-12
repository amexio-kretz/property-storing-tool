package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "core_image")
public class ImageEntity {

    @Column(nullable = false)
    @NotNull
    private Timestamp createdAt;

    private Boolean exportPlatforms;

    private Boolean exportKp;

    @Column(nullable = false)
    @NotNull
    private Boolean favorite;

    @Column(length = 100, nullable = false)
    @NotNull
    private String file;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    /**
     * @return title.
     */
    @Column(length = 255)
    private String title;

    /**
     * @deprecated Related property should be only managed throw core_property_plans and core_property_photos tables.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_property_id", referencedColumnName = "id")
    @Deprecated(since = "Refactoring", forRemoval = true)
    private PropertyEntity relatedProperty;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uploaded_by_user_id", referencedColumnName = "id")
    private UserEntity uploadedByUser;

    private Integer weight;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof ImageEntity)) {
            return false;
        }
        if (id == null && ((ImageEntity) o).getId() == null){
            return false;
        }
        if (id == null || ((ImageEntity) o).getId() == null){
            return false;
        }
        return id.equals(((ImageEntity) o).getId());
    }
}