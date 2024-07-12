package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for mandate number object.
 *
 * @since 1.1.3
 */
@Getter
@Setter

@Entity
@Table(name = "core_mandate_number")
public class MandateNumberEntity {

    @Id
    @NotNull
    private Integer nextNumber;

}