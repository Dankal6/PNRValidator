/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

import PNRValidator.Model.Verification;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of class Verification
 * @author Daniel Kaleta
 */
public class VerificationTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of aboutVerification method, of class Verification.
     */
    @Test
    public void testAboutVerification() {
        Verification instance = new Verification("a","b",true,"c");
        String temp = "b: a is correct\r\n";
        boolean expResult = true;
        String about = instance.aboutVerification();
        boolean result = temp.equals(about);
        assertEquals(expResult, result);
    }
}
