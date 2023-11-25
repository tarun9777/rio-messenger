package com.rio.messenger.dao;

import com.rio.messenger.entity.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao{

    HibernateTransactionManager transactionManager;

    @Autowired
    public MessageDaoImpl(HibernateTransactionManager tm){
        this.transactionManager = tm;
    }
    @Override
    public void save(Message message) {
        Session session = transactionManager.getSessionFactory().getCurrentSession();
        session.save(message);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> findByUsers(String user1, String user2) {
        Session session = transactionManager.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Message.class);
        Criterion c1 = Restrictions.and(Restrictions.eq("from",user1), Restrictions.eq("to", user2));
        Criterion c2 = Restrictions.and(Restrictions.eq("from",user2), Restrictions.eq("to", user1));
        criteria.add(Restrictions.or(c1,c2));
        criteria.addOrder(Order.desc("time"));
        List<Message> messages = (List<Message>) criteria.list();
        return messages;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getUnread(String user) {
        Session session = transactionManager.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Message.class);
        criteria.add(Restrictions.eq("to",user));
        criteria.add(Restrictions.eq("read","N"));
        criteria.addOrder(Order.desc("time"));
        List<Message> messages = (List<Message>) criteria.list();
        for (Message m : messages){
            m.setRead('Y');
            session.update(m);
        }
        return messages;
    }
}
