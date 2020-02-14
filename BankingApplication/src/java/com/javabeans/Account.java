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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Account {
    private int account_number;
    private int routing;
    private float clientID;
    private float APR;
    private String type;
    private float balance;
    public ArrayList<Transaction> transactions;
    private int cardStatus;
    //1 = active card
    //2 = frozen card
    //3 = no card
    
    public void set_balance(float n){
        this.balance = n;
    }
    public void set_account_number(int n){
        this.account_number = n;
    }
    public void set_routing(int n){
        this.routing = n;
    }
    public void set_clientID(float n){
        this.clientID = n;
    }
    public void set_APR(float n){
        this.APR = n;
    }
    public void set_type(String n){
        this.type = n;
    }
    public void set_CardStatus(int n){
        this.cardStatus = n;
    }
    public int get_CardStatus(){
        return this.cardStatus;
    }
    public String get_type(){
        return this.type;
    }
    public float get_balance(){
        return this.balance;
    }
    public int get_account_number(){
        return this.account_number;
    }
    public int get_routing(){
        return this.routing;
    }
    public float get_clientID(){
        return this.clientID;
    }
    public float get_APR(){
        return this.APR;
    }

    /* Transfer from this account to another account*/
    public void transfer_from( float amount, float to, float routing){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            PreparedStatement sql = connection.prepareStatement(
                    "UPDATE Accounts SET Balance = ? WHERE AccountNumber = ?");
            sql.setFloat(1, this.balance - amount);
            sql.setFloat(2,this.account_number);
            sql.executeUpdate();
            
            //if account going to is with our bank
            PreparedStatement check = connection.prepareStatement(
                    "SELECT * FROM Accounts WHERE AccountNumber = ?");
            check.setFloat(1,to);
            ResultSet rs = check.executeQuery();
            if(rs.next()){
                //transfer money to this account
                float otherBalance = Float.parseFloat(rs.getString("Balance"));
                
                sql.setFloat(1, otherBalance + amount);
                sql.setFloat(2,to);
                sql.executeUpdate();
            }
            else{
                //account belongs to another bank
                //leave request
                String request = "This account transfered $" + String.valueOf(amount) +
                        "to " + String.valueOf(to) + "at routing #: " + String.valueOf(routing);
                this.place_request(request);
            }
        } catch (SQLException e) {}{
        }
    }    
    public void place_request(String message){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            PreparedStatement sql = connection.prepareStatement("INSERT INTO ClientRequests VALUES (?,?)");
            sql.setFloat(1, this.account_number);
            sql.setString(2,message);
            sql.executeUpdate();
        } catch (SQLException e) {}{
        }
    }
    
    public void load_transactions(int number){
        transactions = new ArrayList<Transaction>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:ucanaccess://C:/Users/taran/OneDrive - The Pennsylvania State University/IST 412/Semester Project/BankingDB.accdb");
            PreparedStatement sql = connection.prepareStatement("SELECT TOP ? * FROM Transactions WHERE Sending = ? OR Receiving = ?");
            sql.setInt(1, number);
            sql.setFloat(2,this.account_number);
            sql.setFloat(3,this.account_number);
            ResultSet rs = sql.executeQuery();
            
            
            
            while(rs.next()){
                Transaction trans = new Transaction();
                trans.set_ID(rs.getFloat("ID"));
                trans.set_from(rs.getFloat("Sending"));
                trans.set_to(rs.getFloat("Receiving"));
                trans.set_amount(rs.getFloat("Amount"));
                trans.set_time(rs.getTimestamp("Timestamp"));
                
                transactions.add(trans);
            }
        } catch (SQLException e) {}{
        }
        
    }
}
