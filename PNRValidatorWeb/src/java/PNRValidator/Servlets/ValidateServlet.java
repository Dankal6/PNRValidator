/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Servlets;

import PNRValidator.Model.Checker;
import PNRValidator.Model.NipChecker;
import PNRValidator.Model.PeselChecker;
import PNRValidator.Model.RegonChecker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Main class of the servlet that validate entered number
 *
 * @author Daniel Kaleta
 * @version 1.1.0
 */
@WebServlet(name = "Validate", urlPatterns = {"/Validate"})
public class ValidateServlet extends HttpServlet {

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

        String aboutVarification;
        String aboutDatabase = null;
        int counter;
        try {
            counter = (Integer) session.getAttribute("counter");
        } catch (NullPointerException ex) {
            counter = 0;
        }

        counter++;

        String toValid = request.getParameter("number");

        if (toValid.equals("")) {
            aboutVarification = "There is no number to validate!";
        } else {

            String typeOfValid = request.getParameter("typeOfValid");
            JSONObject jVerification = new JSONObject();
            jVerification.put("type", typeOfValid);
            jVerification.put("number", toValid);
            JSONObject finalVerification = verify(jVerification.toString());

            try {
                addToHistory(finalVerification);
            } catch (SQLException ex) {
                aboutDatabase = "Error while saving to DB," + ex.getMessage();
            } catch (NullPointerException ex) {
                aboutDatabase = "Error while saving to DB, no connection";
            }

            if (finalVerification.getBoolean("correct") == false) {
                aboutVarification = typeOfValid + " is incorrect, because " + finalVerification.getString("whyIncorrect");
            } else {
                aboutVarification = typeOfValid + " is correct!";
            }
        }
        session.setAttribute("counter", counter);
        session.setAttribute("aboutVarification", aboutVarification);
        session.setAttribute("aboutDatabase", aboutDatabase);

        response.sendRedirect("/PNRValidatorWeb/aboutVerification.jsp");
    }

    /**
     * Method to verify
     *
     * @param toVertify type and number to validate
     * @return JSONObject with information about the correctness
     * @throws JSONException when JSON Object can not be created
     */
    private JSONObject verify(String toVertify) throws JSONException {
        JSONObject jVerification;

        jVerification = new JSONObject(toVertify);

        boolean correct = false;
        String whyIncorrect = "";

        try {
            switch (jVerification.getString("type")) {
                case "PESEL":
                    Checker pesel = new PeselChecker();
                    correct = pesel.check(jVerification.getString("number"));
                    break;
                case "NIP":
                    Checker nip = new NipChecker();
                    correct = nip.check(jVerification.getString("number"));
                    break;
                case "REGON":
                    Checker regon = new RegonChecker();
                    correct = regon.check(jVerification.getString("number"));
                    break;
            }
        } catch (Exception e) {
            whyIncorrect = e.getMessage();
        }

        jVerification.put("correct", correct);
        jVerification.put("whyIncorrect", whyIncorrect);
        return jVerification;
    }

    /**
     * Adds verification to list of verifications - history
     *
     * @param jVerification verification to add as JSONObject
     */
    public void addToHistory(JSONObject jVerification) throws SQLException {

        String toValid = jVerification.getString("number");
        String typeOfValid = jVerification.getString("type");
        boolean correct = jVerification.getBoolean("correct");
        String whyIncorrect = jVerification.getString("whyIncorrect");

        // make a connection to DB
        Statement statement = con.createStatement();
        String sql = "INSERT INTO VERIFICATION(TOVALID,TYPEOFVALID,CORRECT,WHYINCORRECT) VALUES ('" + toValid + "', '" + typeOfValid + "', '" + Boolean.toString(correct) + "', '" + whyIncorrect + "')";
        statement.executeUpdate(sql);
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
