package org.example.objects.items;

import org.example.objects.Item;

public class Potion extends Item {
    private String itemEffect;
    @Override
    public String getCSV(){
        return itemName + "," + itemType + "," + itemEffect;
    }
}
