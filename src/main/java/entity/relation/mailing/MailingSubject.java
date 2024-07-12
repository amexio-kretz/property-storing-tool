package entity.relation.mailing;

import entity.relation.MailingToProperty;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_mailing_subject")
public class MailingSubject extends MailingToProperty {
    public MailingSubject clone(){
        MailingSubject newSubject = new MailingSubject();
        newSubject.setMailing(getMailing());
        newSubject.setProperty(getProperty());

        return newSubject;
    }
}