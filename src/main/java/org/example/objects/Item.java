package org.example.objects;

public class Item {
    protected String itemName;
    protected String itemType;

    public Item(String itemName, String itemType){
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public String getCSV(){
        return itemName + "," + itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
