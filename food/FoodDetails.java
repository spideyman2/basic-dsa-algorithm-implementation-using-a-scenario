package food;

import java.util.Scanner;

public class FoodDetails {
    class Node { 
        public int orderId;
        public String customerName;
        public String foodItem;
        public double price;
        public int destinationIndex;
        Node left, right;

        Node(int orderId, String customerName, String foodItem, double price, int destinationIndex) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.foodItem = foodItem;
            this.price = price;
            this.destinationIndex = destinationIndex;
            right = null;
            left = null;
        }
    }

    Scanner sc = new Scanner(System.in);
    private Node root;
    private String[] places;
    private int[][] adjMatrix;
    private int numPlaces;

    public FoodDetails() {
        this.root = null;
    }

    public void setupLocations() {
        System.out.print("Enter the number of delivery zones (including restaurant): ");
        numPlaces = sc.nextInt();
        
        places = new String[numPlaces];
        adjMatrix = new int[numPlaces][numPlaces];
        
        System.out.println("Enter the names of the " + numPlaces + " places (First should be the Restaurant):");
        for (int i = 0; i < numPlaces; i++) {
            places[i] = sc.next();
        }
        
        System.out.println("Enter the weighted adjacency matrix for distances (" + numPlaces + "x" + numPlaces + "):");
        for (int i = 0; i < numPlaces; i++) {
            for (int j = 0; j < numPlaces; j++) {
                adjMatrix[i][j] = sc.nextInt();
            }
        }
    }

    public void printAvailableLocations() {
        for (int i = 0; i < numPlaces; i++) {
            System.out.println(i + ": " + places[i]);
        }
    }

    public void insertOrder(int orderId, String customerName, String foodItem, double price, int destinationIndex) {
        if (destinationIndex < 0 || destinationIndex >= numPlaces) {
            System.out.println("Order failed. Invalid location index.");
            return;
        }
        root = Insert(root, orderId, customerName, foodItem, price, destinationIndex);
        System.out.println("Order placed successfully.");
    }

    private Node Insert(Node root, int orderId, String customerName, String foodItem, double price, int destinationIndex) {
        if (root == null) {
            return new Node(orderId, customerName, foodItem, price, destinationIndex);
        }
        if (orderId < root.orderId) {
            root.left = Insert(root.left, orderId, customerName, foodItem, price, destinationIndex);
        } else if (orderId > root.orderId) {
            root.right = Insert(root.right, orderId, customerName, foodItem, price, destinationIndex);
        } else {
            System.out.println("Order ID already exists.");
        }
        return root;  
    }

    public void PostOrderDisplayCall() {
        PostOrderDisplay(root);
    }

    private void PostOrderDisplay(Node root) {
        if (root == null) {
            return;
        }
        PostOrderDisplay(root.left);
        PostOrderDisplay(root.right);
        printOrderDetails(root);
    }

    public void PreOrderDisplayCall() {
        PreOrderDisplay(root);
    }

    private void PreOrderDisplay(Node root) {
        if (root == null) {
            return;
        }
        printOrderDetails(root);
        PreOrderDisplay(root.left);
        PreOrderDisplay(root.right);
    }

    public void SearchOrderIdCall(int key) {
        Node resultNode = SearchOrderId(root, key);
        if (resultNode == null) {
            System.out.println("Order ID " + key + " not found.");
        } else {
            System.out.println("Order Found:");
            printOrderDetails(resultNode);
        }
    }

    private Node SearchOrderId(Node root, int key) {
        if (root == null) {
            return null;
        } else if (root.orderId == key) {
            return root;
        } else if (key < root.orderId) {
            return SearchOrderId(root.left, key);
        } else {
            return SearchOrderId(root.right, key);
        }
    }

    private void printOrderDetails(Node node) {
        int distance = adjMatrix[0][node.destinationIndex];
        System.out.println("ID: " + node.orderId + " | Name: " + node.customerName + 
                           " | Item: " + node.foodItem + " | Price: $" + node.price + 
                           " | Destination: " + places[node.destinationIndex] + 
                           " | Delivery Distance: " + distance + " units");
    }

    public void SortOrderIdCall() {
        SortOrderId(root);
    }

    private void SortOrderId(Node root) {
        if (root == null) {
            return;
        }
        SortOrderId(root.left);
        System.out.print(root.orderId + " ");
        SortOrderId(root.right);
    }

    public int getNumPlaces() {
        return numPlaces;
    }

    public void findShortestDeliveryRoute(int targetIndex) {
        if (numPlaces == 0 || targetIndex < 0 || targetIndex >= numPlaces) {
            System.out.println("Invalid delivery destination.");
            return;
        }

        int[] dist = new int[numPlaces];
        boolean[] visited = new boolean[numPlaces];

        for (int i = 0; i < numPlaces; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[0] = 0;

        for (int i = 0; i < numPlaces - 1; i++) {
            int u = getMinDistanceNode(dist, visited);
            if (u == -1) break; 
            
            visited[u] = true;

            for (int v = 0; v < numPlaces; v++) {
                if (!visited[v] && adjMatrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + adjMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjMatrix[u][v];
                }
            }
        }

        if (dist[targetIndex] == Integer.MAX_VALUE) {
            System.out.println("Error: Location is unreachable from the restaurant.");
        } else {
            System.out.println("Total Delivery Distance: " + dist[targetIndex] + " units");
        }
    }

    private int getMinDistanceNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numPlaces; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}