package com.example.db.entity;

import java.util.Objects;

public class Excursion extends Entity{
    private String excursion_name;
    private Integer number_of_seats;

    public Excursion(String excursion_name, Integer number_of_seats) {
        this.excursion_name = excursion_name;
        this.number_of_seats = number_of_seats;
    }

    public Excursion() {

    }

    public String getExcursion_name() {
        return excursion_name;
    }

    public void setExcursion_name(String excursion_name) {
        this.excursion_name = excursion_name;
    }

    public Integer getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(Integer number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursion excursion = (Excursion) o;
        return Objects.equals(excursion_name, excursion.excursion_name) && Objects.equals(number_of_seats, excursion.number_of_seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(excursion_name, number_of_seats);
    }

    @Override
    public String toString() {
        return "Excursion{" +
                "excursion_name='" + excursion_name + '\'' +
                ", number_of_seats=" + number_of_seats +
                '}';
    }

    @Override
    public String[] columns() {
        return new String[]{"excursion_name", "number_of_seats"};
    }
}
