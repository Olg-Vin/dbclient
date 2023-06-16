module com.example.db {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens com.example.db.entity to javafx.base;
    opens com.example.db to javafx.fxml;
    opens com.example.db.hibernate to org.hibernate.orm.core;
    exports com.example.db;
    exports com.example.db.hibernate;
}