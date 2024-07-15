package service;

import entity.*;
import entity.relation.contact.ContactPendingPropertyAlert;
import entity.relation.contact.ContactSentPropertyAlert;
import entity.relation.mailing.MailingSubject;
import entity.relation.property.*;
import exception.StoringException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.*;

import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class StoringService {
    private final String storingPath;
    private final CSVFormat csvFormat;
    private long storingTimestamp;
    private static final Logger log = LoggerFactory.getLogger(StoringService.class);

    private final PropertyRepository propertyRepository;
    private final MandateRepository mandateRepository;
    private final ImageRepository imageRepository;
    private final VisitRepository visitRepository;
    private final DocumentRepository documentRepository;
    private final OldReferenceEntityRepository oldReferenceEntityRepository;
    private final ContactPendingPropertyAlertRepository contactPendingPropertyAlertRepository;
    private final ContactSentPropertyAlertRepository contactSentPropertyAlertRepository;
    private final MailingSubjectRepository mailingSubjectRepository;
    private final PropertyBoughtRepository propertyBoughtRepository;
    private final PropertyIntermediateBusinessRepository propertyIntermediateBusinessRepository;
    private final PropertyNotaryBuyerRepository propertyNotaryBuyerRepository;
    private final PropertyNotarySellerRepository propertyNotarySellerRepository;
    private final PropertyOwnerRepository propertyOwnerRepository;
    private final PropertyPartnerAgencyRepository propertyPartnerAgencyRepository;
    private final PropertyPhotoRepository propertyPhotoRepository;
    private final PropertyPlanRepository propertyPlanRepository;
    private final PropertyThirdPartyRepository propertyThirdPartyRepository;

    private final Set<PropertyEntity> propertiesSet;
    private final Set<MandateEntity> mandateSet;
    private final Set<ContactEntity> contactSet;
    private final Set<DistrictEntity> districtSet;
    private final Set<ImageEntity> imageSet;
    private final Set<UserEntity> userSet;
    private final Set<MailingEntity> mailingSet;
    private final Set<VisitEntity> visitSet;
    private final Set<DocumentEntity> documentSet;
    private final Set<OldReferenceEntity> oldReferenceEntitySet;
    // Associatives tables
    private final Set<MailingSubject> mailingSubjectSet;
    private final Set<ContactSentPropertyAlert> contactSentPropertyAlertSet;
    private final Set<ContactPendingPropertyAlert> contactPendingPropertyAlertSet;
    private final Set<PropertyBought> propertyBoughtSet;
    private final Set<PropertyIntermediateBusiness> propertyIntermediateBusinessSet;
    private final Set<PropertyNotaryBuyer> propertyNotaryBuyerSet;
    private final Set<PropertyNotarySeller> propertyNotarySellerSet;
    private final Set<PropertyOwner> propertyOwnerSet;
    private final Set<PropertyPartnerAgency> propertyPartnerAgencySet;
    private final Set<PropertyThirdParty> propertyThirdPartySet;
    private final Set<PropertyPhoto> propertyPhotoSet;
    private final Set<PropertyPlan> propertyPlanSet;

    private final List<Set> wholeSet;


    public StoringService(PropertiesConfiguration config) {
        storingPath = config.getString("storing-path");
        csvFormat = CSVFormat.DEFAULT.builder()
                .setAutoFlush(true)
                .setDelimiter("!#")
                .setEscape('\\')
                .setNullString("")
                .setQuote('"')
                .setQuoteMode(QuoteMode.ALL)
                .setTrim(true)
                .build();

        propertyRepository = new PropertyRepository();
        mandateRepository = new MandateRepository();
        propertyOwnerRepository = new PropertyOwnerRepository();
        imageRepository = new ImageRepository();
        propertyPhotoRepository = new PropertyPhotoRepository();
        mailingSubjectRepository = new MailingSubjectRepository();
        visitRepository = new VisitRepository();
        documentRepository = new DocumentRepository();
        contactPendingPropertyAlertRepository = new ContactPendingPropertyAlertRepository();
        contactSentPropertyAlertRepository = new ContactSentPropertyAlertRepository();
        oldReferenceEntityRepository = new OldReferenceEntityRepository();
        propertyBoughtRepository = new PropertyBoughtRepository();
        propertyIntermediateBusinessRepository = new PropertyIntermediateBusinessRepository();
        propertyNotaryBuyerRepository = new PropertyNotaryBuyerRepository();
        propertyNotarySellerRepository = new PropertyNotarySellerRepository();
        propertyPartnerAgencyRepository = new PropertyPartnerAgencyRepository();
        propertyPlanRepository = new PropertyPlanRepository();
        propertyThirdPartyRepository = new PropertyThirdPartyRepository();

        propertiesSet = new HashSet<>();
        mandateSet = new HashSet<>();
        contactSet = new HashSet<>();
        districtSet = new HashSet<>();
        imageSet = new HashSet<>();
        userSet = new HashSet<>();
        mailingSet = new HashSet<>();
        visitSet = new HashSet<>();
        documentSet = new HashSet<>();
        oldReferenceEntitySet = new HashSet<>();
        mailingSubjectSet = new HashSet<>();
        contactSentPropertyAlertSet = new HashSet<>();
        contactPendingPropertyAlertSet = new HashSet<>();
        propertyBoughtSet = new HashSet<>();
        propertyIntermediateBusinessSet = new HashSet<>();
        propertyNotaryBuyerSet = new HashSet<>();
        propertyNotarySellerSet = new HashSet<>();
        propertyOwnerSet = new HashSet<>();
        propertyPartnerAgencySet = new HashSet<>();
        propertyThirdPartySet = new HashSet<>();
        propertyPhotoSet = new HashSet<>();
        propertyPlanSet = new HashSet<>();

        wholeSet = new ArrayList<>(Arrays.asList(
                propertiesSet,
                mandateSet,
                contactSet,
                districtSet,
                imageSet,
                userSet,
                mailingSet,
                visitSet,
                documentSet,
                oldReferenceEntitySet,
                mailingSubjectSet,
                contactSentPropertyAlertSet,
                contactPendingPropertyAlertSet,
                propertyBoughtSet,
                propertyIntermediateBusinessSet,
                propertyNotaryBuyerSet,
                propertyNotarySellerSet,
                propertyOwnerSet,
                propertyPartnerAgencySet,
                propertyThirdPartySet,
                propertyPhotoSet,
                propertyPlanSet)
        );
    }

    public boolean store(Date lowerLimit, Date upperLimit) {
        storingTimestamp = System.currentTimeMillis();

        try {
            List<PropertyEntity> properties = propertyRepository.readBetweenTwoDates(lowerLimit, upperLimit);
            propertiesSet.addAll(properties);

            for (PropertyEntity p : propertiesSet) {
                if (p.getSuiviPar() != null) {
                    userSet.add(p.getSuiviPar());
                }
                if (p.getCreatedByUser() != null) {
                    userSet.add(p.getCreatedByUser());
                }
                if (p.getDistrict() != null) {
                    districtSet.add(p.getDistrict());
                }
                mandateSet.addAll(mandateRepository.findByPropertyId(p.getId()));
                documentSet.addAll(documentRepository.findByPropertyId(p.getId()));
                visitSet.addAll(visitRepository.findByPropertyId(p.getId()));
                oldReferenceEntitySet.addAll(oldReferenceEntityRepository.findByPropertyId(p.getId()));
                List<MailingSubject> mailingSubjects = mailingSubjectRepository.findByPropertyId(p.getId());
                mailingSubjectSet.addAll(mailingSubjects);
                mailingSet.addAll(
                        mailingSubjects.stream()
                                .map(MailingSubject::getMailing)
                                .collect(Collectors.toList())
                );
                addContactsToSet(p);
                addImagesToSet(p);
            }

            for (Set s : wholeSet){
                print(s);
            }

            return true;
        } catch (Exception e) {
            log.error("Error while storing : ", e);
            System.out.println("Errors occurred. Check in app.log");
            return false;
        }
    }

    private <T> void print(Set<T> objects) throws IOException, IllegalAccessException {
        if (!objects.isEmpty()) {
            List<String> columns;
            Class<?> clazz = objects.iterator().next().getClass();
            String directoryName = storingPath + "/data_" + storingTimestamp + "/" + clazz.getAnnotation(Table.class).name() + ".csv";

            // Used to get associative tables fields
            if (clazz.getSuperclass() != AuditableEntity.class && clazz.getSuperclass() != Object.class) {
                clazz = clazz.getSuperclass();
            }
            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));

            // Add created_at and updated_at from AuditableEntity
            if (clazz.getSuperclass() == AuditableEntity.class){
                fields.addAll(
                        List.of(clazz.getSuperclass().getDeclaredFields())
                );
            }
            FileUtils.createParentDirectories(new File(directoryName));
            String[] headers = fields.stream()
                    .map(f -> {
                        // Handling difference between attribute in camel case and column name in snake case
                        if (f.isAnnotationPresent(JoinColumn.class)){
                            return f.getAnnotation(JoinColumn.class).name();
                        } else {
                            StringBuilder snakeCaseName = new StringBuilder();
                            String name = f.getName();
                            for (char c : name.toCharArray()) {
                                if (Character.isUpperCase(c)) {
                                    snakeCaseName.append('_')
                                            .append(Character.toLowerCase(c));
                                } else {
                                    snakeCaseName.append(c);
                                }
                            }
                            return snakeCaseName.toString();
                        }
                    })
                    .toArray(String[]::new);

            try (
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(directoryName), StandardCharsets.UTF_8));
                    CSVPrinter printer = new CSVPrinter(writer, csvFormat.withHeader(headers))
            ) {
                for (T object : objects) {
                    columns = new ArrayList<>();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object rawValue = field.get(object);
                        if (rawValue instanceof Integer ||
                                rawValue instanceof Long ||
                                rawValue instanceof Double ||
                                rawValue instanceof String ||
                                rawValue instanceof Boolean ||
                                rawValue instanceof Timestamp
                        ) {
                            columns.add(rawValue.toString());
                        } else if (rawValue instanceof String[]) {
                            columns.add(Arrays.toString((String[]) rawValue));
                        } else if (rawValue instanceof EntityToConvert) {
                            columns.add(((EntityToConvert) rawValue).getId().toString());
                        } else {
                            columns.add("");
                        }
                    }
                    printer.printRecord(columns);
                }
            }
        }
    }

    private void addContactsToSet(PropertyEntity p) throws StoringException {
        List<ContactSentPropertyAlert> contactSentPropertyAlerts = contactSentPropertyAlertRepository.findByPropertyId(p.getId());
        contactSentPropertyAlertSet.addAll(contactSentPropertyAlerts);
        contactSet.addAll(
                contactSentPropertyAlerts.stream()
                        .map(ContactSentPropertyAlert::getContact)
                        .collect(Collectors.toList())
        );
        List<ContactPendingPropertyAlert> contactPendingPropertyAlerts = contactPendingPropertyAlertRepository.findByPropertyId(p.getId());
        contactPendingPropertyAlertSet.addAll(contactPendingPropertyAlerts);
        contactSet.addAll(
                contactPendingPropertyAlerts.stream()
                        .map(ContactPendingPropertyAlert::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyBought> propertyBoughts = propertyBoughtRepository.findByPropertyId(p.getId());
        propertyBoughtSet.addAll(propertyBoughts);
        contactSet.addAll(
                propertyBoughts.stream()
                        .map(PropertyBought::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyIntermediateBusiness> propertyIntermediateBusinesses = propertyIntermediateBusinessRepository.findByPropertyId(p.getId());
        propertyIntermediateBusinessSet.addAll(propertyIntermediateBusinesses);
        contactSet.addAll(
                propertyIntermediateBusinesses.stream()
                        .map(PropertyIntermediateBusiness::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyNotaryBuyer> propertyNotaryBuyers = propertyNotaryBuyerRepository.findByPropertyId(p.getId());
        propertyNotaryBuyerSet.addAll(propertyNotaryBuyers);
        contactSet.addAll(
                propertyNotaryBuyers.stream()
                        .map(PropertyNotaryBuyer::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyNotarySeller> propertyNotarySellers = propertyNotarySellerRepository.findByPropertyId(p.getId());
        propertyNotarySellerSet.addAll(propertyNotarySellers);
        contactSet.addAll(
                propertyNotarySellers.stream()
                        .map(PropertyNotarySeller::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyOwner> propertyOwners = propertyOwnerRepository.findByPropertyId(p.getId());
        propertyOwnerSet.addAll(propertyOwners);
        contactSet.addAll(
                propertyOwners.stream()
                        .map(PropertyOwner::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyPartnerAgency> propertyPartnerAgencies = propertyPartnerAgencyRepository.findByPropertyId(p.getId());
        propertyPartnerAgencySet.addAll(propertyPartnerAgencies);
        contactSet.addAll(
                propertyPartnerAgencies.stream()
                        .map(PropertyPartnerAgency::getContact)
                        .collect(Collectors.toList())
        );
        List<PropertyThirdParty> propertyThirdParties = propertyThirdPartyRepository.findByPropertyId(p.getId());
        propertyThirdPartySet.addAll(propertyThirdParties);
        contactSet.addAll(
                propertyThirdParties.stream()
                        .map(PropertyThirdParty::getContact)
                        .collect(Collectors.toList())
        );
    }

    private void addImagesToSet(PropertyEntity p) throws StoringException {
        imageSet.addAll(imageRepository.findByPropertyId(p.getId()));
        List<PropertyPhoto> propertyPhotos = propertyPhotoRepository.findByPropertyId(p.getId());
        propertyPhotoSet.addAll(propertyPhotos);
        imageSet.addAll(
                propertyPhotos.stream()
                        .map(PropertyPhoto::getImage)
                        .collect(Collectors.toList())
        );
        List<PropertyPlan> propertyPlans = propertyPlanRepository.findByPropertyId(p.getId());
        propertyPlanSet.addAll(propertyPlans);
        imageSet.addAll(
                propertyPlans.stream()
                        .map(PropertyPlan::getImage)
                        .collect(Collectors.toList())
        );
    }
}

