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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author taran
 */
public class ListAllAccounts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        //load account info
        //////////////////////////////////////////////////////////////////////////////
        //Querry Database for Account
           try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
        }
        Connection connection;
        //"jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb"
        String nan = "";
        int IDNumber = (int) request.getSession(false).getAttribute("ClientID");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            //get client info
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Accounts WHERE ClientID = ?");
            sql.setInt(1,IDNumber);
            ResultSet accounts = sql.executeQuery();
            
            
            
            PreparedStatement cli = connection.prepareStatement("SELECT * FROM Clients WHERE ID = ?");
            cli.setInt(1,IDNumber);
            ResultSet client = cli.executeQuery();
            
            if(!client.next()){
                //no client
            }
           
            //accounts = All Accounts
            //client = client info
            /////////////////////////////////////////////////////////////////////////////
            //generate HTML   
            
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet generateStatement</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<h1>" + client.getString("First") + " " +  client.getString("Last") +"   -   "+ Integer.toString(IDNumber) +"</h1>");
            out.println("<h2>All Accounts:</h2>");
            out.println("<hr>");
            
            while(accounts.next()){
                String AccountNumber = String.valueOf(accounts.getInt("AccountNumber"));
                String AccountType = accounts.getString("Type") + " account";
                String Balance = String.valueOf(accounts.getFloat("Balance"));
                String APR = String.valueOf(accounts.getFloat("APR"));
                
                out.println("<h3><strong>"+ AccountNumber + ":</strong> " + AccountType + "</h3>");
                out.println("<h4>Balance: $" + Balance + "  -    APR: " + APR + "%</h4>");
                out.println("<br/>");
            }      
            
            out.println("<hr>");
            out.println("<h3> Client Phone Number: " + client.getString("Phone") + "</h3>");       
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
                    
        } catch (SQLException e){
        }
       
        
    }      
}


