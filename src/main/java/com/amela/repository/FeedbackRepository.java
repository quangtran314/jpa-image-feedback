package com.amela.repository;

import com.amela.exception.BadWordException;
import com.amela.model.Feedback;
import com.amela.repository.IFeedbackRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.sql.Date;

@Transactional
public class FeedbackRepository implements IFeedbackRepository {

    private static List<String> badWords = List.of("shit", "bitch", "fuck");

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Feedback> findAll() {
        String queryStr = "SELECT f FROM Feedback AS f";
        TypedQuery<Feedback> query = entityManager.createQuery(queryStr, Feedback.class);
        return query.getResultList();
    }

    @Override
    public List<Feedback> findAllPerDay(Date date){
        String queryStr = "SELECT f FROM Feedback AS f WHERE f.date = :date";
        TypedQuery<Feedback> query = entityManager.createQuery(queryStr, Feedback.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public Feedback findOne(Long id) {
        String queryStr = "SELECT f FROM Feedback AS f WHERE f.id = :id";
        TypedQuery<Feedback> query = entityManager.createQuery(queryStr, Feedback.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Feedback feedback) throws BadWordException{
        for(String word : badWords){
            if(feedback.getComment().contains(word)){
                throw new BadWordException();
            }
        }
        if(feedback.getId() != null){
            entityManager.merge(feedback);
        } else {
            entityManager.persist(feedback);
        }
    }

    @Override
    public int likeUpdate(Long id) {
        Feedback feedback = findOne(id);
        if(feedback != null){
            feedback.setLike(feedback.getLike() + 1);
            entityManager.merge(feedback);
            return feedback.getLike();
        }
        return -1;
    }
}
