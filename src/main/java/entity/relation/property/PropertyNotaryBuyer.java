package entity.relation.property;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_notaire_acquereur")
public class PropertyNotaryBuyer extends PropertyToContact {
    public PropertyNotaryBuyer clone(){
        PropertyNotaryBuyer newBuyer = new PropertyNotaryBuyer();
        newBuyer.setContact(getContact());
        newBuyer.setProperty(getProperty());

        return newBuyer;
    }
}