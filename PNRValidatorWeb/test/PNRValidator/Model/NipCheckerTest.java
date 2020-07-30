/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of class NipChecker
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
public class NipCheckerTest {

    NipChecker instance;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new NipChecker();
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests of check method, of class NipChecker.
     */
    @Test
    public void testCheckFirst() {
        String nip = "";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSecond() {
        String nip = "6412318223";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckThird() {
        String nip = "6412318224";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFourth() {
        String nip = "6412318220";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFifth() {
        String nip = "0000000000";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSixth() {
        String nip = "1234567890";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSeventh() {
        String nip = "5842751979";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(nip);
        } catch (BadNipException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    /**
     * Tests of calcCheckSum method, of class NipChecker.
     */
    @Test
    public void testCalcCheckSumFirst() {
        int[] nipInt = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int expResult = 0;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumSecond() {
        int[] nipInt = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int expResult = 230;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumThird() {
        int[] nipInt = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int expResult = 45;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumFourth() {
        int[] nipInt = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        int expResult = -45;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }
}
