package org.example.model.storage;

import org.example.collection.ItemList;
import org.example.model.Item;

import static java.util.Arrays.deepEquals;

// model/storage/Catalog — предоставляет API по поиску информации внутри каталога.
public class Catalog {
    public ItemList itemList;

    public Catalog() {
        this.itemList = new ItemList();
    }

    public CatalogPage getCatalogPage(int pageNumber, int pageSize) {
        return new CatalogPage(this, pageNumber, pageSize);
    }

    public void addItemToCatalog(Item item) {
        this.itemList.addItem(item);
    }

    public int getSize() {
        return itemList.getSize();
    }

    public Catalog filter(String search) {
        Catalog filteredCatalog = new Catalog();
        for (int i = 0; i < itemList.getSize(); i++) {
            if (itemList.getItem(i).getName().toLowerCase().contains(search.toLowerCase()) || itemList.getItem(i).getCategory().toLowerCase().contains(search.toLowerCase())) {
                filteredCatalog.addItemToCatalog(itemList.getItem(i));
            }
        }
        return filteredCatalog;
    }
}
