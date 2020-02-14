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
import java.sql.Timestamp;

public class Transaction {
    private float ID;
    private float from;
    private float to;
    private float amount;
    private Timestamp time;
    
    public void set_ID(float n){
        this.ID = n;
    }
    public void set_from(float n){
        this.from = n;
    }
    public void set_to(float n){
        this.to = n;
    }
    public void set_amount(float n){
        this.amount = n;
    }
    public void set_time(Timestamp t){
        this.time = t;
    }
    public Timestamp get_time(){
        return this.time;
    }
    public float get_ID(){
        return this.ID;
    }
    public float get_from(){
        return this.from;
    }
    public float get_to(){
        return this.to;
    }
    public float get_amount(){
        return this.amount;
    }
    
    public String to_String(float account){
        String r = "";
        
        r = time.toString() + " ----- ";
        
        if(account == to ){
           r = r + "credit";
        }
        else{
           r = r + "debit";
        }
        
        r = r + " ----- $";
                
                
        r = r + String.valueOf(amount);
        return r;
    }
}
