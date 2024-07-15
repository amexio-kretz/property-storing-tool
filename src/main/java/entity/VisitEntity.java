package entity;

import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "core_visit")
public class VisitEntity extends AuditableEntity implements EntityToConvert{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_to_id", referencedColumnName = "id")
    private UserEntity assignedTo;

    private String comments;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    @Column(nullable = false)
    @NotNull
    private Timestamp date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_contact_id", referencedColumnName = "id")
    private ContactEntity relatedContact;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_property_id", referencedColumnName = "id")
    private PropertyEntity relatedProperty;


    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof VisitEntity)) {
            return false;
        }
        if (id == null && ((VisitEntity) o).getId() == null){
            return false;
        }
        if (id == null || ((VisitEntity) o).getId() == null){
            return false;
        }
        return id.equals(((VisitEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}