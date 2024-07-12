package entity;

import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Entity for mandate object.
 *
 * @since 1.1.3
 */
@Getter
@Setter
@Entity
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@Table(name = "core_mandate")
public class MandateEntity extends AuditableEntity {

    private Boolean advertisedByOtherAgencies;

    private Boolean alreadyOnSale;

    private Timestamp alreadyOnSaleSince;

    private String auditOwners;

    private String auditProperty;

    private Timestamp authorizedAt;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorized_by_user_id", referencedColumnName = "id")
    private UserEntity authorizedByUser;

    private Boolean canAdvertise;

    @Type(type = "string-array")
    @Column(name = "can_advertise_on", columnDefinition = "text[]")
    private String[] canAdvertiseOn;

    private String comments;

    // TODO use Spring Audit
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    private Timestamp denunciatedAt;

    private Timestamp endsAt;

    private Integer feesTaxesIncluded;

    private Integer followingAgenciesNumber;

    private Boolean hasNegotiatedPrice;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private String label;

    private Integer number;

    private Integer price;

    @Column(length = 5)
    private String priceValuation;

    @Type(type = "string-array")
    @Column(name = "property_ad_url", columnDefinition = "text[]")
    private String[] propertyAdUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_property_id", referencedColumnName = "id")
    private PropertyEntity relatedProperty;

    private Timestamp startsAt;

    @Column(length = 5)
    private String status;

    @Column(length = 5)
    private String type;

    @Column(length = 5)
    private String validationStatus;

    private Integer exclusiveDuration;

    @Column(length = 5)
    private String shootingType;

    private String refCadastrale;


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof MandateEntity)) {
            return false;
        }
        if (id == null && ((MandateEntity) o).getId() == null){
            return false;
        }
        if (id == null || ((MandateEntity) o).getId() == null){
            return false;
        }
        return id.equals(((MandateEntity) o).getId());
    }
}