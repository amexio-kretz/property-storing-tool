package entity.relation.property;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_proprietaire")
public class PropertyOwner extends PropertyToContact {
    public PropertyOwner clone(){
        PropertyOwner owner = new PropertyOwner();
        owner.setContact(getContact());
        owner.setProperty(getProperty());

        return owner;
    }
}