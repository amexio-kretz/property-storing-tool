package entity.relation;


import entity.ContactEntity;
import entity.PropertyEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@MappedSuperclass
public class PropertyToContact {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    ContactEntity contact;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    PropertyEntity property;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PropertyToContact clone(){
        PropertyToContact newPropertyToContact = new PropertyToContact();
        newPropertyToContact.setContact(contact);
        newPropertyToContact.setProperty(property);

        return newPropertyToContact;
    }
}