package entity;

import com.sun.istack.NotNull;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "core_property")
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
public class PropertyEntity extends AuditableEntity implements EntityToConvert {

    @Column(length = 255)
    private String activitesCommerciales;

    @Column(length = 255)
    private String adresse;

    @Column(nullable = false)
    @NotNull
    private Boolean airConditionne;

    @Column(nullable = false)
    @NotNull
    private Boolean alarme;

    private Integer anneeConstruction;

    /**
     * @deprecated Not used and always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean archived;

    @Column(nullable = false)
    @NotNull
    private Boolean ascenseur;

    @Column(length = 255)
    private String autreVille;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(length = 255)
    private String avenantAuMandatNumero;

    @Column(nullable = false)
    @NotNull
    private Boolean bienEnCopro;

    @Column(nullable = false)
    @NotNull
    private Boolean changesPending;

    private Integer charges;

    @Column(nullable = false)
    @NotNull
    private Boolean chargesChauffage;

    @Column(nullable = false)
    @NotNull
    private Boolean chargesEauChaude;

    @Column(nullable = false)
    @NotNull
    private Boolean chargesGardienne;

    @Column(nullable = false)
    @NotNull
    private Boolean cheminee;

    @Column(length = 100)
    private String codePostal;

    private String comments;

    private String commentsVente;

    private Integer commission;

    private Integer consommationEnergetique;

    private String consommationEnergetiqueLabel;

    @Column(nullable = false)
    @NotNull
    private Boolean coproEnProcedure;

    /**
     * @deprecated Always null
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    private UserEntity createdByUser;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    private Timestamp dateDenonciationMandat;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private Timestamp dateDispo;

    private Timestamp dateFacture;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    private Timestamp dateFinMandat;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    private Timestamp dateMandat;

    private Timestamp datePromesse;

    private Timestamp dateVente;

    private Timestamp dateValuation;

    private Timestamp dateAcceptedOffer;

    private Timestamp dateLead;

    private Timestamp dateAssignedLead;

    @Column(length = 255)
    private String departement;

    @Deprecated(since = "1.1.4", forRemoval = true)
    private String descriptionAr;

    private String descriptionEn;

    private String descriptionEs;

    private String descriptionFr;

    @Deprecated(since = "1.1.4", forRemoval = true)
    private String descriptionRu;

    private String descriptionPt;

    @Deprecated(since = "1.1.4", forRemoval = true)
    private String descriptionZh;

    @Column(length = 255, nullable = false)
    @NotNull
    private String destinationDeReve;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveAtlantique;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveIles;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveMediterranee;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveMontagne;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveOccitanie;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveParis;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeRevePortugal;

    @Column(length = 50, nullable = false)
    @NotNull
    private String destinationDeReveUsa;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quartier_id", referencedColumnName = "id", nullable = true)
    private DistrictEntity district;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    private Boolean duplicating;

    private Integer emissionsGes;

    private String emissionGesLabel;

    private Integer energyCostMax;

    private Integer energyCostMin;

    private Integer etage;

    private Boolean exportBellesdemeures;

    private Boolean exportFigaro;

    private Boolean exportGreenacres;

    private Boolean exportJamesedition;

    private Boolean exportRightmove;

    private Boolean exportIdealista;

    private Boolean exportIdealistaSpain;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean exportInProgress;

    private Boolean exportKp;

    private Boolean exportSeloger;

    private Boolean exportSmc;

    private Boolean exportUbiflow;

    private Boolean exportSocial;

    @Column(nullable = false)
    @NotNull
    private Boolean favoris;

    private Integer honorairesLocation;

    private Integer honorairesVente;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean imported;

    // TODO revoir tous les champs et l'appliquer...
    @Column(length = 255, nullable = false)
    @NotNull
    private String infos;

    private String infosAcces;

    private String infosDetaillees;

    private String infosLead;

    @Column(nullable = false)
    @NotNull
    private Boolean isNew;

    @Column(nullable = false)
    @NotNull
    private Boolean isOffmarket;

    @Column(nullable = false)
    @NotNull
    private Boolean isProtected;

    private Double lat;

    @Column(length = 255)
    private String legacyAdresse;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private String libelleEn;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private String libelleFr;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    private String libelleMandat;

    @Column(length = 255)
    @NotNull
    private String lifeStyle;

    private Double lng;

    @Column(nullable = false)
    @NotNull
    private Boolean loyerCc;

    @Column(nullable = false)
    @NotNull
    private Boolean loyerHt;

    private Integer nombreCaves;

    private Integer nombreChambres;

    private Integer nombreEtages;

    private Integer nombreLotsCopro;

    private Integer nombreParkings;

    private Integer nombrePieces;

    private Integer nombreSallesDeBain;

    private Integer nombreToilettes;

    @Column(nullable = false)
    @NotNull
    private Boolean nouveaute;

    @Column(length = 100)
    private String numero;

    @Column(length = 255)
    private String numeroLot;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    private Integer numeroMandat;

    @Type(type = "string-array")
    @Column(name = "old_refs", columnDefinition = "text[]", nullable = false)
    @NotNull
    private String[] oldRefs;

    @Column(length = 255)
    private String partieVersante;

    @Column(length = 50)
    private String payeurHonoraires;

    @Column(length = 255)
    private String pays;

    @Column(nullable = false)
    @NotNull
    private Boolean piscine;

    /**
     * @deprecated Preview URL for property should be automatically completed as done for photoExport
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(length = 255)
    private String preview;

    private Integer prixAffichageFai;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    private Integer prixCession;

    @Column(nullable = false)
    @NotNull
    private Boolean prixConfidentiel;

    private Integer prixLoyer;

    private Integer prixSaisonnierMin;

    private Integer prixSaisonnierMax;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    private Integer prixMandat;

    private Integer prixNetVendeur;

    private Integer prixVente;

    @Column(length = 255)
    private String propertySousType;

    @Column(length = 255)
    private String propertyType;

    private Timestamp publishedAt;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean publishing;

    @Column(nullable = false)
    @NotNull
    private Boolean refaitNeuf;

    @Column(length = 6)
    private String reference;

    /**
     * @deprecated Used only to force an update, and refresh old indexation
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean refreshing;

    @Column(length = 255)
    private String region;

    @Column(length = 255)
    private String rue;

    @Column(length = 255)
    private String source;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    @Column(length = 255)
    private String statutMandat;

    @Column(length = 255)
    private String statutVente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "suivi_par_id", referencedColumnName = "id")
    private UserEntity suiviPar;

    private Integer surfaceBalcon;

    private Integer surfaceCarrez;

    private Integer surfaceCave;

    private Integer surfaceHabitable;

    private Integer surfaceJardin;

    private Integer surfaceParcelle;

    private Integer surfaceSejour;

    private Integer surfaceTerrasse;

    private Integer surfaceTotale;

    private Integer taxeFonciere;

    private Integer taxeHabitation;

    @Column(length = 3)
    private String theme;

    @Deprecated(since = "1.1.4", forRemoval = true)
    @Column(length = 255)
    private String titreAr;

    @Column(length = 255)
    private String titreEn;

    @Column(length = 255)
    private String titreEs;

    @Column(length = 255)
    private String titreFr;

    @Column(length = 255)
    private String titrePt;

    @Deprecated(since = "1.1.4", forRemoval = true)
    @Column(length = 255)
    private String titreRu;

    @Deprecated(since = "1.1.4", forRemoval = true)
    @Column(length = 255)
    private String titreZh;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Boolean translating;

    @Column(length = 255)
    private String typeAnnonce;

    /**
     * @deprecated Not used and replaced by mandate object
     */
    @Deprecated(since = "1.1.3", forRemoval = true)
    @Column(length = 255)
    private String typeMandat;

    @Column(length = 10)
    private String unitArea;

    @Column(length = 10)
    private String unitPrice;

    @Column(length = 255)
    private String venduPar;

    @Column(length = 255)
    private String venduParOther;

    /**
     * @deprecated Not used
     */
    @Deprecated(since = "Refactoring", forRemoval = true)
    @Column(nullable = false)
    @NotNull
    private Integer version;

    @Column(length = 255)
    private String video;

    @Column(nullable = false)
    @NotNull
    private Boolean videoPublique;

    @Column(length = 255)
    private String ville;

    @Column(length = 255)
    private String visiteVirtuelle;

    private Boolean top_floor;

    private Boolean high_floor;

    private Boolean single_story;

    private Boolean garden;

    private Boolean terrace;

    private Boolean balcony;

    private Boolean handicap_access;

    private Boolean commercial_space;

    private Boolean parking;

    private Boolean toRenovate;

    // Need to write it manually because of the interface
    @Override
    public Long getId(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof PropertyEntity)) {
            return false;
        }
        if (id == null && ((PropertyEntity) o).getId() == null){
            return false;
        }
        if (id == null || ((PropertyEntity) o).getId() == null){
            return false;
        }
        return id.equals(((PropertyEntity) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}