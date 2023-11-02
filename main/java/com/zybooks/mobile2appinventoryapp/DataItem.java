package com.zybooks.mobile2appinventoryapp;

public class DataItem {
    private String name;
    private int Quantity;
    private boolean isSelected;  // To track if it's selected or not

    private int id;

    private String itemName;


    public DataItem(String name, int Quantity) {
        this.name = name;
        this.Quantity = Quantity;
        this.isSelected = false; // Initially set to false
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
