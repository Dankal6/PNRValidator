/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

import java.util.regex.Pattern;

/**
 * Class to check REGON correctness
 * @author Daniel Kaleta
 * @version 1.2.0
 */
public class RegonChecker implements Checker {

    /**
     * Main method that checks if given REGON is correct or not.
     * @param regon this is REGON to valid.
     * @return returns if given REGON is correct or not.
     * @throws BadRegonException if Regon is incorrect
     */
    @Override
    public boolean check(String regon) throws BadRegonException {
        Pattern regonPattern = Pattern.compile("\\d{9}");      //REGON number pattern - 9 digits

        if (regonPattern.matcher(regon).matches() == false) {  //checking if the REGON number provided matches the pattern
            throw new BadRegonException("regon does not match the pattern!");
        }

        int[] regonInt = new int[9];               //table for conversions from characters to integers
        char[] regonChar = regon.toCharArray();    
        
        //changing type from char to int and putting it into an array in a loop
        for (int i = 0; i < 9; i++) {   
            regonInt[i] = Character.getNumericValue(regonChar[i]); //zmiana typu z char na int i wpisanie go do przygotowanej tablicy w pętli
        }
        
        int checkSum = calcCheckSum(regonInt);  //checksum, needed to determine the check digit

        int checkNum = checkSum % 11;  //setting the check number - the remainder from dividing the check sum by 11

        if (checkNum != regonInt[8]) {       //checking whether the calculated check digit is the same as that entered by the user
            throw new BadRegonException("check num is wrong!");
        }
        return true;
    }
    
    /**
     * Method to calculate checksum
     * @param regonInt[] this is REGON converted to an array of integers
     * @return returns calculated checksum
     */
    @Override
    public int calcCheckSum(int regonInt[])
    {
        int checkSum = 0;
        //multiplication of digits from the REGON according to the formula, and adding the result to the checksum
        checkSum += regonInt[0] * 8; 
        checkSum += regonInt[1] * 9;
        checkSum += regonInt[2] * 2;
        checkSum += regonInt[3] * 3;
        checkSum += regonInt[4] * 4;
        checkSum += regonInt[5] * 5;
        checkSum += regonInt[6] * 6;
        checkSum += regonInt[7] * 7;
        
        return checkSum;
    }
}
