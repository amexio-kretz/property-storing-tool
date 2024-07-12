package entity;

import lombok.Getter;
import com.sun.istack.NotNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "core_export_log")
public class ExportLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    Long Id;

    @Column(nullable = false, length = 60)
    @NotNull
    private String exportPlatform;

    @Column(nullable = false)
    @NotNull
    private Timestamp createdAt;

    @Column(nullable = false)
    @NotNull
    private Timestamp updatedAt;

    @Column
    private String hashedExportedProperties;

    @Column
    private String propertyReference;

}
