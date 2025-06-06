package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.collection.ItemList;
import org.example.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ApplicationTest {
    private ItemList itemList;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList();
    }

    @Test
    public void testGetItem() {
        itemList.addItem(new Item("Apple", "1.00", "Fruit"));
        itemList.addItem(new Item("Banana", "0.50", "Fruit"));
        itemList.addItem(new Item("Carrot", "0.30", "Vegetable"));

        Item expectedItem = new Item("Banana", "0.50", "Fruit");
        Item actualItem = itemList.getItem(1);

        assertEquals(expectedItem, actualItem);
    }

    @Test
    public void testGetItem_error() {
        itemList.addItem(new Item("Apple", "1.00", "Fruit"));
        itemList.addItem(new Item("Banana", "0.50", "Fruit"));
        itemList.addItem(new Item("Carrot", "0.30", "Vegetable"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            itemList.getItem(3);
        });

        assertEquals("Некорректный индекс", exception.getMessage());
    }

    @Test
    public void testAddItem() {
        for (int i = 0; i < 15; i++) {
            itemList.addItem(new Item("Apple", "1.00", "Fruit"));
        }
        Item expectedItem = new Item("Apple", "1.00", "Fruit");

        assertEquals(15, itemList.getSize());
    }
}
