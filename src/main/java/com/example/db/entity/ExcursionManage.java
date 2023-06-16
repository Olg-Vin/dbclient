package com.example.db.entity;


public class ExcursionManage {
    private Integer pk_ex_manage_id;
    private String fk_excursion_id;
    private String fk_employee_passport;

    public ExcursionManage(Integer pk_ex_manage_id, String fk_excursion_id, String fk_employee_passport) {
        this.pk_ex_manage_id = pk_ex_manage_id;
        this.fk_excursion_id = fk_excursion_id;
        this.fk_employee_passport = fk_employee_passport;
    }

}
