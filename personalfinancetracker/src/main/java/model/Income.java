package model;

import java.util.Date;

import lombok.Data;

@Data
public class Income {
	private int id;
    private double amount;
    private String category;
    private Date date;
    private String description;
    
    // Constructors, Getters, and Setters
    public Income(int id, double amount, String category, Date date, String description) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public Income(double amount, String category, Date date, String description) {
        this(0, amount, category, date, description);
    }
}
