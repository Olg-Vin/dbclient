package com.example.db.entity;

import java.util.Objects;

public class MuseumItem extends Entity{
    private Integer inventory_number;
    private String author;
    private String date_of_creation;
    private String date_of_discovery;
    private String fond_name;
    private String name_of_item;
    private String safely;
    private String story;

    public MuseumItem(Integer inventory_number, String author, String date_of_creation, String date_of_discovery,
                      String fond_name, String name_of_item, String safely, String story) {
        this.inventory_number = inventory_number;
        this.author = author;
        this.date_of_creation = date_of_creation;
        this.date_of_discovery = date_of_discovery;
        this.fond_name = fond_name;
        this.name_of_item = name_of_item;
        this.safely = safely;
        this.story = story;
    }

    public MuseumItem() {

    }

    public Integer getInventory_number() {
        return inventory_number;
    }

    public void setInventory_number(Integer inventory_number) {
        this.inventory_number = inventory_number;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(String date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getDate_of_discovery() {
        return date_of_discovery;
    }

    public void setDate_of_discovery(String date_of_discovery) {
        this.date_of_discovery = date_of_discovery;
    }

    public String getFond_name() {
        return fond_name;
    }

    public void setFond_name(String fond_name) {
        this.fond_name = fond_name;
    }

    public String getName_of_item() {
        return name_of_item;
    }

    public void setName_of_item(String name_of_item) {
        this.name_of_item = name_of_item;
    }

    public String getSafely() {
        return safely;
    }

    public void setSafely(String safely) {
        this.safely = safely;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuseumItem that = (MuseumItem) o;
        return Objects.equals(inventory_number, that.inventory_number) && Objects.equals(author, that.author) && Objects.equals(date_of_creation, that.date_of_creation) && Objects.equals(date_of_discovery, that.date_of_discovery) && Objects.equals(fond_name, that.fond_name) && Objects.equals(name_of_item, that.name_of_item) && Objects.equals(safely, that.safely) && Objects.equals(story, that.story);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventory_number, author, date_of_creation, date_of_discovery, fond_name, name_of_item, safely, story);
    }

    @Override
    public String toString() {
        return "MuseumItem{" +
                "inventory_number=" + inventory_number +
                ", author='" + author + '\'' +
                ", date_of_creation='" + date_of_creation + '\'' +
                ", date_of_discovery='" + date_of_discovery + '\'' +
                ", fond_name='" + fond_name + '\'' +
                ", name_of_item='" + name_of_item + '\'' +
                ", safely='" + safely + '\'' +
                ", story='" + story + '\'' +
                '}';
    }

    @Override
    public String[] columns() {
        return new String[]{"inventory_number", "author", "date_of_creation", "date_of_discovery",
        "fond_name", "name_of_item", "safely", "story"};
    }
}
