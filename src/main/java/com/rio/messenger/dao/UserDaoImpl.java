package com.rio.messenger.dao;

import com.rio.messenger.entity.User;
import com.rio.messenger.exception.MessengerException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

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
            throw new MessengerException(null,"user already exists");
        }

    }
}
