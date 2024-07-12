package entity.relation;

import entity.ContactEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@MappedSuperclass
public class ContactToContact {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_contact_id")
    ContactEntity fromContact;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_contact_id")
    ContactEntity toContact;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}