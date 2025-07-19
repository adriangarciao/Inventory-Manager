public class InventoryTest {
    public static void main(String[] args) {
        InventoryManager inventory = new InventoryManager();

        // Add products
        System.out.println("Adding products:");
        inventory.addProduct(new Product("A123", "Keyboard", 10, 29.99, "Electronics"));
        inventory.addProduct(new Product("B456", "Mouse", 5, 15.99, "Electronics"));
        inventory.addProduct(new Product("A123", "Mechanical Keyboard", 8, 79.99, "Electronics")); // duplicate SKU test

        // Update quantity
        System.out.println("\nUpdating quantity:");
        inventory.updateQuantity("B456", 12);
        inventory.updateQuantity("X999", 5); // SKU not found test

        // Remove product
        System.out.println("\nRemoving product:");
        inventory.removeProduct("A123");
        inventory.removeProduct("Z999"); // SKU not found test

        // Search for product
        System.out.println("\nSearching for product:");
        Product found = inventory.searchBySku("B456");
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Product not found.");
        }

        // Save to file
        System.out.println("\nSaving inventory to file:");
        inventory.saveToFile();

        // Clear current inventory
        System.out.println("\nClearing inventory in memory.");
        inventory.clearInventory(); // You’ll need to create this method or manually clear your list

        // Load from file
        System.out.println("\nLoading inventory from file:");
        inventory.loadFromFile();

        // Print all loaded products
        System.out.println("\nLoaded products:");
        inventory.printAllProducts(); // You’ll need to create this helper method

        InventoryManager manager = new InventoryManager();
        Product p1 = manager.createProduct("Laptop", 5, 999.99, "Electronics");
        inventory.addProduct(p1);

        Product p2 = manager.createProduct("Phone", 10, 499.99, "Electronics");
        inventory.addProduct(p2);

        inventory.printAllProducts();

    }
}

