import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The InventoryManager class handles all core inventory operations such as
 * adding, removing, searching, filtering, sorting, and managing products.
 *
 * It maintains a list of Product objects and provides utility methods
 * to interact with the inventory data.
 *
 * This class serves as the backend logic for the inventory system,
 * separate from the CLI or user interface.
 */
public class InventoryManager {
    List<Product> inventory;
    private Random random = new Random();

    /**
     * Initializes an empty inventory list.
     */
    public InventoryManager() {
        inventory = new ArrayList<>();
    }

    /**
     * Creates a new Product with a unique SKU.
     *
     * @param name     the product name
     * @param quantity the quantity in stock
     * @param price    the product price
     * @param category the product category
     * @return a Product object with a generated SKU
     */
    public Product createProduct(String name, int quantity, double price, String category){
        String sku = generateUniqueSku();
        Product product = new Product(sku, name, quantity, price, category);
        return product;
    }

    /**
     * Adds a new product to the inventory if its SKU is not already present.
     *
     * @param product the Product to add
     */
    public void addProduct(Product product){
        String newSku = product.getSku();
        for (int i = 0; i < inventory.size(); i++) {
            Product existingProduct = inventory.get(i);
            if (existingProduct.getSku().equals(newSku)) {
                System.out.println("Product with SKU " + newSku + " already exist.");
                return;
            }
        }
        inventory.add(product);
        System.out.println("Product with SKU " + newSku + " added.");
    }

