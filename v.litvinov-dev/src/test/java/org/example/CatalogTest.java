package org.example;

import org.example.model.Item;
import org.example.model.storage.Catalog;
import org.example.model.storage.CatalogPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Тест не только каталога, но и страниц
public class CatalogTest {
    private Catalog catalog;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void initTest() {
        catalog = new Catalog();
        item1 = new Item("ItemName", "1800.00", "Other");
        item2 = new Item("OtherName", "993.00", "Anime");
    }

    @Test
    public void testGetSize() {
        assertEquals(0, catalog.itemList.getSize());

        catalog.addItemToCatalog(item1);

        assertEquals(1, catalog.itemList.getSize());
    }

    @Test
    public void testAddItemToCatalog() {
        catalog.addItemToCatalog(item1);

        assertEquals(item1, catalog.itemList.getItem(0));

        catalog.addItemToCatalog(item2);

        assertEquals(item2, catalog.itemList.getItem(1));
    }

    @Test
    public void testFilter() {
        catalog.addItemToCatalog(item1);
        catalog.addItemToCatalog(item2);

        Catalog filtered = catalog.filter("Anime");

        assertEquals(item2, filtered.itemList.getItem(0));

        filtered = catalog.filter("");
        assertEquals(item1, filtered.itemList.getItem(0));
        assertEquals(item2, filtered.itemList.getItem(1));
    }

    @Test
    public void testGetCatalogPage() {
        CatalogPage catalogPage = new CatalogPage(catalog, 1, 1);

        CatalogPage result = catalog.getCatalogPage(1, 1);

        assertEquals(catalogPage.toString(), result.toString());

        catalogPage = new CatalogPage(catalog, 1, 2);

        result = catalog.getCatalogPage(1, 1);

        assertNotEquals(catalogPage, result);

        catalogPage = new CatalogPage(catalog, 2, 1);

        result = catalog.getCatalogPage(1, 1);

        assertNotEquals(catalogPage, result);
    }

    @Test
    public void testCatalogGetItems() {
        Item[] expected = new Item[]{item1};

        catalog.addItemToCatalog(item1);
        catalog.addItemToCatalog(item2);

        CatalogPage catalogPage = new CatalogPage(catalog, 1, 1);
        Item[] items = catalogPage.getItems();

        assertArrayEquals(expected, items);

        expected = new Item[]{item2};

        catalogPage = new CatalogPage(catalog, 2, 1);
        items = catalogPage.getItems();

        assertArrayEquals(expected, items);
    }

    @Test
    public void testHasNext() {
        catalog.addItemToCatalog(item1);
        catalog.addItemToCatalog(item2);

        CatalogPage catalogPage = catalog.getCatalogPage(1, 1);

        assertTrue(catalogPage.hasNext());

        catalogPage = catalog.getCatalogPage(2, 1);

        assertFalse(catalogPage.hasNext());
    }

    @Test
    public void testHasPrev() {
        catalog.addItemToCatalog(item1);
        catalog.addItemToCatalog(item2);

        CatalogPage catalogPage = catalog.getCatalogPage(1, 1);

        assertFalse(catalogPage.hasPrev());

        catalogPage = catalog.getCatalogPage(2, 1);

        assertTrue(catalogPage.hasPrev());
    }
}
