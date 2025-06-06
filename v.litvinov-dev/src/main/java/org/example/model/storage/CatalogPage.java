package org.example.model.storage;

import org.example.model.Item;

import static java.lang.Math.min;

// model/storage/CatalogPage — предоставляет API по постраничной навигации внутри каталога.
public class CatalogPage {
    // Нумерация с 1.
    public final int pageNumber;
    // Я использую это поле как число элементов на странице
    public final int pageSize;
    // Атрибут нужен для работы методов.
    public final Catalog catalog;
    public final int offset;

    public CatalogPage(Catalog catalog, int pageNumber, int pageSize) {
        this.catalog = catalog;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.offset = (pageNumber - 1) * pageSize;
    }

    // Получает элементы этой страницы каталога как список
    public Item[] getItems() {
        int length = min(pageSize, catalog.getSize() - offset);

        Item[] result = new Item[length];
        int start = offset;
        for (int i = start; i < start + length; i++) {
            result[i - start] = catalog.itemList.getItem(i);
        }
        return result;
    }

    public boolean hasNext() {
        return ((offset + 1) + pageSize) <= catalog.itemList.getSize();
    }

    public CatalogPage next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("Следующей страницы не существует");
        }
        return new CatalogPage(catalog, pageNumber + 1, pageSize);
    }

    public boolean hasPrev() {
        return (offset - pageSize) >= 0;
    }

    public CatalogPage prev() {
        if (!hasPrev()) {
            throw new IndexOutOfBoundsException("Предыдущей страницы не существует");
        }
        return new CatalogPage(catalog, pageNumber - 1, pageSize);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Page (%d/%d)\n", pageNumber, catalog.itemList.getSize() / pageSize + 1));

        for (Item item: getItems()) {
            stringBuilder.append(String.format("%s\n", item.toString()));
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
