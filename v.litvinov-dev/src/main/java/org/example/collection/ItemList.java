package org.example.collection;

import org.example.model.Item;

public class ItemList {
    private Item[] items;
    public int size;

    public ItemList() {
        items = new Item[2];
        size = 0;
    }

    public void addItem(Item item) {
        if (size == items.length) {
            Item[] newItems = new Item[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
        items[size] = item;
        size++;
    }
    
    public Item getItem(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Некорректный индекс");
        }
        return items[index];
    }

    public int getSize() {
        return size;
    }
}

