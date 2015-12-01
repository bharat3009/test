package com.ga.persistance.mapper.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.persistance.entity.UserDetail;
import com.ga.persistance.mapper.IUserMapper;

/**
 * The Class UserMapperImpl.
 *
 * @author Smit
 */
@Repository
public class UserMapperImpl implements IUserMapper {

    /** The session factory. */
    @Autowired
    SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.persistance.mapper.IUserMapper#userLogin(java.lang.String, java.lang.String)
     */
    @Override
    public UserDetail userLogin(String userName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("username is " + userName);
        Criteria cr = session.createCriteria(UserDetail.class);
        cr.add(Restrictions.like("userName", userName));
        
        UserDetail userDetail = (UserDetail) cr.list().get(0);
        System.out.println(userDetail.getPassword() + userDetail.getUserName());
        return userDetail;
    }
}
