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
    }

    public void store(Date lowerLimit, Date upperLimit) {
        storingTimestamp = System.currentTimeMillis();

        try {
            propertiesSet.addAll(propertyRepository.readBetweenTwoDates(lowerLimit, upperLimit));

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
                mailingSet.addAll(
                        mailingSubjectRepository.findByPropertyId(p.getId())
                                .stream()
                                .map(MailingSubject::getMailing)
                                .collect(Collectors.toList())
                );
                addContactsToSet(p);
                addImagesToSet(p);
            }

            print(propertiesSet);
            print(userSet);
            print(districtSet);
            print(mandateSet);
            print(imageSet);
            print(documentSet);
            print(visitSet);
            print(oldReferenceEntitySet);
            print(contactSet);
            print(mailingSet);

        } catch (Exception e) {
            log.error("Error while storing : ", e);
            System.out.println("Errors occurred. Check in app.log");
        }
    }

    private <T> void print(Set<T> objects) throws IOException, IllegalAccessException {
        if (!objects.isEmpty()) {
            List<String> columns;
            Class<?> clazz = objects.iterator().next().getClass();
            Field[] fields = clazz.getDeclaredFields();
            String directoryName = storingPath + "/data_" + storingTimestamp +
                    "/" + clazz.getName().toLowerCase().split("\\.")[1].split("entity")[0] + ".csv";
            FileUtils.createParentDirectories(new File(directoryName));
            String[] headers = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);

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
        contactSet.addAll(
                contactSentPropertyAlertRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(ContactSentPropertyAlert::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                contactPendingPropertyAlertRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(ContactPendingPropertyAlert::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyBoughtRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyBought::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyIntermediateBusinessRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyIntermediateBusiness::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyNotaryBuyerRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyNotaryBuyer::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyNotarySellerRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyNotarySeller::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyOwnerRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyOwner::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyPartnerAgencyRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyPartnerAgency::getContact)
                        .collect(Collectors.toList())
        );
        contactSet.addAll(
                propertyThirdPartyRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyThirdParty::getContact)
                        .collect(Collectors.toList())
        );
    }

    private void addImagesToSet(PropertyEntity p) throws StoringException {
        imageSet.addAll(imageRepository.findByPropertyId(p.getId()));
        imageSet.addAll(
                propertyPhotoRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyPhoto::getImage)
                        .collect(Collectors.toList())
        );
        imageSet.addAll(
                propertyPlanRepository.findByPropertyId(p.getId())
                        .stream()
                        .map(PropertyPlan::getImage)
                        .collect(Collectors.toList())
        );
    }
}

