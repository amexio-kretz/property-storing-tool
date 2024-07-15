package entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * Entity for contact search criteria.
 *
 * @since 1.1.2
 */
@Getter
@Setter
@Entity
@Table(name = "core_contact_search_criteria")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
public class ContactSearchCriteriaEntity implements EntityToConvert {

    @Column(nullable = false)
    @NotNull
    private Boolean alertEnabled;

    @Type(type = "string-array")
    @Column(name = "category", columnDefinition = "text[]")
    private String[] categories;

    private String comment;

    @Type(type = "string-array")
    @Column(name = "country", columnDefinition = "text[]")
    private String[] countries;

    @Type(type = "string-array")
    @Column(name = "department", columnDefinition = "text[]")
    private String[] departments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private Integer maxArea;

    private Integer maxPrice;

    private Integer minArea;

    private Integer minBedrooms;

    private Integer minPrice;

    @Type(type = "string-array")
    @Column(name = "region", columnDefinition = "text[]")
    private String[] regions;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "related_contact_id", referencedColumnName = "id")
    private ContactEntity relatedContact;

    @Type(type = "string-array")
    @Column(name = "town", columnDefinition = "text[]")
    private String[] towns;

    @Column(nullable = false)
    @NotNull
    private String type;

    @Type(type = "string-array")
    @Column(name = "zip_code", columnDefinition = "text[]")
    private String[] zipCodes;

    private Boolean top_floor;

    private Boolean high_floor;

    private Boolean no_ground_floor;

    private Boolean single_story;

    private Boolean garden;

    private Boolean terrace;

    private Boolean balcony;

    private Boolean handicap_access;

    private Boolean commercial_space;

    private Boolean parking;

    private Boolean pool;

    private Boolean air_conditioning;

    private Boolean elevator;

    private Boolean toRenovate;

    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }

}