package entity.relation.property;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_apporteur_d_affaire")
public class PropertyIntermediateBusiness extends PropertyToContact {
    public PropertyIntermediateBusiness clone(){
        PropertyIntermediateBusiness newBusiness = new PropertyIntermediateBusiness();
        newBusiness.setContact(getContact());
        newBusiness.setProperty(getProperty());

        return newBusiness;
    }
}