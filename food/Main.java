package food;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodDetails f1 = new FoodDetails();
        int choice = 0;
        while(choice!=4){
            choice = sc.nextInt();
             switch(choice){
            case 1: System.out.println("Enter the order id");
        int orderId = sc.nextInt();
        System.out.println("Enter the customer name ");
        String customerName = sc.next();
        f1.insertOrder(orderId, customerName);
        break;
        case 2 :  System.out.println("The post order of the data ");
        f1.PostOrderDisplayCall();
        break;
        case 3: 
        System.out.println("Enter the key you want to search : ");
        int key = sc.nextInt();
        f1.SearchOrderIdCall(key);
        break;
        case 4:
            System.out.println("exited");
            break;
            case 5:
             System.out.print("Order id's in the sorted order :  ");
                f1.SortOrderIdCall();
                break;
        default: System.out.println("Invalid error");
        }
        }

    }
}