package org.example.objects;

public class Item {
    protected String itemName;
    protected String itemType;

    public String getCSV(){
        return itemName + "," + itemType;
    }
}
