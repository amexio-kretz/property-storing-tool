package entity;

import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "core_company")
public class CompanyEntity extends AuditableEntity implements EntityToConvert{

    @Column(nullable = false, length = 50)
    @NotNull
    private String category;

    @Column(length = 255)
    private String companyAdresse;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(nullable = false, length = 255)
    @NotNull
    private String name;

    @Column(length = 60)
    private String numeroFiscal;

    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }
}