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
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author taran
 */
public class auth extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String ID = request.getParameter("ClientID");
        int IDNumber = Integer.parseInt(ID);
        String PW = request.getParameter("Password");
        
        //load account info
        //////////////////////////////////////////////////////////////////////////////
        String CorrectPW = "";
        //Querry Database for credentials
           try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
        }
        Connection connection;
        //"jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb"
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            //get client info
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Clients WHERE ID = ?");
            sql.setInt(1,IDNumber);
            ResultSet rs = sql.executeQuery();
            
            if(rs.next()){
                CorrectPW = rs.getString("PW");
            }
            else{
                request.getSession().setAttribute("message", "Client ID not registered");
                ServletContext sc = this.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
            
        } catch (SQLException e){}
        
        /////////////////////////////////////////////////////////////////////////////
        //account info found
        
        if( PW.equals(CorrectPW)){
            //authenticated case
            
            request.getSession().setAttribute("ClientID", IDNumber);
            request.getSession().setAttribute("message", "");
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/main.jsp");
            rd.forward(request, response);
        }
        else{
            request.getSession().setAttribute("message", "Password Incorrect");
            request.getSession().setAttribute("attemptedID", ID);
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }      
}
