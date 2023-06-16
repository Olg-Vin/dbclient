package com.example.db.entity;


import java.util.Objects;

public class Hall extends Entity{
    private String hall_name;
    private Integer count_of_items;
    private String theme;

    public Hall(String hall_name, Integer count_of_items, String theme) {
        this.hall_name = hall_name;
        this.count_of_items = count_of_items;
        this.theme = theme;
    }

    public Hall() {

    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public Integer getCount_of_items() {
        return count_of_items;
    }

    public void setCount_of_items(Integer count_of_items) {
        this.count_of_items = count_of_items;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return Objects.equals(hall_name, hall.hall_name) && Objects.equals(count_of_items, hall.count_of_items) && Objects.equals(theme, hall.theme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hall_name, count_of_items, theme);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hall_name='" + hall_name + '\'' +
                ", count_of_items=" + count_of_items +
                ", theme='" + theme + '\'' +
                '}';
    }
    @Override
    public String[] columns(){
        return new String[]{"hall_name", "count_of_items", "theme"};
    }
}
