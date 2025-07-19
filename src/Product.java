/**
 * Represents a product in the inventory system.
 * Each product has a unique SKU, a name, quantity in stock, price, and category.
 */
public class Product {
    String sku;
    String name;
    int quantity;
    double price;
    String category;

    /**
     * Constructs a new Product with the specified attributes.
     *
     * @param sku      the unique Stock Keeping Unit identifier
     * @param name     the name of the product
     * @param quantity the quantity in stock
     * @param price    the price of the product
     * @param category the category the product belongs to
     */
    public Product(String sku, String name, int quantity, double price, String category){
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    /**
     * Returns the SKU of the product.
     *
     * @return the product's SKU
     */
    public String getSku() {
        return sku;
    }

    /**
     * Sets the SKU of the product.
     *
     * @param sku the new SKU
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Returns the Name of the product.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the new Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the quantity of the product.
     *
     * @return the product's quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the price of the product.
     *
     * @return the products price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price new product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the category of the product.
     *
     * @return the products category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the product
     *
     * @param category new product category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns a string representation of the product, including all its attributes.
     *
     * @return a formatted string containing product details
     */
    @Override
    public String toString() {
        return "Product{" +
                "SKU='" + sku + '\'' +
                ", Name='" + name + '\'' +
                ", Quantity=" + quantity +
                ", Price=$" + price +
                ", Category='" + category + '\'' +
                '}';
    }
}
