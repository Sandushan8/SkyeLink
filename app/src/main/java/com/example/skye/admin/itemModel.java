package com.example.skye.admin;

public class itemModel {
    private  int itemCode;
    private  String itemName;
    private  String itemCategory;
    private  double itemSellPrice;
    private  String itemDescription;
    private byte[] image;

    public itemModel(int itemCode, String itemName, String itemCategory, Double itemSellPrice, String itemDescription, byte[] image) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemSellPrice = itemSellPrice;
        this.itemDescription = itemDescription;
        this.image = image;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public double getItemSellPrice() {
        return itemSellPrice;
    }

    public void setItemSellPrice(double itemSellPrice) {
        this.itemSellPrice = itemSellPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
