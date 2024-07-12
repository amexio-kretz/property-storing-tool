package entity.relation.property;

import entity.relation.PropertyToImage;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_photos")
public class PropertyPhoto extends PropertyToImage {
    public PropertyPhoto clone(){
        PropertyPhoto photo = new PropertyPhoto();
        photo.setImage(getImage());
        photo.setProperty(getProperty());

        return photo;
    }
}