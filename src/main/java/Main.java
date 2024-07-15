import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import service.StoringService;

import java.sql.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n     - Kretz property storing tool -\n");
        Scanner scanner = new Scanner(System.in);
        Date lowerLimitDate,
                upperLimitDate;

        try {
            PropertiesConfiguration config = new PropertiesConfiguration("application.properties");
            StoringService copyService = new StoringService(config);

            do {
                System.out.println("\nYou will fetch and store properties data between two dates\n" +
                        "Enter the lower limit date (format 2024-12-31)");
                while (true) {
                    try {
                        lowerLimitDate = Date.valueOf(scanner.nextLine());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("The date format should be 2024-12-31");
                    }
                }

                System.out.println("Enter the upper limit date (format 2024-12-31)");
                while (true) {
                    try {
                        upperLimitDate = Date.valueOf(scanner.nextLine());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("The date format should be 2024-12-31");
                    }
                }

                System.out.println("Proceeding...");
                 if (copyService.store(lowerLimitDate, upperLimitDate)){
                    System.out.println("Data were successfully stored\n");
                 }
                 System.out.println("Enter y for store other data, any other key to quit");
            } while (scanner.nextLine().equalsIgnoreCase("y"));


        } catch (ConfigurationException e) {
            System.out.println("There is an issue with configuration file\n" +
                    "This tool need an application.properties file in this present folder containing: \n" +
                    "   db.url={value}\n" +
                    "   db.username={value}\n" +
                    "   db.password={value}\n" +
                    "   storing-path={value}");
        }
    }
}
