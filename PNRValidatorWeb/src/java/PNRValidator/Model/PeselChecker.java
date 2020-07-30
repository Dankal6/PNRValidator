/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

import java.util.regex.Pattern;

/**
 * Class to check PESEL correctness
 *
 * @author Daniel Kaleta
 * @version 1.1.0
 */
public class PeselChecker implements Checker {

    interface IntegerMath {

        int operation(int a, int b);
    }

    /**
     * Method performing operation.
     *
     * @param a this is one of the numbers to act.
     * @param b this is one of the numbers to act.
     * @param op this is lambda expression.
     * @return returns call of method operation(a, b)
     */
    int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    /**
     * Main method that checks if given PESEL is correct or not
     *
     * @param pesel this is PESEL to valid.
     * @return returns if given PESEL is correct or not.
     * @throws BadPeselException if Pesel is incorrect
     */
    @Override
    public boolean check(String pesel) throws BadPeselException {
        Pattern peselPattern = Pattern.compile("\\d{11}");     //PESEL number pattern - 11 digits

        if (peselPattern.matcher(pesel).matches() == false) {  //checking if the PESEL number provided matches the pattern
            throw new BadPeselException("pesel does not match the pattern!");
        }

        String year = pesel.substring(0, 2);        //birth year from PESEL number
        String month = pesel.substring(2, 4);       //birth month from PESEL number
        String day = pesel.substring(4, 6);         //birth day from PESEL number

        int monthInt = Integer.parseInt(month);
        monthInt = monthInt % 20;                  //modulo 20 operation to obtain a "clean" month
        int dayInt = Integer.parseInt(day);
        int[] peselInt = new int[10];              //table for conversions from characters to integers
        char[] peselChar = pesel.toCharArray();

        if (monthInt < 1 || monthInt > 12) {       //checking if the month is in the real range (1-12)
            throw new BadPeselException("month birht is not real!");
        }
        if (dayInt < 1 || dayInt > 31) {           //checking if the day is in the real range (1-31)
            throw new BadPeselException("day birht is not real!");
        }

        //changing type from char to int and putting it into an array in a loop
        for (int i = 0; i < 10; i++) {
            peselInt[i] = Character.getNumericValue(peselChar[i]);
        }

        int checkSum = calcCheckSum(peselInt);  //checksum, needed to determine the check digit

        if (checkSum > 10) //if the checksum is two-digit, we do the modulo 10 operation on it
        {
            checkSum = checkSum % 10;
        }

        IntegerMath subtraction = (a, b) -> a - b;
        int checkNum = operateBinary(10, checkSum, subtraction);        //the check digit is the difference of 10 with the checksum

        if (checkNum != Character.getNumericValue(peselChar[10])) {       //checking whether the calculated check digit is the same as that entered by the user
            throw new BadPeselException("check num is wrong!");
        }
        return true;
    }

    /**
     * Method to calculate checksum
     *
     * @param peselInt[] this is PESEL converted to an array of integers
     * @return returns calculated checksum
     */
    @Override
    public int calcCheckSum(int peselInt[]) {
        int i = 0, checkSum = 0;
        for (int element : peselInt) {
            //multiplication of digits from the PESEL according to the formula, and adding the result to the checksum
            switch (i % 4) {
                case 0:
                    if (element * 1 > 10) {
                        checkSum += (element * 1) % 10;
                    } else {
                        checkSum += element * 1;
                    }
                    break;
                case 1:
                    if (element * 3 > 10) {
                        checkSum += (element * 3) % 10;
                    } else {
                        checkSum += element * 3;
                    }
                    break;
                case 2:
                    if (element * 7 > 10) {
                        checkSum += (element * 7) % 10;
                    } else {
                        checkSum += element * 7;
                    }
                    break;
                case 3:
                    if (element * 9 > 10) {
                        checkSum += (element * 9) % 10;
                    } else {
                        checkSum += element * 9;
                    }
                    break;
            }
            i++;
        }
        return checkSum;
    }
}

