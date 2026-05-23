package food;
import java.util.Scanner;
public class FoodDetails {
    class Node{ 
    public int orderId;
    public String customerName;
    public int customerId;
    public String restaurantName;
    Node left , right;

    Node(int orderid , String customerName){
        this.customerName = customerName;
       this.orderId = orderid;
        right = null;
        left = null;
    }
    }
    Scanner sc = new Scanner(System.in);
    private Node root;
    public FoodDetails(){
        this.root = null;
    }
    public void insertOrder(int orderId , String customerName){
        root = Insert(root, orderId, customerName);
    }
       public Node Insert(Node root , int orderId , String customerName){
        if(root == null){
            return new Node(orderId, customerName);
        }
        if (orderId < root.orderId) {

        root.left = Insert(root.left, orderId, customerName);
    } else if (orderId > root.orderId) {

        root.right = Insert(root.right, orderId, customerName);
    }
    return root;  
    }

    public void PostOrderDisplay(Node root){
        if(root == null){
            return;
        }
        PostOrderDisplay(root.left);
        PostOrderDisplay(root.right);
        System.out.println(root.orderId);
    }

    public void PostOrderDisplayCall(){
        PostOrderDisplay(root);
    }
    public void SearchOrderIdCall(int key){
        root = SearchOrderId(root , key);
        if(root == null){
            System.out.println("No key found ");
        }
        else{
            System.out.println("The key found !!");
        }
    }
    public Node SearchOrderId(Node root , int key ){
        if(root == null){
            return null;
        }
        else if(root.orderId == key){
            return root;
        }
        else if(key < root.orderId){
           return SearchOrderId(root.left, key);
        }
        else {
            return SearchOrderId(root.right, key);
        }
    }
    public void SortOrderId(Node root){
        // simply we can use in order as a sorting method.
        if(root == null){
            return;
        }
        SortOrderId(root.left);
        System.out.print(root.orderId);
        SortOrderId(root.right);
    }
    public void SortOrderIdCall(){
        SortOrderId(root);
    }

}
