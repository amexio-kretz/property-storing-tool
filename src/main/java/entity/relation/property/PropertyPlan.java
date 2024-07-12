package entity.relation.property;

import entity.relation.PropertyToImage;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "core_property_plans")
public class PropertyPlan extends PropertyToImage {
    public PropertyPlan clone(){
        PropertyPlan plan = new PropertyPlan();
        plan.setImage(getImage());
        plan.setProperty(getProperty());

        return plan;
    }
}