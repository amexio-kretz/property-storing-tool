package entity.relation.property;

//import entity.relation.PropertyToContact;
import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_tiers")
public class PropertyThirdParty extends PropertyToContact {
    public PropertyThirdParty clone(){
        PropertyThirdParty thirdParty = new PropertyThirdParty();
        thirdParty.setContact(getContact());
        thirdParty.setProperty(getProperty());

        return thirdParty;
    }
}