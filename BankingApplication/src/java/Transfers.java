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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author taran
 */
@WebServlet(urlPatterns = {"/Transfers"})
public class Transfers extends HttpServlet {


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
        String message = "";
        
        int ClientID = (int) request.getSession(false).getAttribute("ClientID");
        int fromAccount = Integer.parseInt(request.getParameter("from"));
        int toAccount = Integer.parseInt(request.getParameter("to"));
        float Amount = Float.parseFloat(request.getParameter("amount"));
        float fromBalance = 0;
        
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
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Accounts WHERE ClientID = ? AND AccountNumber = ?");
            sql.setInt(1,ClientID);
            sql.setInt(2,fromAccount);
            ResultSet accounts = sql.executeQuery();
            
            if(accounts.next()){
                fromBalance = accounts.getFloat("Balance");
                if( fromBalance < Amount){
                    message = "Insufficient Funds from Sending Account";
                }
                else{
                    float newBalance_from = fromBalance - Amount;
                    sql = connection.prepareStatement("UPDATE  Accounts SET Balance = ? WHERE AccountNumber = ?");
                    sql.setFloat(1,newBalance_from);
                    sql.setInt(2,fromAccount);
                    sql.executeUpdate();
                    
                    
                    sql = connection.prepareStatement("SELECT * FROM Accounts WHERE AccountNumber = ?");
                    sql.setInt(1,toAccount);
                    ResultSet to = sql.executeQuery();
                    
                    if(to.next()){
                        float toBalance = to.getFloat("Balance") + Amount;
                        sql = connection.prepareStatement("UPDATE  Accounts SET Balance = ? WHERE AccountNumber = ?");
                        sql.setFloat(1,toBalance);
                        sql.setInt(2,toAccount);
                        sql.executeUpdate();
                        message = "transfer complete";
                    }
                    else{
                        String req = "Client" + String.valueOf(ClientID) + "want to transfer $" +
                                String.valueOf(Amount) + " to non local account " + String.valueOf(toAccount);
                        sql = connection.prepareStatement("INSERT INTO ClientRequests VALUES (?,?)");
                        sql.setFloat(1, ClientID);
                        sql.setString(2,req);
                        sql.executeUpdate();
                        message = "Receiving Account is not with Natareel. Request sent to branch.";
                    }
                }
            }
            else{
                message = "Sending Account Number does not belong to client";
            }
        }catch(SQLException e){
        }
    
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
    }
}

