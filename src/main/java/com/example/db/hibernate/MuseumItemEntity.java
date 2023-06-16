package com.example.db.hibernate;


import com.example.db.entity.MuseumItem;
import jakarta.persistence.*;

@Entity
@Table(name = "museum_item")
public class MuseumItemEntity
        implements Parser<MuseumItem>{

    @Id
    @GeneratedValue
    @Column(name = "pk_inventory_number")
    public Integer inventory_number;
    @Column(name="autor")
    public String author;
    public String date_of_creation;
    public String date_of_discovery;
    @Column(name="fk_fond_name")
    public String fond_name;
    public String name_of_item;
    public String safely;
    public String story;

    @Override
    public MuseumItem parseTo() {
        return new MuseumItem(inventory_number, author,
                date_of_creation, date_of_discovery,
                fond_name, name_of_item, safely, story);
    }
    @ManyToOne
    @JoinTable(name = "connected",
    joinColumns = @JoinColumn(name="fk_inventory_number"),
    inverseJoinColumns = @JoinColumn(name = "fk_support_id"))
    public SupportEntity support;

    @Override
    public String toString() {
        return "MuseumItemEntity{" +
                "inventory_number=" + inventory_number +
                ", author='" + author + '\'' +
                ", date_of_creation='" + date_of_creation + '\'' +
                ", date_of_discovery='" + date_of_discovery + '\'' +
                ", fond_name='" + fond_name + '\'' +
                ", name_of_item='" + name_of_item + '\'' +
                ", safely='" + safely + '\'' +
                ", story='" + story + '\'' +
                ", support=" + support +
                '}';
    }
}
