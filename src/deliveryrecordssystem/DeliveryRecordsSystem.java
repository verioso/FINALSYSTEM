package deliveryrecordssystem;

import java.util.Scanner;

public class DeliveryRecordsSystem {
    static config db = new config(); 
    static manageDeliveries ManageDeliveries = new manageDeliveries();
    static manageRecipients ManageRecipients = new manageRecipients();
    static managePackages ManagePackages = new managePackages();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n===== Delivery Records System =====");
            System.out.println("1. Manage Deliveries");
            System.out.println("2. Manage Recipients");
            System.out.println("3. Manage Packages");
            System.out.println("4. Reports");
            System.out.println("5. Exit");

            
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); 
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ManageDeliveries.manageDeliveries(); 
                    break;
                case 2:
                    ManageRecipients.manageRecipients(); 
                    break;
                case 3:
                    ManagePackages.managePackages(); 
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    private static void generateReports() {
        System.out.println("\n--- Reports ---");
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        
        while (choice != 1 && choice != 2) {
            System.out.println("1. View All Delivery Reports");
            System.out.println("2. View Specific Delivery Report");
            System.out.print("Enter your choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); 
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewAllDeliveryReports();
                    break;
                case 2:
                    viewSpecificDeliveryReport();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void viewAllDeliveryReports() {
        String sql = 
            "SELECT d.delivery_id, d.delivery_date, d.status, r.name, r.address, r.contact_number " +
            "FROM Delivery d " +
            "LEFT JOIN Recipient r ON d.recipient_id = r.recipient_id";

        String[] headers = {"Delivery ID", "Date", "Status", "Recipient Name", "Address", "Contact"};
        String[] columns = {"delivery_id", "delivery_date", "status", "name", "address", "contact_number"};

        db.viewRecords(sql, headers, columns);
    }

    private static void viewSpecificDeliveryReport() {
        Scanner sc = new Scanner(System.in);
        int deliveryId = -1;
       manageDeliveries.viewDeliveries(); 
        
        while (deliveryId <= 0) {
            System.out.print("Enter Delivery ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid Delivery ID.");
                sc.next(); 
            }
            deliveryId = sc.nextInt();

            if (deliveryId <= 0) {
                System.out.println("Delivery ID must be a positive number. Please try again.");
            }
        }

        String sql = 
            "SELECT d.delivery_id, d.delivery_date, d.status, r.name, r.address, r.contact_number " +
            "FROM Delivery d " +
            "LEFT JOIN Recipient r ON d.recipient_id = r.recipient_id " +
            "WHERE d.delivery_id = ?";

        String[] headers = {"Delivery ID", "Date", "Status", "Recipient Name", "Address", "Contact"};
        String[] columns = {"delivery_id", "delivery_date", "status", "name", "address", "contact_number"};

        db.viewRecords(sql, headers, columns, deliveryId); 
    }
}
