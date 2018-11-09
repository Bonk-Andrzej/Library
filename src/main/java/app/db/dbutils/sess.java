package app.db.dbutils;

import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class sess {
//    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .configure("hibernate.cfg.xml").build();
//
//            Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
//
//            return (SessionFactory) metadata.getSessionFactoryBuilder().build();
//
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//    }
//
//    public static SessionFactory getSESSION_FACTORY() {
//        return SESSION_FACTORY;
//    }
/*
1) tworze klasy z polami ktore maja byz zapisane  w bazie danych
2) tworze odpowiednik tych pol w klasaFx - przez co fx bedzie wiedzial jakiego typu sa to pola, tutaj tez implementuje objectproperty
    dla no categori w bookFx - no bo przy dodawaniu bede potrzebowal wybrac jakiej jest to categori ksiazka - wiec ta kalsa ma odwolania do innych klas tez
3) w klasModel tworze odwolania property - odwolania do pol w klasaFx ta klasa jest warstwa laczaca baze z klasami fx
    moge przez moedele odnosic sie do klasFx, implementowac metody ktore odnosza sie do DB, to taka wartwa posrednia miedzy baza a fx plus robi tez cala logike
    metoda cruda
4) w controller w metodzie initialize lacze np textField poprzez npBookModel(ktory ma w sobie odniesienie do BookFx)  z odpowiednim polem w  klasaFx
 */
}