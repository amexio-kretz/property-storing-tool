package entity.relation.property;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_agence_partenaire")
public class PropertyPartnerAgency extends PropertyToContact {
    public PropertyPartnerAgency clone(){
        PropertyPartnerAgency agency = new PropertyPartnerAgency();
        agency.setContact(getContact());
        agency.setProperty(getProperty());

        return agency;
    }
}