package com.amela.repository;

import com.amela.exception.BadWordException;
import com.amela.model.Feedback;

import java.sql.Date;
import java.util.List;

public interface IFeedbackRepository {
    List<Feedback> findAll();

    List<Feedback> findAllPerDay(Date date);

    Feedback findOne(Long id);

    void save(Feedback feedback) throws BadWordException;

    int likeUpdate(Long id);
}
