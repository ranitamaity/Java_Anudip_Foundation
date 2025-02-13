package model;

import lombok.Data;

@Data
public class Category {
	private int id; // Unique ID for the category
    private String name; // Name of the category (e.g., "Salary", "Groceries")
    private String type; // Type of category: "Income" or "Expense"
    
    // Constructor
    public Category(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // Constructor without ID (used for creating new categories)
    public Category(String name, String type) {
        this(0, name, type);
    }
}
