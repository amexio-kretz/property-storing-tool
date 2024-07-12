package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
class AuditableEntity {

    @Column(nullable = false)
    @NotNull
    private Timestamp createdAt;

    @Column(nullable = false)
    @NotNull
    private Timestamp updatedAt;
}