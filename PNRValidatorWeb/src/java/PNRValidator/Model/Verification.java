/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PNRValidator.Model;

/**
 * Class representing verification
 * @author Daniel Kaleta
 * @version 1.0.0
 */
public class Verification {
    
    /**
    * Number verified.
    */
    private final String number;
    /**
    * Type of number verified - PESEL, NIP, or REGON.
    */
    private final String type;
    /**
    * Information whether number is correct or not.
    */
    private final boolean isCorrect;
    /**
    * Information why number is incorrect
    */
    private final String whyIncorrect;

     /**
     * Verification constructor
     * @param number this is verificated number.
     * @param type this is type of verificated number (PESEL, NIP or REGON).
     * @param isCorrect this is information whether correct or not.
     * @param whyIncorrect this is information why number is not corrent
     */
    public Verification(String number, String type, boolean isCorrect, String whyIncorrect) {
        this.number = number;
        this.type = type;
        this.isCorrect = isCorrect;
        this.whyIncorrect = whyIncorrect;
    }
    
    /**
    * Method telling about verification
    * 
    * @return String containing information about the verification.
    */
    public String aboutVerification()
    {
        if(isCorrect ==false)
            return (type+": "+number+" is incorrect, because "+whyIncorrect+System.getProperty("line.separator"));
        else
            return (type+": "+number+" is correct."+System.getProperty("line.separator"));
    }
}
