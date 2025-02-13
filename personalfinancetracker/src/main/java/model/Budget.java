package model;

import lombok.Data;

// This lombok consists of Getter, Setter, No arguments & All arguments Constructor, ToString Method 
@Data
public class Budget {
	private int id;
    private String category;
    private double amount;
    private int month;
    private int year;
    
    // Constructor
    public Budget(int id, String category, double amount, int month, int year) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.month = month;
        this.year = year;
    }

    public Budget(String category, double amount, int month, int year) {
        this(0, category, amount, month, year);
    }
}
