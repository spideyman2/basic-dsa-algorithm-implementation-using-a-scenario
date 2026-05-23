package food;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodDetails f1 = new FoodDetails();
        
        f1.setupLocations();
        
        Segment revenueTree = new Segment(f1.getNumPlaces());
        
        int choice = 0;
        
        while(choice != 8) {
            System.out.println("\n1. Place Food Order");
            System.out.println("2. View All Orders (Post-Order)");
            System.out.println("3. View All Orders (Pre-Order)");
            System.out.println("4. Search Order Details");
            System.out.println("5. View Sorted Order IDs");
            System.out.println("6. Check Revenue by Zone Range");
            System.out.println("7. Calculate Shortest Delivery Route");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            
            choice = sc.nextInt();
            switch(choice) {
                case 1: 
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    System.out.print("Enter Customer Name: ");
                    String customerName = sc.next();
                    System.out.print("Enter Food Item: ");
                    String foodItem = sc.next();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    
                    System.out.println("Available Locations:");
                    f1.printAvailableLocations();
                    System.out.print("Enter Delivery Location ID (Index): ");
                    int locIndex = sc.nextInt();
                    
                    if (locIndex >= 0 && locIndex < f1.getNumPlaces()) {
                        f1.insertOrder(orderId, customerName, foodItem, price, locIndex);
                        revenueTree.addRevenue(locIndex, price);
                    } else {
                        System.out.println("Order failed. Invalid location index.");
                    }
                    break;
                case 2: 
                    System.out.println("Orders Data (Post-Order):");
                    f1.PostOrderDisplayCall();
                    break;
                case 3: 
                    System.out.println("Orders Data (Pre-Order):");
                    f1.PreOrderDisplayCall();
                    break;
                case 4: 
                    System.out.print("Enter Order ID to search: ");
                    int key = sc.nextInt();
                    f1.SearchOrderIdCall(key);
                    break;
                case 5:
                    System.out.print("Processed Order IDs: ");
                    f1.SortOrderIdCall();
                    System.out.println();
                    break;
                case 6:
                    System.out.print("Enter starting zone index: ");
                    int startZone = sc.nextInt();
                    System.out.print("Enter ending zone index: ");
                    int endZone = sc.nextInt();
                    double total = revenueTree.getRevenueRange(startZone, endZone);
                    System.out.println("Total revenue for zones " + startZone + " to " + endZone + ": $" + total);
                    break;
                case 7:
                    System.out.println("Available Locations:");
                    f1.printAvailableLocations();
                    System.out.print("Enter destination index to find shortest route from Restaurant: ");
                    int targetZone = sc.nextInt();
                    f1.findShortestDeliveryRoute(targetZone);
                    break;
                case 8:
                    System.out.println("System terminated.");
                    break;
                default: 
                    System.out.println("Invalid choice.");
            }
        }
    }
}