package com.example.db.hibernate;

import com.example.db.entity.Excursion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQuery(name = "find_all", query = "from ExcursionEntity e")
@Table(name = "excursion")
public class ExcursionEntity implements Parser<Excursion>{
    @Id
    @Column(name = "pk_excursion_name")
    public String excursion_name;
    @Column(name = "number_of_seats")
    public Integer number_of_seats;

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
    public Excursion parseTo() {
        return new Excursion(excursion_name, number_of_seats);
    }

    @Override
    public String toString() {
        return "ExcursionEntity{" +
                "excursion_name='" + excursion_name + '\'' +
                ", number_of_seats=" + number_of_seats +
                '}';
    }
}
