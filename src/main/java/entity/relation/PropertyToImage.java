package entity.relation;

import entity.ImageEntity;
import entity.PropertyEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@MappedSuperclass
public class PropertyToImage {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    ImageEntity image;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id")
    PropertyEntity property;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PropertyToImage clone(){
        PropertyToImage newPropertyToImage = new PropertyToImage();
        newPropertyToImage.setProperty(property);
        newPropertyToImage.setImage(image);

        return newPropertyToImage;
    }
}