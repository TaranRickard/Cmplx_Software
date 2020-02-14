/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javabeans;

import junit.framework.TestCase;

/**
 *
 * @author taran
 */
public class AccountTest extends TestCase {
    
    public AccountTest(String testName) {
        super(testName);
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of set_balance method, of class Account.
     */
    public void testSet_balance() {
        System.out.println("set_balance");
        float n = 0.0F;
        Account instance = new Account();
        instance.set_balance(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_account_number method, of class Account.
     */
    public void testSet_account_number_float() {
        System.out.println("set_account_number");
        float n = 0.0F;
        Account instance = new Account();
        instance.set_account_number(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_routing method, of class Account.
     */
    public void testSet_routing() {
        System.out.println("set_routing");
        float n = 0.0F;
        Account instance = new Account();
        instance.set_routing(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_clientID method, of class Account.
     */
    public void testSet_clientID() {
        System.out.println("set_clientID");
        float n = 0.0F;
        Account instance = new Account();
        instance.set_clientID(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_APR method, of class Account.
     */
    public void testSet_APR() {
        System.out.println("set_APR");
        float n = 0.0F;
        Account instance = new Account();
        instance.set_APR(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_account_number method, of class Account.
     */
    public void testSet_account_number_String() {
        System.out.println("set_account_number");
        String n = "";
        Account instance = new Account();
        instance.set_account_number(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_balance method, of class Account.
     */
    public void testGet_balance() {
        System.out.println("get_balance");
        Account instance = new Account();
        float expResult = 0.0F;
        float result = instance.get_balance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_account_number method, of class Account.
     */
    public void testGet_account_number() {
        System.out.println("get_account_number");
        Account instance = new Account();
        float expResult = 0.0F;
        float result = instance.get_account_number();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_routing method, of class Account.
     */
    public void testGet_routing() {
        System.out.println("get_routing");
        Account instance = new Account();
        float expResult = 0.0F;
        float result = instance.get_routing();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_clientID method, of class Account.
     */
    public void testGet_clientID() {
        System.out.println("get_clientID");
        Account instance = new Account();
        float expResult = 0.0F;
        float result = instance.get_clientID();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_APR method, of class Account.
     */
    public void testGet_APR() {
        System.out.println("get_APR");
        Account instance = new Account();
        float expResult = 0.0F;
        float result = instance.get_APR();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set_account_number method, of class Account.
     */
    public void testSet_account_number_0args() {
        System.out.println("set_account_number");
        Account instance = new Account();
        String expResult = "";
        String result = instance.set_account_number();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of transfer_from method, of class Account.
     */
    public void testTransfer_from() {
        System.out.println("transfer_from");
        float amount = 0.0F;
        float to = 0.0F;
        Account instance = new Account();
        instance.transfer_from(amount, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
