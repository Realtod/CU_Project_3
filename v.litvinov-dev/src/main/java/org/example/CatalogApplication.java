package org.example;

import java.io.IOException;
import java.util.Scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

import org.example.io.CatalogLoader;

import org.example.model.storage.Catalog;
import org.example.model.storage.CatalogPage;

// CatalogApplication — класс с main-методом, отвечает за обработку пользовательского ввода/вывода.
public class CatalogApplication {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        CatalogApplication app = new CatalogApplication();
        app.startApplication();
        app.processApplication();
    }

    public void startApplication() {
        Path filePath = this.collectFilePath(scanner);
        try {
            Session.setCatalog(CatalogLoader.loadCatalog(filePath));
            Catalog catalog = Session.getCatalog();
            Session.setCurrentCatalogPage(catalog.getCatalogPage(1, 4));
        } catch (IOException e) {
            System.err.println("Catalog loading error: " + e.getMessage());
            System.exit(1);
        }
    }
    public void processApplication() {
        while (true) {
            CatalogPage currentCatalogPage = Session.getCurrentCatalogPage();
            System.out.print(currentCatalogPage.toString());

            String userChoice = validateChoiceInput(scanner);
            switch (userChoice) {
                case "N":
                    try {
                        currentCatalogPage = currentCatalogPage.next();
                        Session.setCurrentCatalogPage(currentCatalogPage);
                    } catch (RuntimeException m) {
                        System.out.println(m.getMessage());
                    }
                    break;
                case "P":
                    try {
                        currentCatalogPage = currentCatalogPage.prev();
                        Session.setCurrentCatalogPage(currentCatalogPage);
                    } catch (RuntimeException m) {
                        System.out.println(m.getMessage());
                    }
                    break;
                case "F":
                    System.out.print("Enter filtration query: ");
                    String query = scanner.nextLine();
                    Session.setFilteredCatalog(Session.getCatalog().filter(query));
                    Catalog filteredCatalog = Session.getFilteredCatalog();
                    Session.setFilteredCatalog(filteredCatalog);
                    currentCatalogPage = filteredCatalog.getCatalogPage(1, 4);
                    Session.setCurrentCatalogPage(currentCatalogPage);
                    break;
                case "Q":
                    System.out.println("Goodbye!");
                    System.exit(0);
            }
        }
    }

    // источник вдохновения: https://www.index.dev/blog/check-if-path-is-valid-java
    public Path collectFilePath(Scanner scanner) {
        System.out.print("Enter path to bookmarks file: ");
        String filePath = scanner.nextLine();
        while (true) {
            try {
                Path path = Paths.get(filePath);
                // проверка на .csv и .txt, тк в ТЗ упоминаются оба варианта
                if (!Files.exists(path) || !(filePath.endsWith(".csv") || filePath.endsWith(".txt"))) {
                    System.out.print("Fookmarks file does not exist. Try again: ");
                    filePath = scanner.nextLine();
                    continue;
                }
                return path;
            } catch (InvalidPathException e) {
                System.out.print("Invalid path: " + e.getMessage() + ". Try again: ");
            }
        }
    }

    private String validateChoiceInput(Scanner scanner) {
        System.out.println("[N]ext, [P]rev, [F]ilter, [Q]uit");
        String command = scanner.nextLine().toUpperCase();
        while (!(command.equals("N") || command.equals("P") || command.equals("F") || command.equals("Q"))) {
            System.out.print("Incorrect command option. Try again: ");
            command = scanner.nextLine().toUpperCase();
        }
        return command;
    }
}
