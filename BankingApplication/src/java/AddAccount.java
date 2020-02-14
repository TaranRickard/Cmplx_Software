/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author taran
 */
public class AddAccount extends HttpServlet {

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
        
        int type = Integer.parseInt(request.getParameter("request"));
        int ClientID = (int) request.getSession().getAttribute("ClientID");
        String message = "";
        
        switch(type)
        {
            case 1:
                message = "Client " + String.valueOf(type) + " would like to open another account.";
                break;
            case 2:
                message = "Client " + String.valueOf(type) + " is reporting their card lost or stolen.";
                break;
            case 3:
                message = "Client " + String.valueOf(type) + " wants to order checks.";
                break;
            case 4:
                message = "Client " + String.valueOf(type) + " is disputing a charge";
                break;
            case 5:
                message = "Client " + String.valueOf(type) + " wants to enroll in overdraft protection.";
                break;
            case 6: 
                message = "Client " + String.valueOf(type) + " is reporting fraud on their account.";
                break;
            case 7:
                message = this.card(request);
                break;
        }
        
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            PreparedStatement sql = connection.prepareStatement("INSERT INTO ClientRequests VALUES (?,?)");
            sql.setFloat(1, ClientID);
            sql.setString(2,message);
            sql.executeUpdate();
            
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Request Submitted:</h1>");
            out.println("<h3>" + message + "</h3>");
            out.println("</body>");
            out.println("</html>");
        }
            
        } catch (SQLException e) {}
        
        
      
    }
    private String card(HttpServletRequest request){
        String r = "";
        int type = 0;
        int AccountNumber = (int) request.getSession(false).getAttribute("AccountNumber");
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Accounts WHERE AccountNumber = ?");
            
            
            sql.setInt(1,AccountNumber);
            ResultSet rs = sql.executeQuery();
            
            if(rs.next()){
                type = rs.getInt("CardStatus");
            }
            else{
                return "no card status found";
            }
            
            if( type == 3){
                return "no card status found";
            }
            
            if( type == 1){
                type = 2;
                r = "card has been frozen";
            }
            else{
                type = 1;
                r = "card has been reactivated";
            }
            sql = connection.prepareStatement("UPDATE  Accounts SET CardStatus = ? WHERE AccountNumber = ?");
            sql.setInt(1, type);
            sql.setFloat(2, AccountNumber);
            sql.executeUpdate();
        } catch (SQLException e) {}

        r = r + " for account " + String.valueOf(request.getSession().getAttribute("AccountNumber"));
        
        return r;
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
