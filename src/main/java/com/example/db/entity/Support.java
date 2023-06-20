package com.example.db.entity;

import java.util.Objects;

public class Support extends Entity{
    private Integer pk_support_id;
    private String fk_fond_name;
    private String materials;

    public Support(Integer support_id, String fond_name, String materials) {
        this.pk_support_id = support_id;
        this.fk_fond_name = fond_name;
        this.materials = materials;
    }

    public Support() {

    }

    public Integer getPk_support_id() {
        return pk_support_id;
    }

    public void setPk_support_id(Integer pk_support_id) {
        this.pk_support_id = pk_support_id;
    }

    public String getFk_fond_name() {
        return fk_fond_name;
    }

    public void setFk_fond_name(String fk_fond_name) {
        this.fk_fond_name = fk_fond_name;
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
        return Objects.equals(pk_support_id, support.pk_support_id) && Objects.equals(fk_fond_name, support.fk_fond_name) && Objects.equals(materials, support.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_support_id, fk_fond_name, materials);
    }

    @Override
    public String toString() {
        return "Support{" +
                "support_id=" + pk_support_id +
                ", fond_name='" + fk_fond_name + '\'' +
                ", materials='" + materials + '\'' +
                '}';
    }

    @Override
    public String[] columns() {
        return new String[]{"support_id", "fond_name", "materials"};
    }
}
