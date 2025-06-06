package org.example.model;

// model/Item — хранит поля, которые описывают товар.

import java.util.Objects;

public class Item {
    private final String name;
    private final String price;
    private final String category;

    public Item(String name, String price, String category) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        if (price == null || price.isEmpty()) {
            throw new IllegalArgumentException("Цена не может быть null или пустым");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Категория не может быть null или пустым");
        }

        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " : " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return name.equals(item.name) && price.equals(item.price) && category.equals(item.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category);
    }
}