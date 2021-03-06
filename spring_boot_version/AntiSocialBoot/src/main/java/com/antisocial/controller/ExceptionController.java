package com.antisocial.controller;


import org.hibernate.SessionException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.authentication.rememberme.CookieTheftException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    /**
     * Exception Controller.
     * Catches various exceptions thrown throughout the application runtime.
     *
     * @author Ant Kaynak - Github/Exercon
     */

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(Exception e , RedirectAttributes attr)   {
        e.printStackTrace();
            attr.addFlashAttribute("error","Requested page does not exist!");
            return "redirect:/oups";
    }

    @ExceptionHandler(IOException.class)
    public String handleIO(Exception e , RedirectAttributes attr)   {
        e.printStackTrace();
        attr.addFlashAttribute("error","An error occurred in IO streams! Please contact our support team.");
        return "redirect:/oups";
    }

    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public String databaseError(Exception e , RedirectAttributes attr) {
        e.printStackTrace();
        attr.addFlashAttribute("error","An error occurred with the database! Please contact our support team.");
        return "redirect:/oups";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(Exception e , RedirectAttributes attr)   {
        e.printStackTrace();
        attr.addFlashAttribute("error","Access Denied. Please log in!");
        return "redirect:/oups";
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(Exception e , RedirectAttributes attr)   {
        e.printStackTrace();
        attr.addFlashAttribute("error","Constraint violation occurred. Please try again.");
        return "redirect:/oups";
    }

    @ExceptionHandler(CookieTheftException.class)
    public String handleCookieTheft(Exception e , RedirectAttributes attr) {
        e.printStackTrace();
        attr.addFlashAttribute("error","Your remember me details are invalid. Please log in again.");
        return "redirect:/oups";
    }

    @ExceptionHandler({

            HttpSessionRequiredException.class,
            SessionException.class,
            SessionAuthenticationException.class,
            })
    public String handleSessionRequired(Exception e, RedirectAttributes attr){
        e.printStackTrace();
        attr.addFlashAttribute("error","Your session has been expired. Please log in again.");
        return "redirect:/oups";
    }

    @ExceptionHandler(MultipartException.class)
    public String handleMultipartError(Exception e, RedirectAttributes attr){
        e.printStackTrace();
        attr.addFlashAttribute("error","Could not upload any file. Please contact our support team.");
        return "redirect:/oups";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e , RedirectAttributes attr) {
        e.printStackTrace();
        attr.addFlashAttribute("error","A fatal error occurred! Please contact our support team.");
        return "redirect:/oups";
    }

}