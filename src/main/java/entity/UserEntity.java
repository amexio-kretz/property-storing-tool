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
@Table(name = "users_user")
public class UserEntity implements EntityToConvert {

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(length = 50)
    private String category;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean changesPending;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Timestamp dateJoined;

    private String descriptionAr;

    private String descriptionEn;

    private String descriptionEs;

    private String descriptionFr;

    private String descriptionPt;

    private String descriptionRu;

    private String descriptionZh;

    @Column(nullable = false, length = 20)
    @NotNull
    private String destination;

    @Column(nullable = false, length = 254)
    @NotNull
    private String email;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean exportInProgress;

    @Column(nullable = false, length = 30)
    @NotNull
    private String firstName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Boolean isActive;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean isNew;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean isSuperuser;

    @Column(nullable = false)
    @NotNull
    private Boolean isPhoneDisplayed;

    @Column(nullable = false)
    @NotNull
    private Boolean isVisible;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private Timestamp lastLogin;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false, length = 128)
    @NotNull
    private String password;

    @Column(length = 30)
    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private ImageEntity photo;

    @Column(nullable = false, length = 3)
    @NotNull
    private String role;

    @Deprecated(since = "1.1.4", forRemoval = true)
    @Column(length = 255)
    private String titreAr;

    @Column(length = 255)
    private String titreEn;

    @Column(length = 255)
    private String titreEs;

    @Column(length = 255)
    private String titreFr;

    @Column(length = 255)
    private String titrePt;

    @Deprecated(since = "1.1.4", forRemoval = true)
    @Column(length = 255)
    private String titreRu;

    @Deprecated(since = "1.1.4", forRemoval = true)
    @Column(length = 255)
    private String titreZh;

    private Integer weight;


    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }
        if (id == null && ((UserEntity) o).getId() == null){
            return false;
        }
        if (id == null || ((UserEntity) o).getId() == null){
            return false;
        }
        return id.equals(((UserEntity) o).getId());
    }

}