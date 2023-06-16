package com.example.db.entity;

import java.util.Objects;

public class Support extends Entity{
    private Integer support_id;
    private String fond_name;
    private String materials;

    public Support(Integer support_id, String fond_name, String materials) {
        this.support_id = support_id;
        this.fond_name = fond_name;
        this.materials = materials;
    }

    public Support() {

    }

    public Integer getSupport_id() {
        return support_id;
    }

    public void setSupport_id(Integer support_id) {
        this.support_id = support_id;
    }

    public String getFond_name() {
        return fond_name;
    }

    public void setFond_name(String fond_name) {
        this.fond_name = fond_name;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Support support = (Support) o;
        return Objects.equals(support_id, support.support_id) && Objects.equals(fond_name, support.fond_name) && Objects.equals(materials, support.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(support_id, fond_name, materials);
    }

    @Override
    public String toString() {
        return "Support{" +
                "support_id=" + support_id +
                ", fond_name='" + fond_name + '\'' +
                ", materials='" + materials + '\'' +
                '}';
    }

    @Override
    public String[] columns() {
        return new String[]{"support_id", "fond_name", "materials"};
    }
}
