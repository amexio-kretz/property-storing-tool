package entity.relation;


import entity.MailingEntity;
import entity.PropertyEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@MappedSuperclass
public class MailingToProperty {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mailing_id")
    MailingEntity mailing;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    PropertyEntity property;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public MailingToProperty clone(){
        MailingToProperty newMailingToProperty = new MailingToProperty();
        newMailingToProperty.setMailing(mailing);
        newMailingToProperty.setProperty(property);

        return newMailingToProperty;
    }

}