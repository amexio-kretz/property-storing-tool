package entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@Table(name = "core_contact")
public class ContactEntity extends AuditableEntity {

    // TODO Faire le coup du tableau de données, mettre la documentation
    // https://vladmihalcea.com/postgresql-array-java-list/
    // https://github.com/vladmihalcea/hibernate-types
    // https://www.baeldung.com/java-hibernate-map-postgresql-array

    @Column(length = 255)
    private String adresse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agent_source", referencedColumnName = "id")
    private UserEntity agentSource;

    private Timestamp alertLastSentAt;

    @Type(type = "string-array")
    @Column(name = "category_alt", columnDefinition = "text[]")
    private String[] categoriesAlt;

    @Column(length = 10)
    private String civility;

    @Column(length = 100)
    private String codePostal;

    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private CompanyEntity company;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    @Column(length = 255)
    private String departement;

    @Column(length = 50, nullable = false)
    @NotNull
    private String email;

    @Column(length = 254)
    private String emailSecondary;

    @Column(length = 255)
    private String firstName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Boolean isLegit;

    @Column(nullable = false)
    @NotNull
    private Boolean isVip;

    @Column(length = 255)
    private String lastName;

    private Double lat;

    private Double lng;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(length = 100)
    private String numero;

    @Column(length = 255)
    private String pays;

    @Column(length = 50)
    private String phoneHome;

    @Column(length = 50)
    private String phoneMobile;

    @Column(length = 50, nullable = false)
    @NotNull
    private String phoneWork;

    @Column(length = 255)
    private String position;

    @Column(length = 255)
    private String region;

    @Column(length = 255)
    private String rue;

    @Column(length = 255)
    private String source;

    @Column(length = 3)
    private String statutRecherche;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suivi_par_id", referencedColumnName = "id")
    private UserEntity suiviPar;

    @Column(length = 255)
    private String ville;

    @Column(length = 50)
    private String cni;


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContactEntity)) {
            return false;
        }
        if (id == null && ((ContactEntity) o).getId() == null) {
            return false;
        }
        if (id == null || ((ContactEntity) o).getId() == null) {
            return false;
        }
        return id.equals(((ContactEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}