package entity.relation.contact;

import entity.relation.PropertyToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_contact_alert_sent_properties")
public class ContactSentPropertyAlert extends PropertyToContact {
    public ContactSentPropertyAlert clone(){
        ContactSentPropertyAlert newAlert = new ContactSentPropertyAlert();
        newAlert.setContact(getContact());
        newAlert.setProperty(getProperty());

        return newAlert;
    }
}