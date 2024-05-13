package org.example.objects.items;

import org.example.objects.Item;

public class Equipment extends Item {
    private int attackModifier;

    @Override
    public String getCSV(){
        return itemName + "," + itemType + "," + attackModifier;
    }
}
