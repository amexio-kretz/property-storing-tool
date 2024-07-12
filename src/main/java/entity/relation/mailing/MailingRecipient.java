package entity.relation.mailing;

import entity.relation.MailingToContact;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_mailing_recipient")
public class MailingRecipient extends MailingToContact {

}