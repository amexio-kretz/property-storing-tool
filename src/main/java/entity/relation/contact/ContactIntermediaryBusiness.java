package entity.relation.contact;


import entity.EntityToConvert;
import entity.relation.ContactToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "core_contact_apporteur_d_affaire")
public class ContactIntermediaryBusiness extends ContactToContact {

}