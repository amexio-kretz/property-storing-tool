package entity.relation.property;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_acquereur")
public class PropertyBought extends PropertyToContact {
    public PropertyBought clone(){
        PropertyBought newProperty = new PropertyBought();
        newProperty.setContact(getContact());
        newProperty.setProperty(getProperty());

        return newProperty;
    }
}