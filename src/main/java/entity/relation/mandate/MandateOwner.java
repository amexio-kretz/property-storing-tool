package entity.relation.mandate;

import entity.ContactEntity;
import entity.MandateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "core_mandate_owner")
public class MandateOwner {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    ContactEntity contact;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mandate_id")
    MandateEntity mandate;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}