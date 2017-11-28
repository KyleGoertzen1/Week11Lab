/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.AccountService;
import businesslogic.UserService;
import businesslogic.WebMailService;
import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 729347
 */
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        String action = request.getParameter("action");
        UserService us = new UserService();
        UserDB userDB = new UserDB();
        String emailForUser = request.getParameter("email");
        User user = userDB.getUserByEmail(emailForUser);
        if (action.equals("emailed")) {
            HashMap<String, String> contents = new HashMap<>();
            contents.put("username", user.getUsername());
            contents.put("password", user.getPassword());
            String path = getServletContext().getRealPath("/WEB-INF");

            AccountService as = new AccountService();
            as.createEmail(emailForUser, "Notes Keepr", path, contents);
        }

        if (action.equals("ForgotPass")) {

            AccountService as = new AccountService();
            String path = getServletContext().getRealPath("/WEB-INF");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            try {
                as.forgotPassword(email, path);
            } catch (NotesDBException ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, "Invalid access for username: {0}", username);
            }
        }
    }
}
