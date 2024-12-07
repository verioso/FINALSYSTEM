package deliveryrecordssystem;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class manageDeliveries {
    static config db = new config();

    static void manageDeliveries() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Manage Deliveries ---");
            System.out.println("1. Add Delivery");
            System.out.println("2. View Deliveries");
            System.out.println("3. Update Delivery");
            System.out.println("4. Delete Delivery");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

          
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewDeliveries();
                    addDelivery();
                    break;
                case 2:
                    viewDeliveries();
                    break;
                case 3:
                    viewDeliveries();
                    updateDelivery();
                    viewDeliveries();
                    break;
                case 4:
                    viewDeliveries(); 
                    deleteDelivery();
                    viewDeliveries();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

   
    private static void addDelivery() {
        Scanner sc = new Scanner(System.in);

        String date = "";
       
        while (!isValidDate(date)) {
            System.out.print("Enter Delivery Date (YYYY-MM-DD): ");
            date = sc.nextLine();
            if (!isValidDate(date)) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }

        System.out.print("Enter Status: ");
        String status = sc.nextLine();
        manageRecipients.viewRecipients();
        int recipientId = 0;
        
        while (recipientId <= 0) {
            System.out.print("Enter Recipient ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for Recipient ID.");
                sc.next();
            }
            recipientId = sc.nextInt();
            if (recipientId <= 0) {
                System.out.println("Recipient ID must be a positive integer.");
            }
        }

        
        String sql = "INSERT INTO Delivery (delivery_date, status, recipient_id) VALUES (?, ?, ?)";
        db.addRecord(sql, date, status, recipientId);
    }

    
    static void viewDeliveries() {
        String sql = "SELECT * FROM Delivery";
        String[] headers = {"Delivery ID", "Date", "Status", "Recipient ID"};
        String[] columns = {"delivery_id", "delivery_date", "status", "recipient_id"};
        db.viewRecords(sql, headers, columns);
    }

    
    private static void updateDelivery() {
        Scanner sc = new Scanner(System.in);

        int id = 0;
        
        while (id <= 0) {
            System.out.print("Enter Delivery ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for Delivery ID.");
                sc.next(); 
            }
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("Delivery ID must be a positive integer.");
            }
        }

        sc.nextLine();

        String date = "";
        
        while (!isValidDate(date)) {
            System.out.print("Enter new Delivery Date (YYYY-MM-DD): ");
            date = sc.nextLine();
            if (!isValidDate(date)) {
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD.");
            }
        }

        System.out.print("Enter new Status: ");
        String status = sc.nextLine();

        int recipientId = 0;
       
        while (recipientId <= 0) {
            System.out.print("Enter new Recipient ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for Recipient ID.");
                sc.next(); 
            }
            recipientId = sc.nextInt();
            if (recipientId <= 0) {
                System.out.println("Recipient ID must be a positive integer.");
            }
        }

        
        String sql = "UPDATE Delivery SET delivery_date = ?, status = ?, recipient_id = ? WHERE delivery_id = ?";
        db.updateRecord(sql, date, status, recipientId, id);
    }

    
    private static void deleteDelivery() {
        Scanner sc = new Scanner(System.in);

        int id = 0;
       
        while (id <= 0) {
            System.out.print("Enter Delivery ID to delete: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for Delivery ID.");
                sc.next(); 
            }
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("Delivery ID must be a positive integer.");
            }
        }

        
        String sql = "DELETE FROM Delivery WHERE delivery_id = ?";
        db.deleteRecord(sql, id);
    }

   
    private static boolean isValidDate(String date) {
        
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
