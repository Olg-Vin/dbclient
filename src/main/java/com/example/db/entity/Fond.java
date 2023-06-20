package com.example.db.entity;

import java.util.Date;
import java.util.Objects;

public class Fond extends Entity{
    private String pk_fond_name;
    private String founding_date;

    public Fond(String fond_name, String founding_date) {
        this.pk_fond_name = fond_name;
        this.founding_date = founding_date;
    }

    public Fond() {

    }

    public String getPk_fond_name() {
        return pk_fond_name;
    }

    public void setPk_fond_name(String pk_fond_name) {
        this.pk_fond_name = pk_fond_name;
    }

    public String  getFounding_date() {
        return founding_date;
    }

    public void setFounding_date(String founding_date) {
        this.founding_date = founding_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fond fond = (Fond) o;
        return Objects.equals(pk_fond_name, fond.pk_fond_name) && Objects.equals(founding_date, fond.founding_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk_fond_name, founding_date);
    }

    @Override
    public String toString() {
        return "Fond{" +
                "fond_name='" + pk_fond_name + '\'' +
                ", founding_date=" + founding_date +
                '}';
    }

    @Override
    public String[] columns() {
        return new String[]{"fond_name", "founding_date"};
    }
}