    /**
     * Removes a  product from the inventory if its SKU is found.
     *
     * @param sku the SKU to remove
     */
    public void removeProduct(String sku) {
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getSku().equals(sku)) {
                inventory.remove(i);
                System.out.println("Product with SKU " + sku + " removed.");
                return;
            }
        }
        System.out.println("No product with SKU: " + sku + " found.");
    }

    /**
     * Updates quantity of a product in inventory.
     *
     * @param sku SKU of Product to be updated
     * @param newQty New Quantity
     */
    public void updateQuantity(String sku, int newQty){
        if(newQty < 0){
            System.out.println("Quantity cannot be under than 0");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getSku().equals(sku)) {
                product.setQuantity(newQty);
                System.out.println("Updated: " + product.getName() + " quantity to " + newQty);
                return;
            }
        }
        System.out.println("No product with SKU: " + sku + " found.");
    }

    /**
     * Searches for Product in inventory by its SKU.
     *
     * @param sku Product sku to search for
     * @return returns null if no product with sku found
     */
    public Product searchBySku(String sku){
        sku = sku.toUpperCase();
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getSku().equals(sku)){
                return product;
            }
        }
        return null;
    }

    /**
     * Filters products based on matching name (case-insensitive).
     *
     * @param query the name to filter by
     * @return a list of products matching the name
     */
    public List<Product> filterByName(String query) {
        if (inventory == null || inventory.size() == 0) return null;
        query = query.toLowerCase();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getName() == null) continue;
            if (product.getName().toLowerCase().contains(query)) {
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Filters products based on matching category (case-insensitive).
     *
     * @param query the category to filter by
     * @return a list of products matching the category
     */
    public List<Product> filterByCategory(String query){
        if (inventory == null || inventory.size() == 0) return new ArrayList<>();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getCategory() == null) continue;
            if (product.getCategory().equalsIgnoreCase(query)) {
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Filters products based on matching Exact Price.
     *
     * @param query the exact price to filter by
     * @return a list of products matching the exact price
     */
    public List<Product> filterByExactPrice(Double query){
        if (inventory == null || inventory.size() == 0) return new ArrayList<>();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (Double.compare(product.getPrice(), query) == 0) {
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Filters products based on price range.
     *
     * @param min minimum price range to be filtered by
     * @param max maximum price range to be filtered by
     * @return a list of products matching the price range
     */
    public List<Product> filterByPriceRange(double min, double max){
        if(min > max) return new ArrayList<>();
        if (inventory == null || inventory.size() == 0) return new ArrayList<>();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getPrice() >= min && product.getPrice() <= max) {
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Clears inventory.
     */
    public void clearInventory() {
        inventory.clear();
    }

    /**
     * Prints all Products in the inventory.
     */
    public void printAllProducts() {
        for (Product p : inventory) {
            System.out.println(p);
        }
    }

    /**
     * Generates a unique SKU for a product.
     *
     * @return returns unique sku
     */
    public String generateUniqueSku(){
        while (true) {
            char letter = (char) ('A' + random.nextInt(26));
            StringBuilder digits = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                digits.append(random.nextInt(10));
            }
            String sku = letter + digits.toString();

            boolean duplicate = false;
            for (Product product : inventory) {
                if (product.getSku().equals(sku)) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) return sku;
        }
    }

    /**
     * Sorts the inventory by product name in ascending or descending order.
     * Products with null names are placed at the beginning or end depending on the sort order.
     *
     * @param ascending if true, sorts in ascending (A–Z) order; if false, in descending (Z–A)
     */
    public void sortByName(boolean ascending){
        if (inventory == null || inventory.size() == 0) return;
        inventory.sort((a,b) -> {
            if (a.getName() == null && b.getName() == null) return 0;
            if (a.getName() == null) return -1;
            if (b.getName() == null) return 1;
            return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
        });
        if (!ascending){Collections.reverse(inventory);}
    }

    /**
     * Sorts the inventory by product price in ascending or descending order.
     *
     * @param ascending if true, sorts in ascending price order; if false, in descending price
     */
    public void sortByPrice(boolean ascending){
        if (inventory == null || inventory.size() == 0) return;
        inventory.sort((a,b) -> Double.compare(a.getPrice(), b.getPrice()));
        if (!ascending){Collections.reverse(inventory);}
    }

    /**
     * Sorts the inventory by product category in ascending or descending order.
     * Products with null categories are placed at the beginning or end depending on the sort order.
     *
     * @param ascending if true, sorts in ascending (A–Z) order; if false, in descending (Z–A)
     */
    public void sortByCategory(boolean ascending){
        if (inventory == null || inventory.size() == 0) return;
        inventory.sort((a,b) -> {
            if (a.getCategory() == null && b.getCategory() == null) return 0;
            if (a.getCategory() == null) return -1;
            if (b.getCategory() == null) return 1;
            return a.getCategory().toLowerCase().compareTo(b.getCategory().toLowerCase());
        });
        if (!ascending){Collections.reverse(inventory);}
    }

    /**
     * Sorts the inventory by product quantity in ascending or descending order.
     *
     * @param ascending if true, sorts in ascending quantity order; if false, in descending quantity
     */
    public void sortByQuantity(boolean ascending){
        if (inventory == null || inventory.size() == 0) return;
        inventory.sort((a,b) -> Integer.compare(a.getQuantity(), b.getQuantity()));
        if (!ascending){Collections.reverse(inventory);}
    }

    /**
     * Saves the current inventory to a text file named "inventory.txt".
     * Each product is written in CSV format: sku,name,quantity,price,category.
     * Overwrites the file if it already exists.
     */
    public void saveToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("inventory.txt"))) {
            for (Product product : inventory) {
                // Format: sku,name,quantity,price,category
                String line = product.getSku() + "," +
                        product.getName() + "," +
                        product.getQuantity() + "," +
                        product.getPrice() + "," +
                        product.getCategory();
                writer.println(line);
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads inventory data from a file named "inventory.txt".
     * Each line is expected to be in CSV format: sku,name,quantity,price,category.
     * Skips malformed lines and reports any formatting or parsing errors.
     * Adds successfully parsed products to the current inventory list.
     */
    public void loadFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))){
            String line;
            int lineNumber = 0;
            while((line = reader.readLine()) != null){
                lineNumber++;
                String[] parts = line.split(",");
                if(parts.length != 5){
                    System.out.println("Line " + lineNumber + " is not formatted correctly: " + line);
                    continue; // Skip this malformed line
                }
                String sku = parts[0];
                String name = parts[1];

                int quantity;
                double price;
                try {
                    quantity = Integer.parseInt(parts[2]);
                    price = Double.parseDouble(parts[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Line " + lineNumber + " has invalid number format: " + line);
                    continue; // Skip this malformed line
                }

                String category = parts[4];

                // Create product and add to inventory
                Product product = new Product(sku, name, quantity, price, category);
                inventory.add(product);

            }
            System.out.println("Inventory loaded successfully");
        } catch (IOException e){
            System.out.println("Error loading inventory" + e.getMessage());
        }
    }
}
