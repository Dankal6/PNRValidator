/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Main class of the servlet that shows history of verifications
 *
 * @author Daniel Kaleta
 * @version 1.1.0
 */
@WebServlet(name = "History", urlPatterns = {"/History"})
public class HistoryServlet extends HttpServlet {

    /**
     * Field represents connection to DB
     */
    Connection con;

    @Override
    public void init() {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/danikaldb", "daniel", "daniel");
        } catch (SQLException ex) {
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Shows history of verification
     *
     * @return list of verifications
     * @throws java.sql.SQLException
     */
    public List<String> showHistoryOfVerification() throws SQLException {

        // make a connection to DB
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM VERIFICATION");

        List<String> history = new ArrayList<>();

        while (rs.next()) {
            String verification = rs.getString("typeofvalid") + " " + rs.getString("tovalid") + " ";
            String correct = rs.getString("correct");
            if (correct.equals("true")) {
                verification = verification + "is correct";
            } else {
                verification = verification + "is incorrect, because " + rs.getString("whyincorrect");
            }
            history.add(verification);
        }
        rs.close();

        return history;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);

        List<String> history = null;
        String aboutDatabase = null;

        try {
            history = showHistoryOfVerification();
        } catch (SQLException ex) {
            aboutDatabase = "Error while reading from DB, " + ex.getMessage();
        } catch (NullPointerException ex) {
            aboutDatabase = "Error while saving to DB, no connection";
        }

        session.setAttribute("history", history);
        session.setAttribute("aboutDatabase", aboutDatabase);
        response.sendRedirect("/PNRValidatorWeb/history.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
