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
 * Tests of class PeselChecker
 *
 * @author Daniel Kaleta
 * @version 1.0.0
 */
public class PeselCheckerTest {

    PeselChecker instance;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new PeselChecker();

    }

    @After
    public void tearDown() {
    }

    /**
     * Tests of operateBinary method, of class PeselChecker.
     */
    @Test
    public void testOperateBinaryFirst() {
        PeselChecker.IntegerMath op = (a, b) -> a - b;
        int x = 0;
        int y = 0;
        int expResult = 0;
        int result = instance.operateBinary(x, y, op);
        assertEquals(expResult, result);
    }

    @Test
    public void testOperateBinarySecond() {
        PeselChecker.IntegerMath op = (a, b) -> a - b;
        int x = 1;
        int y = -1;
        int expResult = 2;
        int result = instance.operateBinary(x, y, op);
        assertEquals(expResult, result);
    }

    @Test
    public void testOperateBinaryThird() {
        PeselChecker.IntegerMath op = (a, b) -> a - b;
        int x = -1;
        int y = -1;
        int expResult = 0;
        int result = instance.operateBinary(x, y, op);
        assertEquals(expResult, result);
    }

    @Test
    public void testOperateBinaryFourth() {
        PeselChecker.IntegerMath op = (a, b) -> a - b;
        int x = -1;
        int y = 1;
        int expResult = -2;
        int result = instance.operateBinary(x, y, op);
        assertEquals(expResult, result);
    }

    /**
     * Tests of check method, of class PeselChecker.
     */
    @Test
    public void testCheckFirst() {
        String pesel = "";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSecond() {
        String pesel = "97070206755";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckThird() {
        String pesel = "correctPesel";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFourth() {
        String pesel = "00000000000";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFifth() {
        String pesel = "12345678910";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSixth() {
        String pesel = "98031007648";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSeventh() {
        String pesel = "97070206756";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(pesel);
        } catch (BadPeselException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    /**
     * Tests of calcCheckSum method, of class PeselChecker.
     */
    @Test
    public void testCalcCheckSumFirst() {
        int[] peselInt = {0};
        int expResult = 0;
        int result = instance.calcCheckSum(peselInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumSecond() {
        int[] peselInt = {9, 7, 0, 7, 0, 2, 0, 6, 7, 5};
        int expResult = 35;
        int result = instance.calcCheckSum(peselInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumThird() {
        int[] peselInt = {9, 8, 0, 3, 1, 0, 0, 7, 6, 4};
        int expResult = 32;
        int result = instance.calcCheckSum(peselInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumFourth() {
        int[] peselInt = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int expResult = 0;
        int result = instance.calcCheckSum(peselInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumFifth() {
        int[] peselInt = {-9, -8, -0, -3, -1, 0, 0, -7, -6, -4};
        int expResult = -142;
        int result = instance.calcCheckSum(peselInt);
        assertEquals(expResult, result);
    }

}
