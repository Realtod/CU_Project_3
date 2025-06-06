package org.example;

import org.example.model.storage.Catalog;
import org.example.model.storage.CatalogPage;

// Session — содержит информацию о текущем состоянии программы: загруженный каталог, активная страница каталога, отфильтрованный каталог, если выполнялся поиск.
public class Session {
    private static Catalog catalog;
    private static Catalog filteredCatalog;
    private static CatalogPage currentCatalogPage;

    public static Catalog getCatalog() {
        return catalog;
    }

    public static void setCatalog(Catalog catalog) {
        Session.catalog = catalog;
    }

    public static Catalog getFilteredCatalog() {
        return filteredCatalog;
    }

    public static void setFilteredCatalog(Catalog filteredCatalog) {
        Session.filteredCatalog = filteredCatalog;
    }

    public static CatalogPage getCurrentCatalogPage() {
        return currentCatalogPage;
    }

    public static void setCurrentCatalogPage(CatalogPage currentCatalogPage) {
        Session.currentCatalogPage = currentCatalogPage;
    }
}
