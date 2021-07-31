package com.amela.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rate;
    private String author;
    private String comment;
    private int like_cmt = 0;
    private Date date;

    public Feedback() {
    }

    public Feedback(int rate, String author, String comment) {
        this.rate = rate;
        this.author = author;
        this.comment = comment;
    }

    public Feedback(Long id, int rate, String author, String comment, int like, Date date) {
        this.id = id;
        this.rate = rate;
        this.author = author;
        this.comment = comment;
        this.like_cmt = like;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like_cmt;
    }

    public void setLike(int like) {
        this.like_cmt = like;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
