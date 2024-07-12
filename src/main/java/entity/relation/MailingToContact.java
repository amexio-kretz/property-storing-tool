package entity.relation;

import entity.ContactEntity;
import entity.MailingEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@MappedSuperclass
public class MailingToContact {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mailing_id")
    MailingEntity mailing;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    ContactEntity contact;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}