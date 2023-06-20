package com.example.db.entity;

import java.util.Objects;

public class MuseumItem extends Entity{
    private Integer pk_inventory_number;
    private String author;
    private String date_of_creation;
    private String date_of_discovery;
    private String fk_fond_name;
    private String name_of_item;
    private String safely;
    private String story;

    public MuseumItem(Integer inventory_number, String author, String date_of_creation, String date_of_discovery,
                      String fond_name, String name_of_item, String safely, String story) {
        this.pk_inventory_number = inventory_number;
        this.author = author;
        this.date_of_creation = date_of_creation;
        this.date_of_discovery = date_of_discovery;
        this.fk_fond_name = fond_name;
        this.name_of_item = name_of_item;
        this.safely = safely;
        this.story = story;
    }

    public MuseumItem() {

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

    public Integer getPk_inventory_number() {
        return pk_inventory_number;
    }

    public void setPk_inventory_number(Integer pk_inventory_number) {
        this.pk_inventory_number = pk_inventory_number;
    }

    public String getFk_fond_name() {
        return fk_fond_name;
    }

    public void setFk_fond_name(String fk_fond_name) {
        this.fk_fond_name = fk_fond_name;
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
        return Objects.equals(pk_inventory_number, that.pk_inventory_number) && Objects.equals(author, that.author) && Objects.equals(date_of_creation, that.date_of_creation) && Objects.equals(date_of_discovery, that.date_of_discovery) && Objects.equals(fk_fond_name, that.fk_fond_name) && Objects.equals(name_of_item, that.name_of_item) && Objects.equals(safely, that.safely) && Objects.equals(story, that.story);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_inventory_number, author, date_of_creation, date_of_discovery, fk_fond_name, name_of_item, safely, story);
    }

    @Override
    public String toString() {
        return "MuseumItem{" +
                "inventory_number=" + pk_inventory_number +
                ", author='" + author + '\'' +
                ", date_of_creation='" + date_of_creation + '\'' +
                ", date_of_discovery='" + date_of_discovery + '\'' +
                ", fond_name='" + fk_fond_name + '\'' +
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
