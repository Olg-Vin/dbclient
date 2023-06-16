package com.example.db.hibernate;

import com.example.db.entity.Connection;

import jakarta.persistence.*;

@Entity
@Table(name = "connected")
public class ConnectedEntity
        implements Parser<Connection>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fk_support_id")
    public Integer support_id;
    @Column(name = "fk_inventory_number")
    public Integer inventory_number;
    @Column(name = "fk_connected_id")
    public Integer connected_id;

    @Override
    public Connection parseTo() {
        return new Connection(support_id, inventory_number, connected_id);
    }

    @ManyToOne
    @JoinColumn(name = "fk_inventory_number")
    public MuseumItemEntity museumItem;

    @ManyToOne
    @JoinColumn(name = "fk_support_id")
    public SupportEntity support;

    @Override
    public String toString() {
        return "ConnectedEntity{" +
                "support_id=" + support_id +
                ", inventory_number=" + inventory_number +
                ", connected_id=" + connected_id +
                ", museumItem=" + museumItem +
                ", museumItem=" + museumItem +
                '}';
    }
}
