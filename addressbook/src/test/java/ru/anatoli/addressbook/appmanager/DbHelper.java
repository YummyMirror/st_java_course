package ru.anatoli.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;
import java.util.List;

/**
 * Created by anatoli.anukevich on 5/21/2017.
 */
public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups getGroups() {
        //ALL from http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("FROM GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts getContacts() {
        //ALL from http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorial-basic-test
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("FROM ContactData WHERE deprecated = '0000-00-00 00:00:00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public void refresh(Object o){
        Session session = sessionFactory.openSession();
        session.refresh(o);
        session.close();
    }
}
