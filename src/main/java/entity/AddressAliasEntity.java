package entity;


import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "core_address_alias")
public class AddressAliasEntity extends AuditableEntity implements EntityToConvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 70, nullable = true)
    @Nullable
    private String countryFrom;

    @Column(length = 70, nullable = true)
    @Nullable
    private String countryTo;

    @Column(length = 100, nullable = true)
    @Nullable
    private String departmentFrom;

    @Column(length = 100, nullable = true)
    @Nullable
    private String departmentTo;

    @Column(length = 100, nullable = true)
    @Nullable
    private String regionFrom;

    @Column(length = 100, nullable = true)
    @Nullable
    private String regionTo;

    @Column(length = 200, nullable = true)
    @Nullable
    private String townFrom;

    @Column(length = 200, nullable = true)
    @Nullable
    private String townTo;

    @Column(length = 20, nullable = true)
    @Nullable
    private String zipCodeFrom;

    @Column(length = 20, nullable = true)
    @Nullable
    private String zipCodeTo;

    private Boolean exportKretz;

    private Boolean exportSeloger;

    private Boolean exportFigaro;

    private Boolean exportBelledemeure;

    private Boolean exportSmc;

    private Boolean exportRightmove;

    private Boolean exportGreenacres;

    private Boolean exportJamesedition;

    private Boolean exportIdealistaPt;

    private Boolean exportIdealistaEs;

    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }
}
