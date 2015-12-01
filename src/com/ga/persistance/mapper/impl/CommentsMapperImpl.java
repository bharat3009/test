package com.ga.persistance.mapper.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.persistance.entity.CommentHistory;
import com.ga.persistance.entity.UserDetail;
import com.ga.persistance.mapper.ICommentsMapper;

/**
 * The Class CommentsMapperImpl.
 *
 * @author Smit
 */
@Repository
public class CommentsMapperImpl implements ICommentsMapper {
    /** The session factory. */
    @Autowired
    SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.persistance.mapper.ICommentsMapper#uploadFile(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean uploadFile(String filePath, String comments, String userID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CommentHistory commentsHistory = new CommentHistory();
        commentsHistory.setCommentsDetail(comments);
        commentsHistory.setFilepath(filePath);
        commentsHistory.setUserId(new UserDetail(userID));
        commentsHistory.setCommentDate(new Date());

        session.save(commentsHistory);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.persistance.mapper.ICommentsMapper#getCommentsList(java.lang.String)
     */
    @Override
    public List<CommentHistory> getCommentsList(String userID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserDetail userDetail = new UserDetail(userID);
        System.out.println(userDetail);
        String hql = "FROM CommentHistory where userId ='" + userDetail.getUserId() + "'";
        Query query = session.createQuery(hql);
        List<CommentHistory> communityResuleList = query.list();
        return communityResuleList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ga.persistance.mapper.ICommentsMapper#getCommentByCommentID(int)
     */
    @Override
    public CommentHistory getCommentByCommentID(int commentID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        CommentHistory commentHistory = (CommentHistory) session.get(CommentHistory.class, commentID);
        return commentHistory;
    }

}
