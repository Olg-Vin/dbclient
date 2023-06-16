package com.example.db;

import com.example.db.entity.Excursion;
import com.example.db.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.ResultListTransformer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Необходимо подключить в pom.xml
 * hibernate-core и jakarta.persistence
 * к проекту (и все еще нужен JDBC-драйвер)
 *
 * Для данной реализации необходим файл resources/hibernate.cfg.xml
 */
public class Hiber {
    public Hiber() {
    }

    public static List query(){
        Configuration cfg = new Configuration();
        cfg.configure();    // configure("hibernate.cfg.xml") as default
        cfg.addAnnotatedClass(ExcursionEntity.class); // Annotated entity here or in "hibernate.cfg.xml"


        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            // region 1) Список всех злодеев (поиск)
            System.out.println("Список: ");
            List<ExcursionEntity> rs = session.createNamedQuery("find_all", ExcursionEntity.class)
                    .getResultList();
//            session.createQuery("SELECT v FROM ExcursionEntity v", ExcursionEntity.class);
//            ArrayList<ExcursionEntity> excursionList = new ArrayList<>(session.createNamedQuery("find_all", ExcursionEntity.class)
//                    .getResultList());
//                    .forEach(System.out::println);
            System.out.println();
            return rs;
        }
    }
    public static void main(String[] args) {
        query().forEach(System.out::println);

        /*Configuration cfg = new Configuration();
        cfg.configure();    // configure("hibernate.cfg.xml") as default
        cfg.addAnnotatedClass(ExcursionEntity.class); // Annotated entity here or in "hibernate.cfg.xml"


        try (SessionFactory sessionFactory = cfg.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            // region 1) Список всех злодеев (поиск)
            System.out.println("Список: ");
//            session.createQuery("SELECT v FROM ExcursionEntity v", ExcursionEntity.class);
            session.createNamedQuery("find_all", ExcursionEntity.class)
                    .getResultList()
                    .forEach(System.out::println);
            System.out.println();*/
            // endregion

            // region 2) Описание конкретного злодея (поиск по параметру)
            /*System.out.println("Описание конкретного злодея: ");
            VillainEntity villain = session.get(VillainEntity.class, "Грю");
            System.out.println(villain);
            System.out.println();*/
            // endregion

            // region 3) Описание конкретного пособника (поиск по параметру)
            /*System.out.println("Описание конкретного пособника: ");
            MinionEntity minion = session.get(MinionEntity.class, "Доктор Нефарио");
            System.out.println(minion);

//            minion = session.get(MinionEntity.class, "Карл");
//            System.out.println(minion);
            System.out.println();*/
            // endregion

            // region 4) Заключение контракта (добавление)
            /*System.out.println("Заключение контракта:");
            if (villain != null && minion != null && !villain.minions.contains(minion)){
                if (villain.minions.add(minion)){
                    session.beginTransaction();
                    session.persist(villain);   // необходима открытая транзакция
                    session.getTransaction().commit();
                    System.out.println("Добавлен контракт");
                } // else - уже был контракт
            }
            System.out.println();*/
            // endregion

            // region 6) Разрыв контракта (удаление)
            /*System.out.println("Разрыв контракта:");
            if (villain != null && minion != null && villain.minions.contains(minion)){
                if (villain.minions.remove(minion)){
                    session.beginTransaction();
                    session.persist(villain);   // необходима открытая транзакция
//                    session.remove(villain);  // или удалить кортеж и все связанные с ним если прописан CASCADE.ALL
                    session.getTransaction().commit();
                    System.out.println("Контракт разорван");
                } // else - уже был контракт
            }
            System.out.println();*/
            // endregion

        } // session.close()
    }
