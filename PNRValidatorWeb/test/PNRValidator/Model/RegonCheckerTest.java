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
 * Tests of class RegonChecker
 *
 * @author Daniel Kaleta
 */
public class RegonCheckerTest {

    RegonChecker instance;

    public RegonCheckerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new RegonChecker();
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests of check method, of class RegonChecker.
     */
    @Test
    public void testCheckFirst() {
        String regon = "";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(regon);
        } catch (BadRegonException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSecond() {
        String regon = "correctRegon";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(regon);
        } catch (BadRegonException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckThird() {
        String regon = "278262144";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(regon);
        } catch (BadRegonException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFourth() {
        String regon = "365095696";
        boolean expResult = true;
        boolean result;
        try {
            result = instance.check(regon);
        } catch (BadRegonException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckFifth() {
        String regon = "278262142";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(regon);
        } catch (BadRegonException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckSixth() {
        String regon = "278262146";
        boolean expResult = false;
        boolean result;
        try {
            result = instance.check(regon);
        } catch (BadRegonException e) {
            result = false;
        }
        assertEquals(expResult, result);
    }

    /**
     * Tests of calcCheckSum method, of class RegonChecker.
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
        int expResult = 192;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumThird() {
        int[] nipInt = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int expResult = 44;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcCheckSumFourth() {
        int[] nipInt = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        int expResult = -44;
        int result = instance.calcCheckSum(nipInt);
        assertEquals(expResult, result);
    }
}
