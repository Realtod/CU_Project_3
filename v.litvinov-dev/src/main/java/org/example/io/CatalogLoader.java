package org.example.io;

import org.example.model.Item;
import org.example.model.storage.Catalog;

import java.nio.file.Path;
import java.io.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

// io/CatalogLoader — отвечает за загрузку каталога из csv-файла.
public class CatalogLoader {
    // источник вдохновения: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public static Catalog loadCatalog(Path path) throws IOException {
        File file = path.toFile();

        List<String> lines = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
        }
        String[] array = lines.toArray(new String[0]);
        Catalog catalog = new Catalog();
        for (String s : array) {
            String[] splittedLine = s.split(" ## ");
            Item item = new Item(splittedLine[0], splittedLine[1], splittedLine[2]);
            catalog.addItemToCatalog(item);
        }
        return catalog;
    }

}
