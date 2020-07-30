/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

import java.util.regex.Pattern;

/**
 * Class to check PESEL correctness
 * @author Daniel Kaleta
 * @version 1.1.1
 */
public class NipChecker implements Checker {

     /**
     * Main method that checks if given NIP is correct or not.
     * @param nip this is NIP to valid.
     * @return returns if given NIP is correct or not.
     * @throws BadNipException if Nip is incorrect
     */
    @Override
    public boolean check(String nip) throws BadNipException {
        Pattern nipPattern = Pattern.compile("\\d{10}");   //NIP number pattern - 10 digits

        if (nipPattern.matcher(nip).matches() == false) {  //checking if the NIP number provided matches the pattern
           throw new BadNipException("nip does not match the pattern!");
        }
        
        int nipInt[] = new int[10];         //table for conversions from characters to integers
        char[] nipChar = nip.toCharArray(); 
        
        //changing type from char to int and putting it into an array in a loop
        for (int i = 0; i < 10; i++) {
            nipInt[i] = Character.getNumericValue(nipChar[i]);    
        }
        
        int checkSum = calcCheckSum(nipInt);    //checksum, needed to determine the check digit

        int checkNum = checkSum % 11;  //setting the check number - the remainder from dividing the check sum by 11

        if (checkNum != nipInt[9]) {       //checking whether the calculated check digit is the same as that entered by the user
            throw new BadNipException("check num is wrong!");
        }
        return true;
    }
    
    /**
     * Method to calculate checksum
     * @param nipInt[] this is NIP converted to an array of integers
     * @return returns calculated checksum
     */
    @Override
    public int calcCheckSum(int nipInt[])
    {
        int checkSum = 0;                   

       //multiplication of digits from the REGON according to the formula, and adding the result to the checksum
        checkSum += nipInt[0] * 6;
        checkSum += nipInt[1] * 5;
        checkSum += nipInt[2] * 7;
        checkSum += nipInt[3] * 2;
        checkSum += nipInt[4] * 3;
        checkSum += nipInt[5] * 4;
        checkSum += nipInt[6] * 5;
        checkSum += nipInt[7] * 6;
        checkSum += nipInt[8] * 7;
        
        return checkSum;
    }
}