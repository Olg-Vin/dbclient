package com.example.db.entity;

import java.util.Objects;

public class Connection {
    private Integer support_id;
    private Integer inventory_number;
    private Integer connected_id;

    public Connection(Integer support_id, Integer inventory_number, Integer connected_id) {
        this.support_id = support_id;
        inventory_number = inventory_number;
        this.connected_id = connected_id;
    }

    public Integer getSupport_id() {
        return support_id;
    }

    public void setSupport_id(Integer support_id) {
        this.support_id = support_id;
    }

    public Integer getInventory_number() {
        return inventory_number;
    }

    public void setInventory_number(Integer inventory_number) {
        inventory_number = inventory_number;
    }

    public Integer getConnected_id() {
        return connected_id;
    }

    public void setConnected_id(Integer connected_id) {
        this.connected_id = connected_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(support_id, that.support_id) && Objects.equals(inventory_number, that.inventory_number) && Objects.equals(connected_id, that.connected_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(support_id, inventory_number, connected_id);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "support_id=" + support_id +
                ", Inventory_number=" + inventory_number +
                ", connected_id=" + connected_id +
                '}';
    }
}
