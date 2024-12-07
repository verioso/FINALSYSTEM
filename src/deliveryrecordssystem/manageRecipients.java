package deliveryrecordssystem;

import java.util.Scanner;

public class manageRecipients {
    static config db = new config();

    static void manageRecipients() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Manage Recipients ---");
            System.out.println("1. Add Recipient");
            System.out.println("2. View Recipients");
            System.out.println("3. Update Recipient");
            System.out.println("4. Delete Recipient");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); 
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                     viewRecipients();
                    addRecipient();
                    break;
                case 2:
                    viewRecipients();
                    break;
                case 3:
                    viewRecipients();
                    updateRecipient();
                     viewRecipients();
                    break;
                case 4:
                    viewRecipients();
                    deleteRecipient();
                     viewRecipients();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    
    private static void addRecipient() {
        Scanner sc = new Scanner(System.in);

        String name = "";
        
        while (name.trim().isEmpty()) {
            System.out.print("Enter Recipient Name: ");
            name = sc.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }

        String address = "";
        
        while (address.trim().isEmpty()) {
            System.out.print("Enter Address: ");
            address = sc.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Address cannot be empty. Please enter a valid address.");
            }
        }

        String contactNumber = "";
        
        while (contactNumber.trim().isEmpty() || !contactNumber.matches("\\d+")) {
            System.out.print("Enter Contact Number: ");
            contactNumber = sc.nextLine();
            if (contactNumber.trim().isEmpty()) {
                System.out.println("Contact number cannot be empty.");
            } else if (!contactNumber.matches("\\d+")) {
                System.out.println("Contact number must be numeric.");
            }
        }

      
        String sql = "INSERT INTO Recipient (name, address, contact_number) VALUES (?, ?, ?)";
        db.addRecord(sql, name, address, contactNumber);
    }

   
    static void viewRecipients() {
        String sql = "SELECT * FROM Recipient";
        String[] headers = {"Recipient ID", "Name", "Address", "Contact Number"};
        String[] columns = {"recipient_id", "name", "address", "contact_number"};
        db.viewRecords(sql, headers, columns);
    }


    private static void updateRecipient() {
        Scanner sc = new Scanner(System.in);

        int id = 0;
 
        while (id <= 0) {
            System.out.print("Enter Recipient ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for Recipient ID.");
                sc.next(); 
            }
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("Recipient ID must be a positive integer.");
            }
        }
        sc.nextLine(); 

        String name = "";
        
        while (name.trim().isEmpty()) {
            System.out.print("Enter new Name: ");
            name = sc.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            }
        }

        String address = "";
       
        while (address.trim().isEmpty()) {
            System.out.print("Enter new Address: ");
            address = sc.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Address cannot be empty. Please enter a valid address.");
            }
        }

        String contactNumber = "";
        
        while (contactNumber.trim().isEmpty() || !contactNumber.matches("\\d+")) {
            System.out.print("Enter new Contact Number: ");
            contactNumber = sc.nextLine();
            if (contactNumber.trim().isEmpty()) {
                System.out.println("Contact number cannot be empty.");
            } else if (!contactNumber.matches("\\d+")) {
                System.out.println("Contact number must be numeric.");
            }
        }

     
        String sql = "UPDATE Recipient SET name = ?, address = ?, contact_number = ? WHERE recipient_id = ?";
        db.updateRecord(sql, name, address, contactNumber, id);
    }

  
    private static void deleteRecipient() {
        Scanner sc = new Scanner(System.in);

        int id = 0;
    
        while (id <= 0) {
            System.out.print("Enter Recipient ID to delete: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer for Recipient ID.");
                sc.next(); 
            }
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("Recipient ID must be a positive integer.");
            }
        }

    
        String sql = "DELETE FROM Recipient WHERE recipient_id = ?";
        db.deleteRecord(sql, id);
    }
}
