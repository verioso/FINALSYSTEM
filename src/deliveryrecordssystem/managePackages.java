package deliveryrecordssystem;

import java.util.Scanner;

public class managePackages {
    static config db = new config();

    static void managePackages() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Manage Packages ---");
            System.out.println("1. Add Package");
            System.out.println("2. View Packages");
            System.out.println("3. Update Package");
            System.out.println("4. Delete Package");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); 
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewPackages();
                    addPackage();
                    break;
                case 2:
                    viewPackages();
                    break;
                case 3:
                    viewPackages();
                    updatePackage();
                    viewPackages();
                    break;
                case 4:
                    viewPackages();
                    deletePackage();
                    viewPackages();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

  
    private static void addPackage() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Package Description: ");
        String description = sc.nextLine().trim();

       
        while (description.isEmpty()) {
            System.out.println("Description cannot be empty. Please enter a valid description.");
            description = sc.nextLine().trim();
        }

     
        double weight = 0;
        while (weight <= 0) {
            System.out.print("Enter Weight in kilos: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid weight.");
                sc.next(); 
            }
            weight = sc.nextDouble();
            if (weight <= 0) {
                System.out.println("Weight must be a positive number.");
            }
        }
         manageDeliveries.viewDeliveries(); 
      
        int deliveryId = 0;
        while (deliveryId <= 0) {
            System.out.print("Enter Delivery ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid Delivery ID.");
                sc.next();
            }
            deliveryId = sc.nextInt();
            if (deliveryId <= 0) {
                System.out.println("Delivery ID must be a positive integer.");
            }
        }


        String checkSql = "SELECT COUNT(*) FROM Delivery WHERE delivery_id = ?";
        int count = db.checkExistence(checkSql, deliveryId);
        if (count == 0) {
            System.out.println("No Delivery found with ID: " + deliveryId);
            return;
        }

    
        String sql = "INSERT INTO Package (description, weight, delivery_id) VALUES (?, ?, ?)";
        db.addRecord(sql, description, weight, deliveryId);
    }

  
    private static void viewPackages() {
        String sql = "SELECT * FROM Package";
        String[] headers = {"Package ID", "Description", "Weight", "Delivery ID"};
        String[] columns = {"package_id", "description", "weight", "delivery_id"};
        db.viewRecords(sql, headers, columns);
    }

    
    private static void updatePackage() {
        Scanner sc = new Scanner(System.in);

     
        int id = 0;
        while (id <= 0) {
            System.out.print("Enter Package ID to update: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid Package ID.");
                sc.next(); 
            }
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("Package ID must be a positive integer.");
            }
        }
        sc.nextLine(); 

        System.out.print("Enter new Description: ");
        String description = sc.nextLine().trim();

      
        while (description.isEmpty()) {
            System.out.println("Description cannot be empty. Please enter a valid description.");
            description = sc.nextLine().trim();
        }

   
        double weight = 0;
        while (weight <= 0) {
            System.out.print("Enter new Weight in kilos: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid weight.");
                sc.next();
            }
            weight = sc.nextDouble();
            if (weight <= 0) {
                System.out.println("Weight must be a positive number.");
            }
        }

        
        int deliveryId = 0;
        while (deliveryId <= 0) {
            System.out.print("Enter new Delivery ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid Delivery ID.");
                sc.next(); 
            }
            deliveryId = sc.nextInt();
            if (deliveryId <= 0) {
                System.out.println("Delivery ID must be a positive integer.");
            }
        }

        
        String checkSql = "SELECT COUNT(*) FROM Delivery WHERE delivery_id = ?";
        int count = db.checkExistence(checkSql, deliveryId);
        if (count == 0) {
            System.out.println("No Delivery found with ID: " + deliveryId);
            return;
        }

       
        String sql = "UPDATE Package SET description = ?, weight = ?, delivery_id = ? WHERE package_id = ?";
        db.updateRecord(sql, description, weight, deliveryId, id);
    }

  
    private static void deletePackage() {
        Scanner sc = new Scanner(System.in);

       
        int id = 0;
        while (id <= 0) {
            System.out.print("Enter Package ID to delete: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid Package ID.");
                sc.next();
            }
            id = sc.nextInt();
            if (id <= 0) {
                System.out.println("Package ID must be a positive integer.");
            }
        }

       
        String sql = "DELETE FROM Package WHERE package_id = ?";
        db.deleteRecord(sql, id);
    }
}
