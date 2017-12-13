package notebook;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class NotebookController {

    private SessionFactory sessionFactory;

    public NotebookController() {
        sessionFactory = HibernateUtil.getSessionAnnotationFactory();
    }

    public void addContact(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }

    public List<Contact> getContacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from Contact" ).list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void removeContact(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
        session.close();
    }

    public void updateContact(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        session.close();
    }

    public boolean isExists(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean isExists = session.contains(contact);
        session.getTransaction().commit();
        session.close();
        return isExists;
    }
}