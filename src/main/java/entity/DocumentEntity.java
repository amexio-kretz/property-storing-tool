package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "core_document")
public class DocumentEntity implements EntityToConvert{

    @Column(length = 50)
    private String category;

    @Column(nullable = false)
    @NotNull
    private Timestamp createdAt;

    @Column(length = 100, nullable = false)
    @NotNull
    private String file;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NotNull
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Boolean isTemplate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_property_id", referencedColumnName = "id")
    private PropertyEntity relatedProperty;

    @Column(length = 50)
    private String templateName;

    @Column(length = 255)
    private String title;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uploaded_by_user_id", referencedColumnName = "id")
    private UserEntity uploadedByUser;

    /**
     * @deprecated Always equals to 0
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private Integer weight;


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
        if (!(o instanceof DocumentEntity)) {
            return false;
        }
        if (id == null && ((DocumentEntity) o).getId() == null){
            return false;
        }
        if (id == null || ((DocumentEntity) o).getId() == null){
            return false;
        }
        return id.equals(((DocumentEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}