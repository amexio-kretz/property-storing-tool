package entity.relation.contact;

import entity.relation.ContactToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_contact_related_contact")
public class ContactRelatedContact extends ContactToContact {

}