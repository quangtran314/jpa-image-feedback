package com.amela.controller;

import com.amela.exception.BadWordException;
import com.amela.model.Feedback;
import com.amela.repository.IFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @GetMapping("/")
    public ModelAndView showImage(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("feedback", new Feedback());
        modelAndView.addObject("feedbacks", feedbackRepository.findAllPerDay(getCurrentDate()));
        return modelAndView;
    }

    @PostMapping
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback) throws BadWordException {
        feedback.setDate(getCurrentDate());
        feedbackRepository.save(feedback);
        return "redirect:/";
    }

    @PostMapping("/like/{id}")
    public String updateLike(@PathVariable("id") Long id){
        feedbackRepository.likeUpdate(id);
        return "redirect:/";
    }

    private Date getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        try{
            return new Date(dateFormat.parse(dateFormat.format(date)).getTime());
        }catch(ParseException ex){
            throw new IllegalArgumentException(ex);
        }
    }

    @ExceptionHandler(BadWordException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("inputs-not-acceptable");
    }
}
