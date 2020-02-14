/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javabeans;

/**
 *
 * @author taran
 */

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class Client {
    private float ID;
    private String SSN;
    public Account savings;
    public Account checkings;
    private boolean checking_exists;
    private boolean savings_exists;
    private String first;
    private String last;
    private String phone;
    public ArrayList<Account> otherAccounts;
    
    public void set_ID(float n){
        this.ID = n;
    }
    public void set_SSN(String n){
        this.SSN = n;
    }
    public void set_First(String n){
        this.first = n;
    }
    public void set_Last(String n){
        this.last = n;
    }
    public void set_Phone(String n){
        this.phone = n;
    }
    public float get_ID(){
        return this.ID;
    }
    public String get_SSN(){
        return this.SSN;
    }
    public String get_First(){
        return this.first;
    }
    public String get_Last(){
        return this.last;
    }
    public String get_Phone(){
        return this.phone;
    }
    public boolean checking_Exists(){
        return checking_exists;
    }
    public boolean savings_Exists(){
        return savings_exists;
    }
    public ArrayList<String> getTransactions(int quant){
        ArrayList<String> r = new ArrayList<String>();
        int c = 0;
        if(savings_exists){
            for (Transaction t: savings.transactions){
                r.add(t.to_String(savings.get_account_number()));
                c++;
                if( c == quant) return r;
            }
        }
        if(checking_exists){
            for (Transaction t: checkings.transactions){
                r.add(t.to_String(checkings.get_account_number()));
                c++;
                if( c == quant) return r;
            }
        }
        while(c < quant){
            r.add("none found");
            c++;
        }
        return r;
    }
    
        public ArrayList<String> getTransactions_Checking(int quant){
        ArrayList<String> r = new ArrayList<String>();
        int c = 0;

        if(checking_exists){
            for (Transaction t: checkings.transactions){
                r.add(t.to_String(checkings.get_account_number()));
                c++;
                if( c == quant) return r;
            }
        }
        while(c < quant){
            r.add("none found");
            c++;
        }
        return r;
    }
    
        public ArrayList<String> getTransactions_Savings(int quant){
        ArrayList<String> r = new ArrayList<String>();
        int c = 0;
        if(savings_exists){
            for (Transaction t: savings.transactions){
                r.add(t.to_String(savings.get_account_number()));
                c++;
                if( c == quant) return r;
            }
        }
        while(c < quant){
            r.add("none found");
            c++;
        }
        return r;
    }    
    
    public void retrieve(int ClientID){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            //get client info
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM Clients WHERE ID = ?");
            sql.setInt(1,ClientID);
            
            ResultSet rs = sql.executeQuery();
            
            if(rs.next()){
                this.set_SSN(rs.getString("SSN"));
                this.set_First(rs.getString("First"));
                this.set_Last(rs.getString("Last"));
                this.set_Phone(rs.getString("Phone"));
                this.set_ID(ClientID);
                
                //get Savings Account
                PreparedStatement acctQuery = connection.prepareStatement("SELECT * FROM Accounts WHERE ClientID = ?");
                acctQuery.setInt(1,ClientID);
                ResultSet accounts = acctQuery.executeQuery();

                boolean save = false;
                boolean check = false;
                String type = "";
                otherAccounts = new ArrayList<Account>();
                
                
                while(accounts.next()){
                    type = accounts.getString("Type");
                    if(type.equals("Savings") && !save){
                        //accounts = primary savings
                        savings = new Account();
                        savings.set_balance(accounts.getFloat("Balance"));
                        savings.set_account_number(accounts.getInt("AccountNumber"));
                        savings.set_routing(accounts.getInt("RoutingNumber"));
                        savings.set_clientID(accounts.getFloat("ClientID"));
                        savings.set_APR(accounts.getFloat("APR"));
                        savings.set_type("Savings");
                        savings.set_CardStatus(accounts.getInt("CardStatus"));
                        savings.load_transactions(15);
                        save = true;
                    }
                    else if(type.equals("Checking") && !check){
                        //accounts = primary checking
                        checkings = new Account();
                        checkings.set_balance(accounts.getFloat("Balance"));
                        checkings.set_account_number(accounts.getInt("AccountNumber"));
                        checkings.set_routing(accounts.getInt("RoutingNumber"));
                        checkings.set_clientID(accounts.getFloat("ClientID"));
                        checkings.set_APR(accounts.getFloat("APR"));
                        checkings.set_type("Checking");
                        checkings.set_CardStatus(accounts.getInt("CardStatus"));
                        checkings.load_transactions(15);
                        check = true;
                    }
                    else{
                        //accounts = another account
                        Account other = new Account();
                        other.set_balance(accounts.getFloat("Balance"));
                        other.set_account_number(accounts.getInt("AccountNumber"));
                        other.set_routing(accounts.getInt("RoutingNumber"));
                        other.set_clientID(accounts.getFloat("ClientID"));
                        other.set_APR(accounts.getFloat("APR"));
                        other.set_type(accounts.getString("Type"));
                        other.set_CardStatus(accounts.getInt("CardStatus"));
                        other.load_transactions(15);
                        otherAccounts.add(other);
                    }
                }
                checking_exists = check;
                savings_exists = save;
            }
            else{
                //no info for that client ID
            }
        } catch (SQLException e) {}{
        }
    }
}
