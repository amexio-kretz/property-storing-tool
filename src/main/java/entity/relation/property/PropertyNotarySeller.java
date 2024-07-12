package entity.relation.property;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_notaire_vendeur")
public class PropertyNotarySeller extends PropertyToContact {
    public PropertyNotarySeller clone(){
        PropertyNotarySeller newSeller = new PropertyNotarySeller();
        newSeller.setContact(getContact());
        newSeller.setProperty(getProperty());

        return newSeller;
    }
}