package com.example.db.hibernate;

import com.example.db.entity.Support;
import jakarta.persistence.*;

@Entity
@Table(name = "support")
public class SupportEntity
        implements Parser<Support>{
    @Id
    @Column(name = "pk_support_id")
    public Integer support_id;
    @Column(name = "fk_fond_name")
    public String fond_name;
    public String materials;

    @Override
    public Support parseTo() {
        return new Support(support_id, fond_name, materials);
    }

    @OneToOne
    @JoinTable(name = "connected",
    joinColumns = @JoinColumn(name = "fk_support_id"),
    inverseJoinColumns = @JoinColumn(name = "fk_inventory_number"))
    public MuseumItemEntity museumItem;

    @Override
    public String toString() {
        return "SupportEntity{" +
                "support_id=" + support_id +
                ", fond_name='" + fond_name + '\'' +
                ", materials='" + materials + '\'' +
                ", museumItem=" + museumItem +
                '}';
    }
}
