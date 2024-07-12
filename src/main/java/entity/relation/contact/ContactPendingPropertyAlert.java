package entity.relation.contact;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_contact_alert_pending_properties")
public class ContactPendingPropertyAlert extends PropertyToContact {
    public ContactPendingPropertyAlert clone(){
        ContactPendingPropertyAlert newAlert = new ContactPendingPropertyAlert();
        newAlert.setContact(getContact());
        newAlert.setProperty(getProperty());

        return newAlert;
    }
}