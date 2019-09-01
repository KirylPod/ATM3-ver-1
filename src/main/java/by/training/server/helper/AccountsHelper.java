package by.training.server.helper;

import by.training.server.HibernateUtil;
import by.training.server.entity.Accounts;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AccountsHelper {

    private SessionFactory sessionFactory;

    public AccountsHelper() {sessionFactory = HibernateUtil.getSessionFactory();}

    public List<Accounts> getChartersList() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Accounts.class);

        Root<Accounts> root = cq.from(Accounts.class);
        cq.select(root);

        Query query = session.createQuery(cq);
        List<Accounts> chartersList = query.getResultList();
        session.close();
        return chartersList;

    }
}
