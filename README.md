# Inventory Management System (Java Console App)

This is a simple inventory management system built in Java that allows users to add, remove, search, filter, sort, and persist product data using a command-line interface.

## 🚀 Features

- ✅ Add new products (auto-generate or enter custom SKU)
- ✅ Remove products by SKU
- ✅ View all products
- ✅ Search products by SKU
- ✅ Filter products by:
    - Category
    - Name (partial match)
    - Exact Price
    - Price Range
- ✅ Sort inventory by:
    - Name
    - Price
    - Category
    - Quantity
- ✅ Save inventory to a file (`inventory.txt`)
- ✅ Load inventory from a file

---

## 📦 Product Structure

Each product contains:

- `SKU` (String)
- `Name` (String)
- `Quantity` (int)
- `Price` (double)
- `Category` (String)

---

## 🖥️ How to Run

1. **Compile the classes:**

   ```bash
   javac Product.java InventoryManager.java InventoryApp.java

2. Run the app:

    ```bash
   java InventoryApp
3. Follow the on-screen menu to interact with the inventory.

---
## 📂 File Persistence

- Products are saved in `inventory.txt` using CSV format.
- You can load saved inventory when restarting the application.

---

## 🧠 Concepts Used

- Object-Oriented Programming (OOP)
- Exception Handling
- File I/O (`BufferedReader`, `PrintWriter`)
- ArrayList
- Java Collections Sorting
- Basic Input Validation

---
## Author

Made by **Adrian Garcia**
DePaul University - Computer Science (Software Development)

---
## 📘 License

This project is open source and available under MIT License.
