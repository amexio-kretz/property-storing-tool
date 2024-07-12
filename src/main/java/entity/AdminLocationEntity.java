package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter

@Entity
@Table(name = "admin_location")
public class AdminLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @Column(length = 255)
    private String newCountry;

    @Column(length = 255)
    private String newDepartment;

    @Column(length = 255)
    private String newRegion;

    @Column(length = 255)
    private String newTown;

    @Column(length = 100)
    private String newZipCode;

    @Column(length = 255)
    private String oldCountry;

    @Column(length = 255)
    private String oldDepartment;

    @Column(length = 255)
    private String oldRegion;

    @Column(length = 255)
    private String oldTown;

    @Column(length = 100)
    private String oldZipCode;

}