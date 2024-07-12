package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Table(name = "core_content")
public class ContentEntity extends AuditableEntity {

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean changesPending;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean exportInProgress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean isNew;

    @Column(nullable = false)
    @NotNull
    private String key;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private Timestamp publishedAt;

    @Column(nullable = false)
    @NotNull
    private String textAr;

    @Column(nullable = false)
    @NotNull
    private String textEn;

    @Column(nullable = false)
    @NotNull
    private String textEs;

    @Column(nullable = false)
    @NotNull
    private String textFr;

    @Column(nullable = false)
    @NotNull
    private String textPt;

    @Column(nullable = false)
    @NotNull
    private String textRu;

    @Column(nullable = false)
    @NotNull
    private String textZh;

}