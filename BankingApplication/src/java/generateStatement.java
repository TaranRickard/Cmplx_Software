/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.javabeans.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author taran
 */
public class generateStatement extends HttpServlet {

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
        int IDNumber = (int) request.getSession(false).getAttribute("ClientID");
        int AccountNumber = (int) request.getSession(false).getAttribute("AccountNumber");
        
        
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        String AccountType = "";
        String Balance = "";
        String APR = "";
        String ClientName = "";
        String ClientPhone = "";
        
        String nan = "";
        
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            //get client info
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Accounts WHERE AccountNumber = ?");
            sql.setInt(1,AccountNumber);
            ResultSet acc = sql.executeQuery();
            
            
            if(acc.next()){
                nan = nan + "account found";
                AccountType = acc.getString("Type");
                APR = String.valueOf(acc.getFloat("APR")) + "%";          
                Balance = "$" + String.valueOf(acc.getFloat("Balance"));
            }
            
            sql = connection.prepareStatement("SELECT * FROM Clients WHERE ID = ?");
            sql.setInt(1,IDNumber);
            ResultSet client = sql.executeQuery();
            
            if(client.next()){
                nan = nan+ "client found";
                ClientName = client.getString("First") + " " + client.getString("Last");
                ClientPhone = client.getString("Phone");
            }
            
            sql = connection.prepareStatement("SELECT TOP ? * FROM Transactions WHERE Sending = ? OR Receiving = ?");
            sql.setInt(1,15);
            sql.setInt(2, AccountNumber);
            sql.setInt(3, AccountNumber);
            nan = nan + "statement made";
            ResultSet trans = sql.executeQuery();
            nan = nan + "transactions querried";
            while(trans.next()){
                
                Transaction t = new Transaction();
                t.set_ID(trans.getFloat("ID"));
                t.set_from(trans.getFloat("Sending"));
                t.set_to(trans.getFloat("Receiving"));
                t.set_amount(trans.getFloat("Amount"));
                t.set_time(trans.getTimestamp("Timestamp"));
                
                transactions.add(t);
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
            out.println("<h1>Statement for Account: " + String.valueOf(AccountNumber) +"</h1>");
            out.println("<h3>Account Type: "+ AccountType + " - APR: " + APR +"</h3>");
            out.println("<h3>Client Name: " + ClientName + " - Phone: " + ClientPhone + "</h3>");
            out.println("<hr>");
            out.println("<h3>Account Balance: " + Balance);
            out.println("<h3>Transactions:</h3>");
            out.println("<br/>");
            
            for(Transaction tran : transactions){
                
                out.println("<h3><strong>"+ tran.to_String(AccountNumber) + "</h3>");
                
            }           
            out.println("<hr>");
            out.println("<h3> Client Name:" + ClientName + " - Phone: " + ClientPhone + "</h3>");        
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
                    
        } catch (SQLException e){}
    }      
}
