package com.rio.messenger.dao;

import com.rio.messenger.entity.User;
import com.rio.messenger.exception.MessengerException;
import com.rio.messenger.exception.UserException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

     HibernateTransactionManager transactionManager;

    @Autowired
    public UserDaoImpl(HibernateTransactionManager tm){
        this.transactionManager = tm;
    }

    @Override
    public void save(User user) {
        try{
            Session session = transactionManager.getSessionFactory().getCurrentSession();
            session.save(user);
            session.flush();
        } catch (ConstraintViolationException e){
            throw new UserException("user already exists");
        }
    }

    @Override
    public Optional<User> findById(String username){
        Session session = transactionManager.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username",username));
        User user = (User) criteria.uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        Session session = transactionManager.getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = (List<User>)criteria.list();
        return users;
    }


}
