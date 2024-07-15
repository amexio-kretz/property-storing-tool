package entity;

import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table(name = "core_mailing")
public class MailingEntity extends AuditableEntity implements EntityToConvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @NotNull
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    @Column(length = 5)
    private String selectedTemplate;

    @Column(length = 100, nullable = false)
    @NotNull
    private String mailingContentFile;

    @Column(name = "status_ok")
    private Boolean statusOk;


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
        if (!(o instanceof MailingEntity)) {
            return false;
        }
        if (id == null && ((MailingEntity) o).getId() == null) {
            return false;
        }
        if (id == null || ((MailingEntity) o).getId() == null) {
            return false;
        }
        return id.equals(((MailingEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}