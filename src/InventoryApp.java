import java.util.List;
import java.util.Scanner;


/**
 * InventoryApp is the main class that runs the inventory management system.
 * It provides a command-line interface for users to add, remove, search, sort,
 * filter, and manage product data stored in memory.
 */
public class InventoryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryManager manager = new InventoryManager();

    /**
     * Entry point of the application.
     * Displays a menu and handles user interactions for inventory operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("========= Inventory Menu =========");
            System.out.println("1. Add a product");
            System.out.println("2. Remove a product");
            System.out.println("3. View inventory");
            System.out.println("4. Search by SKU");
            System.out.println("5. Filter inventory");
            System.out.println("6. Sort inventory");
            System.out.println("7. Save to file");
            System.out.println("8. Load from file");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            try{
                int userInput = Integer.parseInt(scanner.nextLine());
                switch(userInput){
                    case 1:
                        Product p = promptForProduct();
                        manager.addProduct(p);
                        break;
                    case 2:
                        System.out.println("Enter product sku: ");
                        String sku = scanner.nextLine().trim();
                        manager.removeProduct(sku);
                        break;
                    case 3:
                        manager.printAllProducts();
                        break;
                    case 4:
                        System.out.println("Enter product sku: ");
                        String querySku = scanner.nextLine().trim();
                        manager.searchBySku(querySku);
                        break;
                    case 5:
                        filterMenu();
                        break;
                    case 6:
                        sortMenu();
                        break;
                    case 7:
                        manager.saveToFile();
                        break;
                    case 8:
                        manager.loadFromFile();
                        break;
                    case 9:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }

        }
    }


    /**
     * Prompts the user for product information through the console.
     * Allows manual SKU entry or auto-generates one if left blank.
     *
     * @return a new Product instance based on user input
     */
    private static Product promptForProduct() {
        while(true){
            try{
                System.out.print("Enter product name: ");
                String name = scanner.nextLine().trim();

                System.out.print("Enter product price: ");
                Double price = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter product quantity: ");
                Integer quantity = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter product category: ");
                String category = scanner.nextLine().trim();

                System.out.print("Enter product sku (leave blank to auto-generate): ");
                String sku = scanner.nextLine().trim();

                if (sku.trim().isEmpty()) {
                    return manager.createProduct(name, quantity, price, category);
                } else {
                    return new Product(sku, name, quantity, price, category);
                }
            } catch (Exception e){
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Prints a list of products to the console.
     * If the list is empty or null, it prints a message indicating no products were found.
     *
     * @param products the list of products to print
     */
    private static void printProductsList(List<Product> products){
        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : products) {
            System.out.println(p);
        }
    }

    /**
     * Displays filtering options to the user and handles filter input.
     * Supports filtering by category, name, exact price, and price range.
     */
    private static void filterMenu(){
        System.out.println("Select filtering method");
        System.out.println("1. Filter by Category");
        System.out.println("2. Filter by Name");
        System.out.println("3. Filter by Exact Price");
        System.out.println("4. Filter by Price Range");
        try{
            int filterChoice = Integer.parseInt(scanner.nextLine());
            switch(filterChoice){
                case 1:
                    System.out.println("Enter Category:");
                    String userCategory = scanner.nextLine().trim();
                    manager.filterByCategory(userCategory);
                    break;
                case 2:
                    System.out.println("Enter Name:");
                    String userName = scanner.nextLine().trim();
                    manager.filterByName(userName);
                    break;
                case 3:
                    System.out.println("Enter Exact Price:");
                    double userExactPrice = Double.parseDouble(scanner.nextLine().trim());
                    manager.filterByExactPrice(userExactPrice);
                    break;
                case 4:
                    System.out.println("Enter Minimum:");
                    double min = Double.parseDouble(scanner.nextLine().trim());
                    System.out.println("Enter Maximum:");
                    double max = Double.parseDouble(scanner.nextLine().trim());
                    manager.filterByPriceRange(min, max);
                    break;
                default:
                    System.out.println("Invalid input.");

            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
    }

    /**
     * Displays sorting options to the user and allows sorting the inventory
     * by name, price, category, or quantity in ascending or descending order.
     */
    private static void sortMenu(){
        boolean running = true;
        while(running){
            boolean ascending;
            while(true){
                System.out.println("Ascending or descending: ");
                System.out.println("1. Ascending");
                System.out.println("2. Descending");
                try{
                    int ascendingChoice = Integer.valueOf(scanner.nextLine());
                    if(ascendingChoice == 1){
                        ascending = true;
                        break;
                    } else if ( ascendingChoice == 2){
                        ascending = false;
                        break;
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid number. Try again.");
                }
            }

            System.out.println("Choose sorting method");
            System.out.println("1. Name");
            System.out.println("2. Price");
            System.out.println("3. Category");
            System.out.println("4. Quantity");
            System.out.print("Enter your choice: ");
            try{
                int sortChoice = Integer.valueOf(scanner.nextLine());

                switch(sortChoice){
                    case 1:
                        manager.sortByName(ascending);
                        break;
                    case 2:
                        manager.sortByPrice(ascending);
                        break;
                    case 3:
                        manager.sortByCategory(ascending);
                        break;
                    case 4:
                        manager.sortByQuantity(ascending);
                        break;
                    default:
                        System.out.println("Invalid sorting method");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again");
            }

            System.out.println("Sort again? (y/n)");
            String again = scanner.nextLine().trim();
            if (again.equalsIgnoreCase("n")) running = false;
        }
    }
}